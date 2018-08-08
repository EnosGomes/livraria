package com.example.SistemaDeLivraria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.SistemaDeLivraria.repository.Livros;

@Controller
public class HomeController {
	@Autowired
	Livros livros;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("/index");
		modelAndView.addObject("livros", livros.findAll());
		return modelAndView;
	}

	@GetMapping("/minhaConta")
	public String minhaConta() {
		return "conta-usuario";
	}
	/*
	 * @GetMapping("/index2") public ModelAndView home2() { ModelAndView
	 * modelAndView = new ModelAndView("/index2" );
	 * modelAndView.addObject("livros", livros.findAll() ); return modelAndView;
	 * }
	 */

	/*
	 * @GetMapping("/lista") public String list() { return "listagem"; }
	 */

	/*
	 * @GetMapping("/minhaConta") public String minhaConta() { return
	 * "conta-usuario"; }
	 */

}
