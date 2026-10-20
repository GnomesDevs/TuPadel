# Este dockerfile es el contexto de build que usará el compose
# Etapa 1: compilar con Maven (no se necesita ni Maven ni JDK instalados para esto)
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Solo copiamos el primer pom para que Docker cachee las dependencias, luego copiamos el codigo y se compila el .jar
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
RUN mvn -B -q package -DskipTests

# Etapa 2: imagen final, solo con el JRE y el .jar
FROM eclipse-temurin:21-jre
WORKDIR /app

# Usuario sin privilegios: el contenedor no corre como root
RUN useradd --system --no-create-home appuser
COPY --from=build /app/target/*.jar app.jar
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# Copiar el pom.xml antes que src hace que, si solo cambio algo de código, Docker reutilice la capa de dependencias y tarde segundos en vez de minutos