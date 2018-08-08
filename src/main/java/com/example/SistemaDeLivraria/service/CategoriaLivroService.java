package com.example.SistemaDeLivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaDeLivraria.model.CategoriaLivro;
import com.example.SistemaDeLivraria.model.Editora;
import com.example.SistemaDeLivraria.model.Livro;
import com.example.SistemaDeLivraria.repository.CategoriaLivros;
import com.example.SistemaDeLivraria.repository.Livros;

@Service
public class CategoriaLivroService {

	@Autowired
	CategoriaLivros Categorialivros;

	@Transactional
	public void salva(CategoriaLivro Categorialivro) {
		Categorialivros.save(Categorialivro);
		// fazer outras operações no banco

	}

	public List<CategoriaLivro> todos() {
		return Categorialivros.findAll();
	}

	public CategoriaLivro buscaPor(Integer id) {
		return Categorialivros.findOne(id);
	}

	public void deletarLivro(CategoriaLivro livro) {
		Categorialivros.delete(livro);
	}

	public List<CategoriaLivro> todas() {
		return Categorialivros.findAll();
	}

	@Transactional
	public void excluirPelo(Integer id) {

		CategoriaLivro categoria = this.buscaPor(id);

		if (id != null) {
			Categorialivros.delete(id);
			Categorialivros.flush();
		} else {
			throw new IllegalArgumentException("Informe uma editora válida para exclusão");
		}
	}

}
