#!/usr/bin/env bash

function db_install() {
    CONTAINER=$1
    echo "${CONTAINER}"
    docker exec -d ${CONTAINER} sh -c "./sqlcmd -S localhost -U SA -P Das12345 -i ./scripts/script.sql"
}

echo "Installing DBs"

db_install 'mssql_indec'