package com.example.SistemaDeLivraria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import com.example.SistemaDeLivraria.model.DetalhesImagemLivro;

import com.example.SistemaDeLivraria.model.CategoriaLivro;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "{livro.form.titulo.obrigatoria}")
	@Size(min = 2, message = "{livro.form.titulo.tamanho}")
	private String titulo;

	// @NotEmpty(message="{livro.form.autor.obrigatoria}")
	// @Size(min=2, message="{livro.form.autor.tamanho}")
	// private String autor;

	@NotEmpty(message = "{livro.form.autores.obrigatoria}")
	@ManyToMany
	private List<AutorLivro> autores;

    @NotNull(message="{livro.form.editora.obrigatoria}")
	// @Size(min=2, message="{livro.form.editora.tamanho}")
	@ManyToOne
	private Editora editora;

	@NotNull(message = "{livro.form.data.obrigatoria}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;

	@NotEmpty(message = "{livro.form.origem.obrigatoria}")
	private String origem;
	private String idioma;

	// @NotNull(message="{livro.form.categoria.obrigatoria}")
	// @ManyToOne
	// private CategoriaLivro categoria;
	private int paginas;

	@NotEmpty(message = "{livro.form.categorias.obrigatoria}")
	@ManyToMany
	private List<CategoriaLivro> categorias;

	@NotNull(message = "{livro.form.isbn.obrigatoria}")
	private int isbn;
	private double peso;
	private String caminhoFotoLivro;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal precoTabelado;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal nossoPreco;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal nossoPrecoAluguel;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal nossoPrecoAluguelRenovacao;

	private boolean ativo = true;
	private int venda_aluguel;

	@NotEmpty(message = "{livro.form.descricao.obrigatoria}")
	@Column(columnDefinition = "text")
	private String descricao;

	@NotNull(message = "{livro.form.qtdEstoque.obrigatoria}")
	private int quantidaEmEstoque;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private DetalhesImagemLivro detalhesImagem;

	private String fotoPath;

	public boolean igualdade(int comparar) {
		// System.out.println(comparar);
		// deve ser editado futuramente
		if (/* this.categorias */ 1 == comparar)
			return true;
		else
			return false;
	}

	@Transient
	private MultipartFile fotoDoLivro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String title) {
		this.titulo = title;
	}

	public String getCaminhoFotoLivro() {
		return caminhoFotoLivro;
	}

	public void setCaminhoFotoLivro(String caminhoFotoLivro) {
		this.caminhoFotoLivro = caminhoFotoLivro;
	}

	public List<AutorLivro> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorLivro> author) {
		this.autores = author;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public List<CategoriaLivro> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaLivro> categorias) {
		this.categorias = categorias;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public BigDecimal getPrecoTabelado() {
		return precoTabelado;
	}

	public void setPrecoTabelado(BigDecimal precoTabelado) {
		this.precoTabelado = precoTabelado;
	}

	public BigDecimal getNossoPreco() {
		return nossoPreco;
	}

	public void setNossoPreco(BigDecimal nossoPreco) {
		this.nossoPreco = nossoPreco;
	}

	public BigDecimal getNossoPrecoAluguel() {
		return nossoPrecoAluguel;
	}

	public void setNossoPrecoAluguel(BigDecimal nossoPrecoAluguel) {
		this.nossoPrecoAluguel = nossoPrecoAluguel;
	}

	public BigDecimal getNossoPrecoAluguelRenovacao() {
		return nossoPrecoAluguelRenovacao;
	}

	public void setNossoPrecoAluguelRenovacao(BigDecimal nossoPrecoAluguelRenovacao) {
		this.nossoPrecoAluguelRenovacao = nossoPrecoAluguelRenovacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getVenda_aluguel() {
		return venda_aluguel;
	}

	public void setVenda_aluguel(int venda_aluguel) {
		this.venda_aluguel = venda_aluguel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidaEmEstoque() {
		return quantidaEmEstoque;
	}

	public void setQuantidaEmEstoque(int quantidadeEmEstoque) {
		this.quantidaEmEstoque = quantidadeEmEstoque;
	}

	public MultipartFile getFotoDoLivro() {
		return fotoDoLivro;
	}

	public void setFotoDoLivro(MultipartFile fotoDoLivro) {
		this.fotoDoLivro = fotoDoLivro;
	}

	public String getOrigem() {
		return idioma;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean ehNovo() {
		return (this.id == null);
	}

	public String getFotoPath() {
		return fotoPath;
	}

	public void setFotoPath(String fotoPath) {
		this.fotoPath = fotoPath;
	}

	public DetalhesImagemLivro getDetalhesImagem() {
		return detalhesImagem;
	}

	public void setDetalhesImagem(DetalhesImagemLivro detalhesImage) {
		this.detalhesImagem = detalhesImage;
	}

	public boolean temFotoCapa() {
		return (this.detalhesImagem != null) && (this.detalhesImagem.getNomeArquivo() != null);
	}

}
