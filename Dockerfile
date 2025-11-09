# Etapa 1: Build do projeto
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiar Maven Wrapper e pom.xml
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Copiar código-fonte
COPY src ./src

# Dar permissão de execução ao mvnw
RUN chmod +x mvnw

# Build do projeto (sem rodar testes)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar o jar do build anterior
COPY --from=build /app/target/*.jar /app/app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/app.jar"]

