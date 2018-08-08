package com.example.SistemaDeLivraria.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.SistemaDeLivraria.model.CategoriaLivro;
import com.example.SistemaDeLivraria.model.Editora;
import com.example.SistemaDeLivraria.service.CategoriaLivroService;

@Controller
@RequestMapping("/categoria")
public class CategoriaLivroControle {

	@Autowired
	CategoriaLivroService CategorialivroService;

	@GetMapping("/form")
	public String form(Model model) {
		CategoriaLivro Categorialivro = new CategoriaLivro();

		model.addAttribute("categorialivro", Categorialivro);
		return "categoria/cadastro-categoria";
	}

	@PostMapping("/salva")
	public String salva(@Validated CategoriaLivro Categorialivro, Errors validacao, RedirectAttributes redirect,
			MultipartFile imagemDoLivro, HttpServletRequest request) {

		// System.out.println("######## Imagem do Livro : " +
		// foto.getOriginalFilename() );

		if (validacao.hasErrors()) {
			return "categoria/cadastro-categoria";
		}

		CategorialivroService.salva(Categorialivro);

		redirect.addFlashAttribute("mensagem_sucesso", "A Categoria do livro foi Salvo com Sucesso");
		String rota = Categorialivro.ehNovo() ? "redirect:/categoria/form" : "redirect:/categoria/pesquisa";

		return rota;
	}

	@ModelAttribute("todasCategorias")
	public List<CategoriaLivro> todasCategorias() {
		return CategorialivroService.todas();
	}

	@RequestMapping("/deleta{id}")
	public String deletar(@PathVariable Integer id) {
		CategoriaLivro Categorialivro = CategorialivroService.buscaPor(id);
		CategorialivroService.deletarLivro(Categorialivro);
		return "redirect:/categoria/pesquisa";
	}

	@PostMapping("/remove")
	public ModelAndView remove(@ModelAttribute("id") Integer livroId, RedirectAttributes redirect) {
		System.out.println("Deu certo");
		CategoriaLivro Categorialivro = CategorialivroService.buscaPor(livroId);
		CategorialivroService.deletarLivro(Categorialivro);
		redirect.addFlashAttribute("mensagem_sucesso", "O Livro foi Removido com Sucesso");

		return this.pesquisa();

	}

	@GetMapping("/pesquisa")
	public ModelAndView pesquisa() {
		ModelAndView modelAndView = new ModelAndView("categoria/pesquisa-categoria");
		modelAndView.addObject("categorialivros", CategorialivroService.todos());

		return modelAndView;
	}

	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable Integer id) {
		CategoriaLivro Categorialivro = CategorialivroService.buscaPor(id);

		ModelAndView modelAndView = new ModelAndView("categoria/cadastro-categoria");
		modelAndView.addObject("categorialivro", Categorialivro);

		return modelAndView;

	}

	@PostMapping("/removeLista")
	@ResponseBody
	public String removeLivros(@RequestBody ArrayList<Integer> listaLivroId) {

		System.out.println("%%%%%%%%%%%%%%%%%%%% Lista de Livros : " + listaLivroId);

		listaLivroId.forEach(id -> CategorialivroService.excluirPelo(id));

		return "Editoras excluídas com sucesso";

	}

}
