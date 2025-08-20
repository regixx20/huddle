# Docker Compose for Meat Easy (prise de rendez-vous)

This setup containerizes the Spring Boot app and a PostgreSQL database.

## Files
- `docker-compose.yml` — Orchestrates `db`, `app`, and optional `pgadmin`.
- `Dockerfile` — Multi-stage build for the Spring Boot JAR.
- `src/main/resources/application-docker.properties` — Spring profile for Docker (use `SPRING_PROFILES_ACTIVE=docker`).
- `.env.example` — Copy to `.env` to customize passwords and DB names.
- `docker/postgres/init/001_init.sql` — Optional SQL run once at first DB start.

## Quick start
```bash
# 1) Copy variables
cp .env.example .env

# 2) Build and start
docker compose up -d --build

# 3) App
# http://localhost:8080
# 4) PgAdmin
# http://localhost:5050
```

## Spring configuration
Ensure your **default** `application.properties` keeps your current HSQLDB settings for dev.
This compose uses a **docker** profile with PostgreSQL:
```properties
spring.profiles.active=docker
```
and the file `application-docker.properties` already maps to env vars provided by Docker.

## Migrations (optional but recommended)
If you use Flyway, add `flyway` dependency and place migrations in `src/main/resources/db/migration`.
They will run automatically when the app starts.

## Troubleshooting
- If the app can’t connect to DB, check logs:
  ```bash
  docker compose logs -f db
  docker compose logs -f app
  ```
- If tables aren’t created, temporarily set `SPRING_JPA_HIBERNATE_DDL_AUTO=update` in compose env.
  Prefer Flyway/Liquibase for production.
