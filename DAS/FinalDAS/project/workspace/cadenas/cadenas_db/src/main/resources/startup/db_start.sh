 
#!/usr/bin/env bash

function dbstart () {
    SCHEMA=$1
    echo "Refreshing ${SCHEMA}"
    mvn -f ../../../.. clean install -Denv=$SCHEMA
}

echo "Refreshing Dbs"
dbstart "cadena_axis_one_env" # 1434
dbstart "cadena_rest_one_env" # 1435
dbstart "cadena_rest_two_env" # 1436
dbstart "cadena_cxf_one_env"  # 1437
dbstart "cadena_cxf_two_env"  # 1438
