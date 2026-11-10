-- ============================================
-- TuPadel - Datos de prueba (SEED)
-- Archivo: docs/seed.sql
-- ============================================


-- ============================================
-- 1. INSTALACIONES
-- ============================================

INSERT INTO instalacion (
    nombre, 
    descripcion,
    direccion,
    localidad,
    tipo_acceso,
    codigo_acceso,
    duracion_sesion_minutos,
    activa
) VALUES
(
    'TuPadel Valencia Centro', 
    'Instalacion de padel con pistas interiores y exteriores, situada en el centro de Valencia',
    'Calle Colon 25',
    'Valencia',
    'PUBLICA',
    'NULL',
    90,
    TRUE
),
(
    'Club Deportivo La Raqueta',
    'Club privado de padel con instalaciones deportivas modernas y acceso para socios.',
    'Avenida de las Cortes Valencianas 40',
    'Valencia',
    'PRIVADA',
    '1234',
    90,
    TRUE
);

-- ============================================
-- 2. PISTAS
-- ============================================

INSERT INTO pista (
    id_instalacion,
    nombre,
    tipo,
    activa
) VALUES
(
    1,
    'Pista Central',
    'INDOOR',
    TRUE
),
(
    1,
    'Pista Exterior 1',
    'OUTDOOR',
    TRUE
),
(
    2,
    'Pista Panoramica',
    'OUTDOOR',
    TRUE
),
(
    2,
    'Pista Cubierta 1',
    'INDOOR',
    TRUE
);

-- ============================================
-- 3. USUARIO DE PRUEBA
-- ============================================

INSERT INTO usuario(
    nombre,
    apellido1,
    apellido2,
    correo,
    password_hash,
    dni,
    rol,
    activo
) VALUES (
    'Carlos',
    'Martinez',
    'Lopez',
    'carlos.martinez@tupadel.test',
    '$2y$10$Udu11V8u/9pDJL9UHrxQ7u1bL36KUPDMzeCt6cAvIn2cnqAvGCxLq',
    '12345678Z',
    'CLIENTE',
    TRUE
);