# TuPadel

Plataforma web y móvil para la gestión y reserva de pistas de pádel, pensada para ayuntamientos y para administradores de fincas o comunidades de vecinos. Proyecto de fin de grado (DAM).

## Funcionalidades del MVP

- Registro y login de usuarios
- Búsqueda de pistas
- Reserva con horarios
- Cancelación de reservas

## Arquitectura

Monorepo con tres aplicaciones que comparten una única base de datos a través de la API del backend.

| Carpeta | Descripción | Tecnología |
|---------|-------------|------------|
| `backend/` | API REST y lógica de negocio | Java + Spring Boot |
| `web/` | Aplicación web | React (Node.js) + Vite |
| `mobile/` | Aplicación móvil | Kotlin |
| `docs/` | Documentación, modelo E-R y diagramas | Markdown |

Base de datos: MySQL.

## Cómo ejecutarlo en local

1. Clona el repositorio con `git clone https://github.com/GnomesDevs/TuPadel.git` y entra en la carpeta con `cd TuPadel`.
2. Crea una base de datos MySQL vacía para el proyecto.
3. En `backend/`, crea el archivo `application-local.properties` con tus credenciales de MySQL. Está en el `.gitignore` y no se sube al repo.
4. Arranca el backend desde `backend/` con `./mvnw spring-boot:run`, usando el perfil `local`.
5. Arranca la web desde `web/` con `npm install` y después `npm run dev`.
6. Abre `mobile/` con Android Studio y ejecútalo en un emulador.

## Cambios de esta rama (tarea-9-10-11)

- Flyway: migración `V3__seed_desarrollo.sql` en `backend/src/main/resources/db/migration/` con 2 instalaciones, 4 pistas y un usuario de prueba. Las pistas buscan su instalación por nombre, no por id fijo.
- Autenticación: `POST /auth/register` (201) y `POST /auth/login`, que devuelve un JWT con los datos resumidos del usuario.
- Errores de la API: cuerpo `{codigo, mensaje}` (esquema `ErrorDetalle` del OpenAPI) en los 400 y 409. Un correo o contraseña incorrectos devuelven siempre el mismo 401.
- `GET /instalaciones`: protegido con JWT (401 sin token, 200 con token). No devuelve `codigo_acceso`.
- CORS: solo se permite el origen `http://localhost:5173`, con los métodos GET, POST, PUT, DELETE y OPTIONS y las cabeceras `Authorization` y `Content-Type`.
- Configuración de seguridad y de la base de datos en `SecurityConfig`, `application.properties`, `docker-compose.yml` y `.env.example`. La clave de firma del JWT va en la variable `JWT_SECRET` (mínimo 32 caracteres, sin valor real en `.env.example`).

## Usuario de prueba (SOLO PARA DESARROLLO)

El seed `V3__seed_desarrollo.sql` crea este usuario. No existe en producción y estas credenciales no se deben usar fuera de tu entorno local.

| Campo | Valor |
|-------|-------|
| Correo | `carlos.martinez@tupadel.test` |
| Contraseña | `<contraseña-del-seed>` |
| Rol | `CLIENTE` |

Prueba rápida con `curl.exe`:

```powershell
curl.exe -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d "{\"correo\":\"carlos.martinez@tupadel.test\",\"password\":\"<contraseña-del-seed>\"}"
curl.exe -i http://localhost:8080/instalaciones
curl.exe -i http://localhost:8080/instalaciones -H "Authorization: Bearer <token>"
```

## Flujo de trabajo

- `main`: versión estable para entregas.
- `develop`: integración del trabajo del equipo.
- Cada tarea va en una rama propia (`feature/...`, `fix/...`, `docs/...`) creada desde `develop`.
- Todo cambio entra por Pull Request con al menos 1 aprobación.
- Está prohibido subir contraseñas, claves o tokens al repositorio.

## Equipo

- Víctor Touriño
- Josep Arrufat
- Alejandro Martínez
- Rubén Ginés