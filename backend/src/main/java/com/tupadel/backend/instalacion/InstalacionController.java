package com.tupadel.backend.instalacion;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tupadel.backend.instalacion.dto.InstalacionResponse;

@RestController
@RequestMapping("/instalaciones")
public class InstalacionController {

    private final InstalacionService instalacionService;

    public InstalacionController(InstalacionService instalacionService) {
        this.instalacionService = instalacionService;
    }

    // Roles autorizados según el OpenAPI: CLIENTE, ADMIN_INSTALACION y SUPERADMIN.
    // Los tres son todos los roles existentes, así que basta con estar autenticado (lo exige SecurityConfig).
    @GetMapping
    public List<InstalacionResponse> listar() {
        return instalacionService.listar();
    }
}