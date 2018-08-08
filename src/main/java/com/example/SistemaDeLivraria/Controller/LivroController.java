package com.example.SistemaDeLivraria.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.SistemaDeLivraria.model.Livro;
import com.example.SistemaDeLivraria.repository.Livros;
import com.example.SistemaDeLivraria.service.AutorLivroService;
import com.example.SistemaDeLivraria.service.CategoriaLivroService;
import com.example.SistemaDeLivraria.service.EditoraService;
import com.example.SistemaDeLivraria.service.LivroService;

import ifma.dcomp.mybookstore.controller.util.ArquivoUpload;

import com.example.SistemaDeLivraria.model.Editora;
import com.example.SistemaDeLivraria.model.AutorLivro;
import com.example.SistemaDeLivraria.model.CategoriaLivro;
import com.example.SistemaDeLivraria.model.DetalhesImagemLivro;

@Controller
@RequestMapping("/livro")
public class LivroController {

	// @Autowired
	// Livros livros;
	@Autowired
	CategoriaLivroService CategorialivroService;

	@Autowired
	AutorLivroService AutorlivroService;

	@Autowired
	EditoraService editoraService;

	@Autowired
	LivroService livroService;

	// @GetMapping("/form")
	// public String form(Model model) {
	// Livro livro = new Livro();
	// model.addAttribute("categorialivros", CategorialivroService.todos() );
	// model.addAttribute("editoras", editoraService.todos() );
	// model.addAttribute("livro", livro);
	// return "livro/cadastro-livro";
	// }

	@GetMapping("/form")
	public String form(Model model, Livro livro) {
		model.addAttribute("livro", livro);
		return "livro/cadastro-livro";
	}

	@ModelAttribute("editoras")
	public List<Editora> todasEditoras() {
		return editoraService.todas();
	}

	@ModelAttribute("todosAutores")
	public List<AutorLivro> todosAutores() {
		return AutorlivroService.todas();
	}

	/*
	 * @PostMapping("/adiciona") public String salva(Livro livro,
	 * RedirectAttributes redirect) {
	 * 
	 * String rota = livro.ehNovo() ? "redirect:/livro/form" :
	 * "redirect:/livro/pesquisa";
	 * 
	 * livros.save(livro);
	 * 
	 * redirect.addFlashAttribute("mensagem_sucesso",
	 * "O livro foi salvo com Sucesso" ); return rota; }
	 */
	// @PostMapping("/salva")
	// public String salva(@Validated Livro livro,
	// Errors validacao,
	// Model model,
	// /*Long categoriaId,*/
	// Long editoraId,
	// RedirectAttributes redirect,
	// MultipartFile imagemDoLivro,
	// HttpServletRequest request ) {
	//
	// //System.out.println("######## Imagem do Livro : " +
	// foto.getOriginalFilename() );
	// System.out.println("asdasdasd");
	// //deve ser editado
	// if (validacao.hasErrors() ) {
	// model.addAttribute("categorialivros", CategorialivroService.todos() );
	// model.addAttribute("editoras", editoraService.todos() );
	// model.addAttribute("livro", livro);
	// System.out.println("asdasdasd");
	// return "livro/cadastro-livro";
	// //return modelAndView;
	// }
	// System.out.println("asdasdasd");
	// //modo de salvar imagem do livro antigo
	//// if ( foiSelecionadaA(imagemDoLivro )) {
	//// SalvaArquivoNoServidor salvaArquivo = new SalvaArquivoNoServidor();
	//// String fotoPath = salvaArquivo.salvaImagem("imagens/img-livros" ,
	// imagemDoLivro, request);
	//// livro.setFotoPath(fotoPath);
	////
	//// }
	// System.out.println("asdasdasd");
	// if ( foiSelecionadaA(imagemDoLivro )) {
	// ArquivoUpload arquivoUpload = new ArquivoUpload(imagemDoLivro);
	//
	// arquivoUpload.salvaImagem("imagens/img-livros", livro.getId(), request );
	//
	// DetalhesImagemLivro imagemLivro =
	// arquivoUpload.criaDetalhesImagemLivro();
	//
	// livro.setDetalhesImagem(imagemLivro);
	//
	// }
	//
	// System.out.println("asdasdasd ttttt " +editoraId);
	// livroService.salva(livro, /*categoriaId,*/ editoraId);
	// System.out.println("asdasdasd");
	//
	// redirect.addFlashAttribute("mensagem_sucesso", "O livro foi Salvo com
	// Sucesso" );
	// String rota = livro.ehNovo() ? "redirect:/livro/form" :
	// "redirect:/livro/pesquisa";
	//
	// return rota;
	// }

