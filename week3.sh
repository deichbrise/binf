#!/bin/bash

####################
# Execution Script #
####################

./build.sh

echo "======================================================================"
echo "EXECUTE Tests Week 3"
echo "======================================================================"

java -jar bin/sources.jar ".*\.week3.*"

if [ $? -eq 1 ]; then
    echo "======================================================================"
    echo "ERROR"
    echo "======================================================================"
    exit 1
else
    echo "======================================================================"
    echo "SUCCESS"
    echo "======================================================================"
    exit 0
fi
