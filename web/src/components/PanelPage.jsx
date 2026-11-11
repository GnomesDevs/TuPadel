import { useAuth } from '../context/AuthContext';

export function PanelPage() {
  const { user, logout } = useAuth();

  return (
    <div style={{ padding: '2rem', maxWidth: '600px', margin: '0 auto' }}>
      <h2>Panel Principal</h2>
      <p>Bienvenido/a, <strong>{user?.nombre || 'Usuario'}</strong></p>
      <p>Rol: <code>{user?.rol || 'CLIENTE'}</code></p>
      <button onClick={logout} style={{ marginTop: '1rem', padding: '0.5rem 1rem' }}>
        Cerrar sesión
      </button>
    </div>
  );
}