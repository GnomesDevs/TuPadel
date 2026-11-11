package com.tupadel.backend.instalacion;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instalacion")
public class Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstalacion;

    @Column(nullable = false, length = 150)
    private String nombre;

    // columnDefinition evita que ddl-auto=validate espere un VARCHAR(255) donde la tabla tiene TEXT.
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String localidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcceso tipoAcceso;

    // SMALLINT UNSIGNED en la tabla, por eso Short y no Integer.
    private Short duracionSesionMinutos;

    @Column(nullable = false)
    private boolean activa = true;

    @Column(insertable = false, updatable = false)
    private Instant creadoEn;

    @Column(insertable = false, updatable = false)
    private Instant actualizadoEn;

    // codigo_acceso no se mapea a propósito: es writeOnly en el OpenAPI y nunca debe salir en una respuesta.

    public Long getIdInstalacion() {
        return idInstalacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public TipoAcceso getTipoAcceso() {
        return tipoAcceso;
    }

    public Short getDuracionSesionMinutos() {
        return duracionSesionMinutos;
    }

    public boolean isActiva() {
        return activa;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public Instant getActualizadoEn() {
        return actualizadoEn;
    }
}