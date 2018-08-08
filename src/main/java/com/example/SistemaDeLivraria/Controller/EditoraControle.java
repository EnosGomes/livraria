package com.example.SistemaDeLivraria.Controller;

import java.util.ArrayList;

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
import com.example.SistemaDeLivraria.model.Livro;
import com.example.SistemaDeLivraria.service.CategoriaLivroService;
import com.example.SistemaDeLivraria.service.EditoraService;

@Controller
@RequestMapping("/editora")
public class EditoraControle {

	@Autowired
	EditoraService CategorialivroService;

	@GetMapping("/form")
	public String form(Model model) {
		Editora Categorialivro = new Editora();

		model.addAttribute("editora", Categorialivro);
		return "editora/cadastro-editora";
	}

	@PostMapping("/salva")
	public String salva(@Validated Editora Categorialivro, Errors validacao, RedirectAttributes redirect,
			MultipartFile imagemDoLivro, HttpServletRequest request) {

		// System.out.println("######## Imagem do Livro : " +
		// foto.getOriginalFilename() );

		if (validacao.hasErrors()) {
			return "editora/cadastro-editora";
		}

		CategorialivroService.salva(Categorialivro);

		redirect.addFlashAttribute("mensagem_sucesso", "A Editora do livro foi Salvo com Sucesso");
		String rota = Categorialivro.ehNovo() ? "redirect:/editora/form" : "redirect:/editora/pesquisa";

		return rota;
	}

	@RequestMapping("/deleta{id}")
	public String deletar(@PathVariable Integer id) {
		Editora Categorialivro = CategorialivroService.buscaPor(id);
		CategorialivroService.deletarLivro(Categorialivro);
		return "redirect:/editora/pesquisa";
	}

	@PostMapping("/remove")
	public ModelAndView remove(@ModelAttribute("id") Integer livroId, RedirectAttributes redirect) {
		System.out.println("Deu certo");
		Editora Categorialivro = CategorialivroService.buscaPor(livroId);
		CategorialivroService.deletarLivro(Categorialivro);
		redirect.addFlashAttribute("mensagem_sucesso", "A Editora foi Removida com Sucesso");

		return this.pesquisa();

	}

	@PostMapping("/removeLista")
	@ResponseBody
	public String removeLivros(@RequestBody ArrayList<Integer> listaLivroId) {

		System.out.println("%%%%%%%%%%%%%%%%%%%% Lista de Livros : " + listaLivroId);

		listaLivroId.forEach(id -> CategorialivroService.excluirPelo(id));

		return "Editoras excluídas com sucesso";

	}

	@GetMapping("/pesquisa")
	public ModelAndView pesquisa() {
		ModelAndView modelAndView = new ModelAndView("editora/pesquisa-editora");
		modelAndView.addObject("editoras", CategorialivroService.todos());

		return modelAndView;
	}

	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable Integer id) {
		Editora Categorialivro = CategorialivroService.buscaPor(id);

		ModelAndView modelAndView = new ModelAndView("editora/cadastro-editora");
		modelAndView.addObject("editora", Categorialivro);

		return modelAndView;

	}

}
