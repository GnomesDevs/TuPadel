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

<img width="2752" height="1536" alt="GnomesDev" src="https://github.com/user-attachments/assets/5ae4d42f-e69e-4110-809c-f63575422fba" />
