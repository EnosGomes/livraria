package com.example.SistemaDeLivraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class AutorLivro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "{livro.form.titulo.obrigatoria}")
	@Size(min = 2, message = "{livro.form.titulo.tamanho}")
	private String nomeAutor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public boolean ehNovo() {
		return (this.id == null);
	}

	@Override
	public String toString() {
		return this.nomeAutor;
	}

}
