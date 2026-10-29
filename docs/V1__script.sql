-- ========================================
-- TuPadel - Esquema de base de datos
-- ========================================

-- ======================================
-- Tabla Usuario
-- ======================================
CREATE TABLE usuario(
  
  id_usuario BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(20) NOT NULL,
  apellido1 VARCHAR(20) NOT NULL,
  apellido2 VARCHAR(20) NULL,
  correo VARCHAR(254) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  dni VARCHAR(20) NOT NULL,
  rol ENUM('CLIENTE', 'ADMIN_INSTALACION', 'SUPERADMIN')
    NOT NULL DEFAULT 'CLIENTE',
  activo BOOLEAN NOT NULL DEFAULT TRUE,
  creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_usuario PRIMARY KEY (id_usuario),
  CONSTRAINT uq_usuario_correo UNIQUE (correo),
  CONSTRAINT uq_usuario_dni UNIQUE (dni)
  ) ENGINE=InnoDB;

-- ======================================
-- Tabla Instalacion
-- ======================================

CREATE TABLE instalacion (
id_instalacion BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(150) NOT NULL,
  descripcion TEXT NULL,
  direccion VARCHAR(255) NOT NULL,
  localidad VARCHAR(100) NOT NULL,
  tipo_acceso ENUM('PUBLICA', 'PRIVADA') NOT NULL,
  codigo_acceso VARCHAR(255) NULL,
  duracion_sesion_minutos SMALLINT UNSIGNED NULL,
  activa BOOLEAN NOT NULL DEFAULT TRUE,
  creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP 
    ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_instalacion PRIMARY KEY (id_instalacion)
  )ENGINE=InnoDB;

-- ======================================
-- Tabla Pista
-- ======================================

CREATE TABLE pista(
  id_pista BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  id_instalacion BIGINT UNSIGNED NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  tipo ENUM('INDOOR', 'OUTDOOR') NOT NULL,
  activa BOOLEAN NOT NULL DEFAULT TRUE,
  creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT pk_pista PRIMARY KEY (id_pista),
  
  CONSTRAINT fk_pista_instalacion
    FOREIGN KEY (id_instalacion)
    REFERENCES instalacion(id_instalacion),
  
  CONSTRAINT uq_pista_nombre_instalacion
    UNIQUE (id_instalacion, nombre)
  )ENGINE=InnoDB;

-- ============================================
-- Tabla: UsuarioInstalacion
-- ============================================

CREATE TABLE usuario_instalacion (
    id_usuario BIGINT UNSIGNED NOT NULL,
    id_instalacion BIGINT UNSIGNED NOT NULL,
    asignado_por BIGINT UNSIGNED NOT NULL,
    asignado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_usuario_instalacion
        PRIMARY KEY (id_usuario, id_instalacion),

    CONSTRAINT fk_ui_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario),

    CONSTRAINT fk_ui_instalacion
        FOREIGN KEY (id_instalacion)
        REFERENCES instalacion(id_instalacion),

    CONSTRAINT fk_ui_asignado_por
        FOREIGN KEY (asignado_por)
        REFERENCES usuario(id_usuario)
) ENGINE = InnoDB;

-- ============================================
-- Tabla: FranjaHoraria
-- ============================================

CREATE TABLE franja_horaria (
    id_franja BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_pista BIGINT UNSIGNED NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,

    CONSTRAINT pk_franja_horaria
        PRIMARY KEY (id_franja),

    CONSTRAINT fk_franja_pista
        FOREIGN KEY (id_pista)
        REFERENCES pista(id_pista),

    CONSTRAINT uq_franja_pista_fecha_inicio
        UNIQUE (id_pista, fecha, hora_inicio),

    CONSTRAINT chk_franja_horas
        CHECK (hora_fin > hora_inicio)
) ENGINE = InnoDB;


-- ============================================
-- Tabla: Reserva
-- ============================================

CREATE TABLE reserva (
    id_reserva BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_usuario BIGINT UNSIGNED NOT NULL,
    id_franja BIGINT UNSIGNED NOT NULL,

    estado ENUM('CONFIRMADA', 'CANCELADA')
        NOT NULL DEFAULT 'CONFIRMADA',

    creada_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cancelada_en TIMESTAMP NULL DEFAULT NULL,

    franja_activa BIGINT UNSIGNED
        GENERATED ALWAYS AS (
            CASE
                WHEN estado = 'CONFIRMADA'
                THEN id_franja
                ELSE NULL
            END
        ) STORED,

    CONSTRAINT pk_reserva
        PRIMARY KEY (id_reserva),

    CONSTRAINT fk_reserva_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario),

    CONSTRAINT fk_reserva_franja
        FOREIGN KEY (id_franja)
        REFERENCES franja_horaria(id_franja),

    CONSTRAINT uq_reserva_franja_activa
        UNIQUE (franja_activa),

    CONSTRAINT chk_reserva_cancelacion
        CHECK (
            (estado = 'CONFIRMADA' AND cancelada_en IS NULL)
            OR
            (estado = 'CANCELADA' AND cancelada_en IS NOT NULL)
        )
) ENGINE = InnoDB;
