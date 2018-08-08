package com.example.SistemaDeLivraria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.SistemaDeLivraria.model.Usuario;
import com.example.SistemaDeLivraria.repository.Usuarios;

@Controller
public class UsuarioController {

	@Autowired
	private Usuarios usuarios;

	@PostMapping("/usuario/salvar")
	public ModelAndView salvar(Usuario usuario) {
		// salvar no banco
		usuarios.save(usuario);

		ModelAndView modelAndView = new ModelAndView("conta-usuario");
		modelAndView.addObject("mensagem_sucesso", "Usuário cadastrado com sucesso!");

		return modelAndView;
	}

}
