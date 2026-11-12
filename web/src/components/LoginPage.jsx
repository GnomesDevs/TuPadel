import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { loginApi } from '../api/auth';
import './LoginPage.css';

export function LoginPage() {
  const [correo, setCorreo] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [remember, setRemember] = useState(true);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    try {
      const data = await loginApi(correo, password);
      login(data.token, data.usuario);
      navigate('/panel');
    } catch (err) {
      setError(err.message || 'Error al iniciar sesión');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login">
      {/* ───────── Panel izquierdo: imagen ───────── */}
      <aside className="login__hero">
        <div className="login__hero-overlay" />

        <header className="login__hero-top">
          <div className="login__brand">
            <span className="login__brand-name">TuPadel</span>
            <span className="login__brand-icon" aria-hidden="true"></span>
          </div>
          <span className="login__hero-tag">ADMINISTRACIÓN DE PISTA</span>
        </header>

        <div className="login__hero-bottom">
          <p className="login__eyebrow">
            <span className="login__dot" />
            TU PISTA, AL SIGUIENTE NIVEL
          </p>
          <h1 className="login__headline">
            El juego empieza.
            <br />
            La gestión, contigo.
          </h1>
          <p className="login__lead">
            Reservas y jugadores. Todo conectado para que tu pista nunca deje de moverse.
          </p>
          <hr className="login__rule" />
        </div>
      </aside>

      {/* ───────── Panel derecho: formulario ───────── */}
      <main className="login__panel">
        <div className="login__bar">
          <span className="login__admin">
            <svg width="11" height="12" viewBox="0 0 12 13" fill="none" stroke="currentColor" strokeWidth="1.2">
              <rect x="1.5" y="5.5" width="9" height="6.5" rx="1.2" />
              <path d="M3.8 5.5V3.8a2.2 2.2 0 0 1 4.4 0v1.7" />
            </svg>
            ÁREA DE ADMINISTRACIÓN
          </span>

          <label className="login__lang">
            <span className="sr-only">Idioma</span>
            <select defaultValue="es" aria-label="Idioma">
              <option value="es">ES</option>
              <option value="ca">VAL</option>
              <option value="en">EN</option>
            </select>
          </label>
        </div>

        <form className="login__form" onSubmit={handleSubmit}>
          <span className="login__accent" />
          <h2 className="login__title">Vuelve a tu pista.</h2>
          <p className="login__subtitle">
            Inicia sesión para gestionar tu pista,
            <br />
            reservas y equipo desde un solo lugar.
          </p>

          <div className="field">
            <label htmlFor="correo">Correo electrónico</label>
            <input
              id="correo"
              type="email"
              autoComplete="email"
              value={correo}
              onChange={(e) => setCorreo(e.target.value)}
              placeholder="tu@pista.es"
              required
              disabled={loading}
            />
          </div>

          <div className="field">
            <label htmlFor="password">Contraseña</label>
            <div className="field__password">
              <input
                id="password"
                type={showPassword ? 'text' : 'password'}
                autoComplete="current-password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Escribe tu contraseña"
                required
                disabled={loading}
              />
              <button
                type="button"
                className="field__eye"
                onClick={() => setShowPassword((v) => !v)}
                aria-label={showPassword ? 'Ocultar contraseña' : 'Mostrar contraseña'}
                aria-pressed={showPassword}
              >
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                  <path d="M2 12s3.6-7 10-7 10 7 10 7-3.6 7-10 7S2 12 2 12z" />
                  <circle cx="12" cy="12" r="3" />
                  {showPassword && <path d="M4 20 20 4" />}
                </svg>
              </button>
            </div>
          </div>

          <div className="login__row">
            <label className="check">
              <input
                type="checkbox"
                checked={remember}
                onChange={(e) => setRemember(e.target.checked)}
              />
              <span className="check__box" aria-hidden="true">
                <svg viewBox="0 0 12 12" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                  <path d="m2.5 6.2 2.4 2.4L9.5 3.8" />
                </svg>
              </span>
              Recordarme
            </label>
            <a className="login__forgot" href="/recuperar-contrasena">
              ¿Olvidaste tu contraseña?
            </a>
          </div>

          {error && (
            <div className="alert" role="alert">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round">
                <circle cx="12" cy="12" r="9.5" />
                <path d="M12 7.5v5.5M12 16.4v.1" />
              </svg>
              <div>
                <strong>Correo o contraseña incorecto.</strong>
              </div>
            </div>
          )}

          <button type="submit" className="btn" disabled={loading}>
            <span>{loading ? 'Cargando...' : 'Entrar'}</span>
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">
              <path d="M4 12h16M14 6l6 6-6 6" />
            </svg>
          </button>

          <p className="login__help">
            ¿Necesitas acceso? <a href="/contacto">Contacta con tu administrador</a>
          </p>
        </form>

        <footer className="login__footer">
          <span>© 2026 TuPadel</span>
          <nav>
            <a href="/privacidad">Privacidad</a>
            <a href="/terminos">Términos de uso</a>
          </nav>
        </footer>
      </main>
    </div>
  );
}

export default LoginPage;