# Usamos una imagen ligera de Node.js en su versión LTS
FROM node:20-alpine

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los archivos de dependencias
COPY package*.json ./

# Instalamos las dependencias dentro del contenedor
RUN npm install

# Copiamos el resto del código del proyecto
COPY . .

# Exponemos el puerto de Vite
EXPOSE 5173

# Comando para iniciar el servidor de desarrollo
CMD ["npm", "run", "dev"]