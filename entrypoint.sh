#!/usr/bin/env bash
set -e

# 1) DATABASE_URL -> JDBC ve user:pass@ kısmını temizle
if [ -n "$DATABASE_URL" ]; then
  # Şemayı jdbc:postgresql yap
  JDBC_URL="$(echo "$DATABASE_URL" | sed -E 's/^postgres(ql)?:/jdbc:postgresql:/')"
  # jdbc:postgresql://user:pass@host:port/db  ->  jdbc:postgresql://host:port/db
  JDBC_URL="$(echo "$JDBC_URL" | sed -E 's#(jdbc:postgresql://)[^/@]+@#\1#')"
  export SPRING_DATASOURCE_URL="$JDBC_URL"
fi

# 2) Kullanıcı/şifreyi ENV'den geçir
[ -n "$DB_USER" ] && export SPRING_DATASOURCE_USERNAME="$DB_USER"
[ -n "$DB_PASSWORD" ] && export SPRING_DATASOURCE_PASSWORD="$DB_PASSWORD"

# 3) Profil ve port
export SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-prod}

# 4) JAR'ı bul ve çalıştır
JAR_FILE=$(ls /app/target/*-SNAPSHOT.jar 2>/dev/null || ls /app/target/*.jar 2>/dev/null || echo "/app/app.jar")
exec java -Dserver.port=${PORT:-8080} -jar "$JAR_FILE"
