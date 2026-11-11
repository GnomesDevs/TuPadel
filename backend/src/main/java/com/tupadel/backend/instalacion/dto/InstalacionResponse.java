package com.tupadel.backend.instalacion.dto;

import java.time.Instant;

import com.tupadel.backend.instalacion.Instalacion;
import com.tupadel.backend.instalacion.TipoAcceso;

// Los nombres salen en snake_case por spring.jackson.property-naming-strategy=SNAKE_CASE.
public record InstalacionResponse(
        Long idInstalacion,
        String nombre,
        String descripcion,
        String direccion,
        String localidad,
        TipoAcceso tipoAcceso,
        Integer duracionSesionMinutos,
        boolean activa,
        Instant creadoEn,
        Instant actualizadoEn
) {

    public static InstalacionResponse desde(Instalacion i) {
        Short duracion = i.getDuracionSesionMinutos();
        return new InstalacionResponse(
                i.getIdInstalacion(),
                i.getNombre(),
                i.getDescripcion(),
                i.getDireccion(),
                i.getLocalidad(),
                i.getTipoAcceso(),
                duracion == null ? null : duracion.intValue(),
                i.isActiva(),
                i.getCreadoEn(),
                i.getActualizadoEn());
    }
}