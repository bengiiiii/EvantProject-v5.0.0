# ---------- Build stage ----------
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Bağımlılık cache'i için önce POM
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Kaynak kodu kopyala ve paketle
COPY src src
RUN mvn -q -DskipTests package

# ---------- Run stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV TZ=Europe/Istanbul
ENV PORT=8080
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0"

# Build’ten çıkan jar’ı tek isimle kopyala
COPY --from=build /app/target/*.jar /app/app.jar

# Entrypoint
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

EXPOSE 8080
CMD ["/app/entrypoint.sh"]
