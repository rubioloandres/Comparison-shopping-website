#!/usr/bin/env bash

function build_docker(){
    echo "Building docker image mssql_cadena:1.0"
    docker build --no-cache -t mssql_cadena:1.0 .
}
build_docker
