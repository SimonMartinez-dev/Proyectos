package com.simon.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.simon.models.LoginUsuario;
import com.simon.models.Usuario;
import com.simon.repositories.RepositorioUsuarios;

@Service
public class UsuarioService {
    @Autowired
	private RepositorioUsuarios repositorio;

	public Usuario crear(Usuario usuario) {
		String hashPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		usuario.setPassword(hashPassword);
		return this.repositorio.save(usuario);
	}
    
    public Usuario obtenerPorEmail(String email) {
		return this.repositorio.findByEmail(email).orElse(null);
	}

    public BindingResult validarRegistro(BindingResult validaciones, Usuario usuario) {
		if (!usuario.getPassword().equals(usuario.getConfirmarPassword())) {
			validaciones.rejectValue("confirmarPassword", "passwordNoCoincide", "La contraseñas no coinciden.");
		}
		return validaciones;
	}

	// Validamos en el login si encontramos el email de usuario
	// Y si coinciden las contraseñas
	public BindingResult validarLogin(BindingResult validaciones, LoginUsuario usuario) {
		Usuario usuarioDb = this.obtenerPorEmail(usuario.getEmail());
		if (usuarioDb == null) {
			validaciones.rejectValue("email", "emailNoRegistrado",
					"El email ingresado no se encuentra en nuestra base de datos.");
		} else {
			if (!BCrypt.checkpw(usuario.getPassword(), usuarioDb.getPassword())) {
				validaciones.rejectValue("password", "passwordIncorrecta", "Contraseña incorrecta.");
			}
		}
		return validaciones;
	}
}
