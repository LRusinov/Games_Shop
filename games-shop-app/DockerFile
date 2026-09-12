FROM eclipse-temurin:17-jre

WORKDIR /games-shop-app

COPY target/*.jar Games_Shop-0.0.1-SNAPSHOT.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "Games_Shop-0.0.1-SNAPSHOT.jar"]
