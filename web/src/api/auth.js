const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

export async function loginApi(correo, password) {
  let response;
  
  try {
      response = await fetch(`${API_URL}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ correo, password }),
    });
  } catch (error) {
    throw new Error('Error al iniciar sesión');
  }
  

  const data = await response.json();
  if (!response.ok) {
    throw new Error('Correo o contraseña incorrecta');
  }

  return data;
}

export async function fetchWithAuth(endpoint, options = {}) {
  const token = localStorage.getItem('token');
  const headers = {
    'Content-Type': 'application/json',
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
    ...options.headers,
  };

  const response = await fetch(`${API_URL}${endpoint}`, { ...options, headers });
  if (response.status === 401) {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = '/login';
  }
  return response;
}