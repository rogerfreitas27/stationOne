# syntax=docker/dockerfile:1
# Com a tag acima eu garanto que  o docker usara a sintaxe mais atual



FROM openjdk:16-alpine3.13 as base

WORKDIR /app
# Aqui eu defino o diretorio de trabalho da imagem


COPY .mvn/ .mvn
COPY mvnw pom.xml ./






 
RUN chmod +x ./mvnw



COPY src ./src
# Este COPY comando pega todos os arquivos localizados no diretório atual e os copia na imagem. 





FROM base as development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]


FROM base as build
RUN ./mvnw  package



FROM openjdk:11-jre-slim as production
EXPOSE 8080
COPY --from=build /app/target/com.stationone.filme-*.jar /com.stationone.filme.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/com.stationone.filme.jar"]



     
