FROM mysql:8.4.0

COPY ./database /docker-entrypoint-initdb.d/