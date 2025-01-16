package com.simon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simon.models.LoginUsuario;
import com.simon.models.Usuario;
import com.simon.services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
	private UsuarioService servicio;


    @GetMapping("/") // form de login
	public String formLogin(Model modelo) {
		modelo.addAttribute("loginUsuario", new LoginUsuario());
		return "LoginUsuario";
	}
    @GetMapping("/registro") // form de registro
	public String formRegistro(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "RegisterUsuario";
	}

    @PostMapping("/login") // procesar info del login
	public String logIn(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario, BindingResult validaciones,
			Model modelo, HttpSession sesion) {
		this.servicio.validarLogin(validaciones, loginUsuario);
		if (validaciones.hasErrors()) {
			return "LoginUsuario";
		}
		Usuario usuario = this.servicio.obtenerPorEmail(loginUsuario.getEmail());
		sesion.setAttribute("nombreCompleto", usuario.getNombre() + " " + usuario.getApellido());
		return "inicio";
	}

	@PostMapping("/register") // procesar info del registro
	public String register(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult validaciones, Model modelo,
			HttpSession sesion) {
		this.servicio.validarRegistro(validaciones, usuario);
		if (validaciones.hasErrors()) {
			return "RegistroUsuario";
		}
		Usuario usuario2 = this.servicio.crear(usuario);
		sesion.setAttribute("nombreCompleto", usuario2.getNombre() + " " + usuario2.getApellido());
		return "inicio";
	}
}
