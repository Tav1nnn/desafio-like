# Use uma imagem oficial do Maven com JDK 17 como a imagem base
FROM maven:3.8.4-openjdk-17-slim AS build

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o pom.xml e os arquivos do projeto para o contêiner
COPY pom.xml .
COPY src ./src

# Construa o aplicativo usando Maven
RUN mvn clean package -DskipTests

# Use a imagem oficial do Eclipse Temurin com JDK 17 como a imagem base
FROM eclipse-temurin:17

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR construído do estágio anterior para o contêiner
COPY --from=build /app/target/*.jar ./app.jar

# Defina o comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
