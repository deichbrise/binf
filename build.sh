#!/bin/bash

if [ -d bin ]; then
    printf '%s\n' "Removing Lock (bin)"
    rm -rf bin
fi
mkdir -p bin
javac -cp ".:lib/*" -d bin $(find ./src/* | grep .java)
cd bin && jar cvf sources.jar com