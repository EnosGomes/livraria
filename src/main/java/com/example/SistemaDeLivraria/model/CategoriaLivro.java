package com.example.SistemaDeLivraria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CategoriaLivro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "{livro.form.titulo.obrigatoria}")
	@Size(min = 2, message = "{livro.form.titulo.tamanho}")
	private String nomeCategoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	@Override
	public String toString() {
		return this.nomeCategoria;
	}

	// @Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	// private Integer id;
	//
	// @NotEmpty(message="{livro.form.titulo.obrigatoria}")
	// @Size(min=2, message="{livro.form.titulo.tamanho}")
	// private String nomeCategoria;
	//
	//// @OneToMany(mappedBy="categoria")
	//// private List<Livro> livros;
	//
	//
	// public Integer getId() {
	// return id;
	// }
	//
	// public void setId(Integer id) {
	// this.id = id;
	// }
	//
	// public String getNomeCategoria() {
	// return nomeCategoria;
	// }
	//
	// public void setNomeCategoria(String nomeCategoria) {
	// this.nomeCategoria = nomeCategoria;
	// }

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((id == null) ? 0 : id.hashCode());
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// CategoriaLivro other = (CategoriaLivro) obj;
	// if (id == null) {
	// if (other.id != null)
	// return false;
	// } else if (!id.equals(other.id))
	// return false;
	// return true;
	// }
	//
	public boolean ehNovo() {
		return (this.id == null);
	}

	// @Override
	// public String toString() {
	// return this.nomeCategoria;
	// }

}
