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

import com.example.SistemaDeLivraria.model.AutorLivro;
import com.example.SistemaDeLivraria.service.AutorLivroService;

@Controller
@RequestMapping("/autor")
public class AutorLivroControle {

	@Autowired
	AutorLivroService AutorlivroService;

	@GetMapping("/form")
	public String form(Model model) {
		AutorLivro Autorlivro = new AutorLivro();

		model.addAttribute("autorlivro", Autorlivro);
		return "autor/cadastro-autor";
	}

	@PostMapping("/salva")
	public String salva(@Validated AutorLivro Autorlivro, Errors validacao, RedirectAttributes redirect,
			MultipartFile imagemDoLivro, HttpServletRequest request) {

		// System.out.println("######## Imagem do Livro : " +
		// foto.getOriginalFilename() );

		if (validacao.hasErrors()) {
			return "autor/cadastro-autor";
		}

		AutorlivroService.salva(Autorlivro);

		redirect.addFlashAttribute("mensagem_sucesso", "O autor do livro foi Salvo com Sucesso");
		String rota = Autorlivro.ehNovo() ? "redirect:/autor/form" : "redirect:/autor/pesquisa";

		return rota;
	}

	@ModelAttribute("todosAutores")
	public List<AutorLivro> todosAutores() {
		return AutorlivroService.todas();
	}

	@RequestMapping("/deleta{id}")
	public String deletar(@PathVariable Integer id) {
		AutorLivro Autorlivro = AutorlivroService.buscaPor(id);
		AutorlivroService.deletarLivro(Autorlivro);
		return "redirect:/autor/pesquisa";
	}

	@PostMapping("/remove")
	public ModelAndView remove(@ModelAttribute("id") Integer livroId, RedirectAttributes redirect) {
		System.out.println("Deu certo");
		AutorLivro Autorlivro = AutorlivroService.buscaPor(livroId);
		AutorlivroService.deletarLivro(Autorlivro);
		redirect.addFlashAttribute("mensagem_sucesso", "O Autor foi Removido com Sucesso");

		return this.pesquisa();

	}

	@GetMapping("/pesquisa")
	public ModelAndView pesquisa() {
		ModelAndView modelAndView = new ModelAndView("autor/pesquisa-autor");
		modelAndView.addObject("autorlivros", AutorlivroService.todos());

		return modelAndView;
	}

	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable Integer id) {
		AutorLivro Autorlivro = AutorlivroService.buscaPor(id);

		ModelAndView modelAndView = new ModelAndView("autor/cadastro-autor");
		modelAndView.addObject("autorlivro", Autorlivro);

		return modelAndView;

	}

	@PostMapping("/removeLista")
	@ResponseBody
	public String removeLivros(@RequestBody ArrayList<Integer> listaLivroId) {

		System.out.println("%%%%%%%%%%%%%%%%%%%% Lista de Livros : " + listaLivroId);

		listaLivroId.forEach(id -> AutorlivroService.excluirPelo(id));

		return "livros excluídos com sucesso";

	}

}
