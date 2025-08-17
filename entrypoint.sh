#!/usr/bin/env bash
set -e

if [ -n "$DATABASE_URL" ] && [ -z "$SPRING_DATASOURCE_URL" ]; then
  export SPRING_DATASOURCE_URL="$(echo "$DATABASE_URL" | sed -E 's/^postgres(ql)?:/jdbc:postgresql:/')"
fi

if [ -n "$DB_USER" ]; then export SPRING_DATASOURCE_USERNAME="$DB_USER"; fi
if [ -n "$DB_PASSWORD" ]; then export SPRING_DATASOURCE_PASSWORD="$DB_PASSWORD"; fi

export SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-prod}

JAR_FILE=$(ls /app/target/*-SNAPSHOT.jar 2>/dev/null || ls /app/target/*.jar)
exec java -Dserver.port=${PORT:-8080} -jar "$JAR_FILE"
