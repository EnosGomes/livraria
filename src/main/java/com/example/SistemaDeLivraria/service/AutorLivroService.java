package com.example.SistemaDeLivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaDeLivraria.model.AutorLivro;
import com.example.SistemaDeLivraria.model.CategoriaLivro;
import com.example.SistemaDeLivraria.model.Editora;
import com.example.SistemaDeLivraria.repository.AutorLivros;
import com.example.SistemaDeLivraria.repository.CategoriaLivros;

@Service
public class AutorLivroService {

	@Autowired
	AutorLivros Autorlivros;

	@Transactional
	public void salva(AutorLivro Autorlivro) {
		Autorlivros.save(Autorlivro);
		// fazer outras operações no banco

	}

	public List<AutorLivro> todos() {
		return Autorlivros.findAll();
	}

	public AutorLivro buscaPor(Integer id) {
		return Autorlivros.findOne(id);
	}

	public void deletarLivro(AutorLivro livro) {
		Autorlivros.delete(livro);
	}

	public List<AutorLivro> todas() {
		return Autorlivros.findAll();
	}

	@Transactional
	public void excluirPelo(Integer id) {

		AutorLivro autor = this.buscaPor(id);

		if (id != null) {
			Autorlivros.delete(id);
			Autorlivros.flush();
		} else {
			throw new IllegalArgumentException("Informe uma editora válida para exclusão");
		}
	}

}
