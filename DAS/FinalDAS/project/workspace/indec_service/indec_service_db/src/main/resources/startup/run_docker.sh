run_docker () {
    echo "Running mssql_indec"
    docker run -p 1439:1433 --name mssql_indec -d mssql_indec:1.0
}

echo "Running Docker container"
run_docker
