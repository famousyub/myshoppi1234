FROM postgres:13.5-alpine
ENV POSTGRES_PASSWORD root
ENV POSTGRES_USER postgres
ENV POSTGRES_DB perfume
COPY perfumedb_initdb.sql /docker-entrypoint-initdb.d/
EXPOSE 5432

