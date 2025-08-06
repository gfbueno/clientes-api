# Estágio de build: usa uma imagem base para compilar a aplicação
FROM maven:3.9.11-amazoncorretto-21 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml e os arquivos de código-fonte
COPY pom.xml .
COPY src ./src

# Compila a aplicação e empacota em um arquivo .jar
RUN mvn clean package -DskipTests

# Estágio de execução: usa uma imagem otimizada para rodar a aplicação
FROM amazoncorretto:21-alpine-full

# Define o diretório de trabalho para a aplicação
WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080 do contêiner
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado
ENTRYPOINT ["java", "-jar", "app.jar"]