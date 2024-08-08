# Use a imagem base do OpenJDK 22
FROM openjdk:22-jdk-slim

# Crie o diretório para a aplicação
WORKDIR /app

# Copie o JAR gerado para o diretório de trabalho
COPY target/library-management-system.jar /app/library-management-system.jar

# Defina o comando para executar o JAR
ENTRYPOINT ["java", "-jar", "library-management-system.jar"]
