package com.example.SistemaDeLivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SistemaDeLivraria.model.CategoriaLivro;
import com.example.SistemaDeLivraria.model.Editora;
import com.example.SistemaDeLivraria.model.Livro;
import com.example.SistemaDeLivraria.repository.Editoras;

@Service
public class EditoraService {

	@Autowired
	private Editoras editoras;

	public List<Editora> todos() {
		return editoras.findAll();
	}

	public List<Editora> todas() {
		return editoras.findAll();
	}

	@Transactional
	public void salva(Editora Categorialivro) {
		editoras.save(Categorialivro);
		// fazer outras operações no banco

	}

	/*
	 * public List<CategoriaLivro> todos() { return editoras.findAll(); }
	 */

	public Editora buscaPor(Integer id) {
		return editoras.findOne(id);
	}

	public void deletarLivro(Editora livro) {
		editoras.delete(livro);
	}

	@Transactional
	public void excluirPelo(Integer id) {

		Editora editora = this.buscaPor(id);

		if (id != null) {
			editoras.delete(id);
			editoras.flush();
		} else {
			throw new IllegalArgumentException("Informe uma editora válida para exclusão");
		}
	}

}
