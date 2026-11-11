package com.tupadel.backend.instalacion;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tupadel.backend.instalacion.dto.InstalacionResponse;

@Service
public class InstalacionService {

    private final InstalacionRepository instalacionRepository;

    public InstalacionService(InstalacionRepository instalacionRepository) {
        this.instalacionRepository = instalacionRepository;
    }

    @Transactional(readOnly = true)
    public List<InstalacionResponse> listar() {
        return instalacionRepository.findAll().stream()
                .map(InstalacionResponse::desde)
                .toList();
    }
}