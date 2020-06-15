#!/usr/bin/env bash

function test_db(){
    CONTAINER=$1
    echo "Connecting to container ${CONTAINER}"
    docker exec -ti ${CONTAINER} /bin/bash -c "./sqlcmd -S localhost -U SA -P Das12345"
}

# Example of usages
# test_db axis_one
# use db_supermercados