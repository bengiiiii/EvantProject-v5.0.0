#!/usr/bin/env bash
set -e

# DATABASE_URL -> SPRING_DATASOURCE_URL dönüşümü
if [ -n "$DATABASE_URL" ] && [ -z "$SPRING_DATASOURCE_URL" ]; then
  export SPRING_DATASOURCE_URL="$(echo "$DATABASE_URL" | sed -E 's/^postgres(ql)?:/jdbc:postgresql:/')"
fi
[ -n "$DB_USER" ] && export SPRING_DATASOURCE_USERNAME="$DB_USER"
[ -n "$DB_PASSWORD" ] && export SPRING_DATASOURCE_PASSWORD="$DB_PASSWORD"

export SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-prod}

# Jar'ı bul (target veya /app/app.jar)
JAR_FILE=$(ls /app/target/*-SNAPSHOT.jar 2>/dev/null || ls /app/target/*.jar 2>/dev/null || echo "/app/app.jar")

exec java -Dserver.port=${PORT:-8080} -jar "$JAR_FILE"