	@PostMapping("/salva")
	public String salva(@Validated Livro livro, Errors validacao, RedirectAttributes redirect,
			MultipartFile imagemDoLivro, HttpServletRequest request) {

		if (validacao.hasErrors()) {
			return "livro/cadastro-livro";
		}

		livro = livroService.salva(livro);

		if (foiSelecionadaA(imagemDoLivro)) {
			ArquivoUpload arquivoUpload = new ArquivoUpload(imagemDoLivro);

			arquivoUpload.salvaImagem("imagens/img-livros", livro.getId(), request);

			DetalhesImagemLivro imagemLivro = arquivoUpload.criaDetalhesImagemLivro();

			livro.setDetalhesImagem(imagemLivro);

		}
		livroService.salva(livro);

		redirect.addFlashAttribute("mensagem_sucesso", "O livro foi Salvo com Sucesso");
		String rota = livro.ehNovo() ? "redirect:/livro/form" : "redirect:/livro/pesquisa";

		return rota;
	}

	/*
	 * private String salvaImagem(String baseFolder, MultipartFile arquivo,
	 * HttpServletRequest request) {
	 * 
	 * try { // obtem o caminho completo (real) no servidor. String realPath =
	 * request.getServletContext().getRealPath("/" + baseFolder );
	 * 
	 * String path = realPath + "/" + arquivo.getOriginalFilename() ;
	 * 
	 * System.out.println(path );
	 * 
	 * // transfere o arquivo para o servidor arquivo.transferTo( new File(path)
	 * );
	 * 
	 * // retorna o endereço relativo return baseFolder + "/" +
	 * arquivo.getOriginalFilename();
	 * 
	 * } catch (IllegalStateException | IOException e ) {
	 * 
	 * throw new RuntimeException(e ); }
	 * 
	 * }
	 */

	private boolean foiSelecionadaA(MultipartFile imagem) {
		return (imagem != null) && (!imagem.isEmpty());
	}

	@RequestMapping("/deleta{id}")
	public String deletar(@PathVariable Long id) {
		// Livro livro = livros.findOne(id );
		// livros.delete(livro);
		Livro livro = livroService.buscaPor(id);
		livroService.deletarLivro(livro);
		return "redirect:/livro/pesquisa";
	}

	// @PostMapping("/remove")
	// public ModelAndView remove(@ModelAttribute("id") Long livroId,
	// RedirectAttributes redirect) {
	// System.out.println("Deu certo");
	// Livro livro = livroService.buscaPor(livroId);
	// livroService.deletarLivro(livro);
	// redirect.addFlashAttribute("mensagem_sucesso", "O Livro foi Removido com
	// Sucesso" );
	//
	// return this.pesquisa();
	//
	//
	// }
	//
	// @PostMapping("/removeLista")
	// @ResponseBody
	// public String removeLivros(@RequestBody ArrayList<Long> listaLivroId) {
	//
	// System.out.println("%%%%%%%%%%%%%%%%%%%% Lista de Livros : " +
	// listaLivroId);
	//
	// listaLivroId.forEach( id -> livroService.excluirPelo(id) );
	//
	// return "livros excluídos com sucesso";
	//
	// }

	@PostMapping("/remove")
	public ModelAndView remove(@ModelAttribute("id") Long livroId, RedirectAttributes redirect) {
		livroService.excluirPelo(livroId);
		redirect.addFlashAttribute("mensagem_sucesso", "O Livro foi Removido com Sucesso");

		return this.pesquisa();

	}

