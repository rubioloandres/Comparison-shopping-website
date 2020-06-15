FROM microsoft/mssql-server-linux:2017-latest
ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=Das12345
EXPOSE 1433
WORKDIR /opt/mssql-tools/bin
RUN mkdir scripts
COPY script.sql ./scripts
