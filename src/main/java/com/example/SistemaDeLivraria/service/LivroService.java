package com.example.SistemaDeLivraria.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaDeLivraria.model.CategoriaLivro;
import com.example.SistemaDeLivraria.model.Editora;
import com.example.SistemaDeLivraria.model.Livro;
import com.example.SistemaDeLivraria.repository.CategoriaLivros;
import com.example.SistemaDeLivraria.repository.Editoras;
import com.example.SistemaDeLivraria.repository.Livros;

import com.example.SistemaDeLivraria.model.DetalhesImagemLivro;

@Service
public class LivroService {

	@Autowired
	Livros livros;

	@Autowired
	private CategoriaLivros categoriaLivros;

	@Autowired
	private Editoras editoras;

	@Transactional
	public Livro salva(Livro livro /* ,Long categoriaId, *//* Long editoraId */) {
		System.out.println("asdasdasd");
		// List<CategoriaLivro> categoriaLivro =
		// categoriaLivros.findOne(categoriaId);
		// List<CategoriaLivro> categoriaLivro = (List<CategoriaLivro>)
		// categoriaLivros.findOne(categoriaId);
		System.out.println("asdasdasd");
		// Editora editora = editoras.findOne(editoraId);
		// livro.setCategorias(categoriaLivro);
		// livro.setEditora(editora);

		if (houveEdicaoSemFotoSelecionadaNo(livro)) {
			Livro livroAntigo = buscaPor(livro.getId());

			if (livroAntigo.temFotoCapa()) {
				livro.setDetalhesImagem(livroAntigo.getDetalhesImagem());
			}
		}
		System.out.println("asdasdasd");
		return livros.save(livro);
		// fazer outras operações no banco

	}

	private boolean houveEdicaoSemFotoSelecionadaNo(Livro livro) {
		return (livro != null) && (livro.getId() != null) && (livro.getDetalhesImagem() == null);
	}

	public List<Livro> todos() {
		return livros.findAll();
	}

	public Livro buscaPor(Long id) {
		return livros.findOne(id);
	}

	public void deletarLivro(Livro livro) {
		livros.delete(livro);
	}

	@Transactional
	public void excluirPelo(Long id) {

		Livro livro = this.buscaPor(id);
		// if (livro != null && livro.getDetalhesImagem() != null)
		// this.excluirImagem(livro.getDetalhesImagem() );
		if (livro != null && livro.getDetalhesImagem() != null)
			this.excluirImagem(livro.getDetalhesImagem());

		if (id != null) {
			livros.delete(id);
			livros.flush();
		} else {
			throw new IllegalArgumentException("Informe um livro válido para exclusão");
		}
	}

	private void excluirImagem(DetalhesImagemLivro detalhesImagem) {

		Path path = Paths.get(detalhesImagem.getRealPathComNomeDoArquivo());
		try {
			Files.deleteIfExists(path);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