	@PostMapping("/removeLista")
	@ResponseBody
	public String removeLivros(@RequestBody ArrayList<Long> listaLivroId) {

		System.out.println("%%%%%%%%%%%%%%%%%%%% Lista de Livros : " + listaLivroId);

		listaLivroId.forEach(id -> livroService.excluirPelo(id));

		return "livros excluídos com sucesso";

	}

	@ModelAttribute("todasCategorias")
	public List<CategoriaLivro> todasCategorias() {
		return CategorialivroService.todas();
	}

	/*
	 * @GetMapping("/pesquisa") public ModelAndView pesquisa() { ModelAndView
	 * modelAndView = new ModelAndView("livro/pesquisa-livro" );
	 * modelAndView.addObject("livros", livros.findAll() );
	 * 
	 * return modelAndView; }
	 */
	@GetMapping("/pesquisa")
	public ModelAndView pesquisa() {
		ModelAndView modelAndView = new ModelAndView("livro/pesquisa-livro");
		modelAndView.addObject("livros", livroService.todos());

		return modelAndView;
	}

	@GetMapping("/visualisa/{id}")
	public ModelAndView visualisar(@PathVariable Long id) {
		// Livro livro = livros.findOne(id);
		Livro livro = livroService.buscaPor(id);

		ModelAndView modelAndView = new ModelAndView("livro/view");
		modelAndView.addObject("livro", livro);

		return modelAndView;

	}

	/*
	 * @GetMapping("{id}") public ModelAndView edicao(@PathVariable Long id) {
	 * Livro livro = livros.findOne(id );
	 * 
	 * ModelAndView modelAndView = new ModelAndView("livro/cadastro-livro");
	 * modelAndView.addObject("livro", livro);
	 * 
	 * return modelAndView;
	 * 
	 * }
	 */

	// deve ser alterado
	// @GetMapping("{id}")
	// public ModelAndView edicao(@PathVariable Long id) {
	// System.out.println("asdasdasd jjjjjjjjj");
	// Livro livro = livroService.buscaPor(id );
	//
	// ModelAndView modelAndView = new ModelAndView("livro/cadastro-livro");
	// System.out.println("asdasdasd jjjjjjjjj 2 ");
	// modelAndView.addObject("categorialivros", CategorialivroService.todos()
	// );
	// System.out.println("asdasdasd jjjjjjjjj 3");
	// modelAndView.addObject("editoras", editoraService.todos() );
	// System.out.println("asdasdasd jjjjjjjjj 4");
	// modelAndView.addObject("livro", livro);
	// System.out.println("asdasdasd jjjjjjjjj 5");
	// return modelAndView;
	//
	//
	// }

	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		Livro livro = livroService.buscaPor(id);

		ModelAndView modelAndView = new ModelAndView("livro/cadastro-livro");
		modelAndView.addObject("livro", livro);

		return modelAndView;

	}

	@GetMapping("/{id}/detalhes")
	public ModelAndView detalhes(@PathVariable("id") Long id) {

		Livro livro = livroService.buscaPor(id);
		ModelAndView modelAndView = new ModelAndView("livro/detalhes-livro");
		modelAndView.addObject("livro", livro);

		return modelAndView;

	}

	/*
	 * @RequestMapping(value = "upload", method = RequestMethod.POST) public
	 * String upload(HttpServletRequest request) throws IllegalStateException,
	 * IOException { MultipartHttpServletRequest multipartRequest =
	 * (MultipartHttpServletRequest) request; MultipartFile multipartFile =
	 * multipartRequest.getFile("file");
	 * System.out.println(multipartFile.getSize()); return
	 * "redirect:/livro/form"; }
	 */

	@PostMapping("/filtrar")
	public ModelAndView filtrar(@RequestParam(required = false) String titulo, @RequestParam Long editoraId) {

		System.out.println("-------------------------------");
		System.out.println(titulo);
		System.out.println(editoraId);

		ModelAndView modelAndView = new ModelAndView("livro/pesquisa-livro");
		modelAndView.addObject("livros", livroService.todos());

		return modelAndView;
	}

}
