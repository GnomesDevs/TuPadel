package com.tupadel.backend.auth;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.tupadel.backend.auth.dto.LoginRequest;
import com.tupadel.backend.auth.dto.LoginResponse;
import com.tupadel.backend.auth.dto.RegisterRequest;
import com.tupadel.backend.auth.dto.UsuarioResumen;
import com.tupadel.backend.usuario.Rol;
import com.tupadel.backend.usuario.Usuario;
import com.tupadel.backend.usuario.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarios;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    // Hash de referencia: se compara también cuando el correo no existe, para que el tiempo de respuesta no revele qué correos hay.
    private final String hashFicticio;

    public AuthService(UsuarioRepository usuarios, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarios = usuarios;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.hashFicticio = passwordEncoder.encode("contrasena-ficticia-para-tiempos");
    }

    @Transactional
    public void registrar(RegisterRequest req) {
        if (usuarios.existsByCorreo(req.correo()) || usuarios.existsByDni(req.dni())) {
            throw conflicto();
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(req.nombre());
        usuario.setApellido1(req.apellido1());
        usuario.setApellido2(req.apellido2());
        usuario.setCorreo(req.correo());
        usuario.setDni(req.dni());
        usuario.setPasswordHash(passwordEncoder.encode(req.password()));
        usuario.setRol(Rol.CLIENTE);
        try {
            usuarios.save(usuario);
        } catch (DataIntegrityViolationException e) {
            // Dos registros simultáneos con el mismo correo o DNI: lo resuelve la restricción UNIQUE de la base.
            throw conflicto();
        }
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest req) {
        Optional<Usuario> encontrado = usuarios.findByCorreo(req.correo());
        String hash = encontrado.map(Usuario::getPasswordHash).orElse(hashFicticio);
        boolean passwordOk = passwordEncoder.matches(req.password(), hash);

        if (encontrado.isEmpty() || !passwordOk || !encontrado.get().isActivo()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Usuario usuario = encontrado.get();
        String token = jwtService.generarToken(usuario.getIdUsuario(), usuario.getRol().name());
        return new LoginResponse(
                token,
                new UsuarioResumen(usuario.getIdUsuario(), usuario.getNombre(), usuario.getRol()));
    }

    private ResponseStatusException conflicto() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese correo o DNI");
    }
}