# ----- Estágio 1: Build (Compilação) -----
# Usamos uma imagem que já contém o Maven e o JDK 17 (especificado no seu pom.xml)
FROM maven:3.9-eclipse-temurin-17 AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache de dependências do Docker
COPY pom.xml .

# Baixa todas as dependências do projeto
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Compila o projeto e gera o arquivo .jar. Pulamos os testes durante o build.
RUN mvn clean package -DskipTests

# ----- Estágio 2: Runtime (Execução) -----
# Usamos uma imagem "slim" (magra) que contém apenas o Java Runtime (JRE)
FROM eclipse-temurin:17-jre-focal

# Define o diretório de trabalho
WORKDIR /app

# Argumento para pegar o nome do JAR gerado no estágio anterior
# O seu pom.xml define <artifactId>project</artifactId> e <version>0.0.1-SNAPSHOT</version>
ARG JAR_FILE=target/project-0.0.1-SNAPSHOT.jar

# Copia o .jar do estágio "builder" para o estágio "runtime"
COPY --from=builder /app/${JAR_FILE} app.jar

# Expõe a porta 8080 (porta padrão do Spring Boot)
EXPOSE 8080

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]