# Usa imagem do Java 17
FROM eclipse-temurin:21-jdk

# Define diretório de trabalho
WORKDIR /app

# Copia o JAR para dentro do container
COPY target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
