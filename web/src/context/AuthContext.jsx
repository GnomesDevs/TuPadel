import { createContext, useContext, useState } from 'react';

const AuthContext = createContext(null);
const ROLES_PERMITIDOS = ['ADMIN_INSTALACION', 'SUPERADMIN'];

export function AuthProvider({ children }) {
  const [token, setToken] = useState(() => localStorage.getItem('token') || null);
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('user');
    return saved ? JSON.parse(saved) : null;
  });

  const login = (newToken, userData) => {
    if (!ROLES_PERMITIDOS.includes(data.usuario.rol)) {
      throw new Error('Acceso denegado: esta web es exclusiva para administradores.');
    } 

    setToken(newToken);
    setUser(userData);
    localStorage.setItem('token', newToken);
    localStorage.setItem('user', JSON.stringify(userData));
  };
  
  const logout = () => {
    setToken(null);
    setUser(null);
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  };
  
  const tieneRolValido = Boolean(user && ROLES_PERMITIDOS.includes(user.rol));

  return (
    <AuthContext.Provider value={{ token, user, login, logout, isAuthenticated: !!token && tieneRolValido,}}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth debe usarse dentro de AuthProvider');
  return context;
}