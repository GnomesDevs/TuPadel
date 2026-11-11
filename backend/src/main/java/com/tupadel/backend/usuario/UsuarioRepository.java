package com.tupadel.backend.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
    boolean existsByDni(String dni);//Con existsByDni y existsByCorreo comprobamos antes de insertar y devolvemos un 409 con un mensaje personalizado ("Ya existe un usuario con ese DNI/correo" por ejemplo)
}
