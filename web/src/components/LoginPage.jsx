import { useState } from 'react';
import './LoginPage.css';

export function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!email || !password) {
      setErrorMessage('Por favor, rellena todos los campos.');
      return;
    }

    setErrorMessage('');
    console.log('Datos listos para enviar:', { email, password });
  };

  return (
    <main className="login-container">
      <section className="login-card">
        <header className="login-header">
          <h1>Panel de Administración</h1>
          <p>Acceso para ayuntamientos y comunidades</p>
        </header>

        {errorMessage && (
          <div className="error-banner" role="alert">
            {errorMessage}
          </div>
        )}

        <form onSubmit={handleSubmit} className="login-form">
          <div className="form-group">
            <label htmlFor="email">Correo electrónico</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="ejemplo@ayuntamiento.es"
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Contraseña</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
              required
            />
          </div>

          <button type="submit" className="submit-button">
            Entrar
          </button>
        </form>
      </section>
    </main>
  );
}