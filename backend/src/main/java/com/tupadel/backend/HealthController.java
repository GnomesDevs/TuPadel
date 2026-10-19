package com.tupadel.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //le dice a Spring que esta clase es un bean que atiende peticiones HTTP, y que lo que devuelven sus métodos va directo en el cuerpo de la respuesta.
public class HealthController {

    @GetMapping("/health") //asocia las peticiones GET /health con ese metodo.
    public String health() {
        return "OK";
    }
}