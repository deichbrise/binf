#!/bin/bash

###############
# Build Script
###############

echo "======================================================================"
echo "START COMPILING RESOURCES"
echo "======================================================================"
if [ -d bin ]; then
    printf '%s\n' "Removing Lock (bin)"
    rm -rf bin
fi

mkdir -p bin

javac -cp "lib/*" -d bin $(find ./src/* | grep .java)

echo "======================================================================"
echo "FINISH COMPILING RESOURCES"
echo "======================================================================"

echo "======================================================================"
echo "START PACKAGE RESOURCES"
echo "======================================================================"

cd bin && jar cvfm sources.jar ../MANIFEST.MF com ../lib/*.jar META-INF && cd ..
cd bin && jar cvfm calculator.jar ../resources/manifests/calculator/MANIFEST.MF com ../lib/*.jar META-INF && cd ..

echo "======================================================================"
echo "FINISH PACKAGE RESOURCES"
echo "======================================================================"

echo "======================================================================"
echo "GENERATING JAVADOC"
echo "======================================================================"

javadoc -cp ".:lib/*" -d javadoc/ -sourcepath src/ -subpackages com

echo "======================================================================"
echo "FINISHED GENERATING JAVADOC"
echo "======================================================================"
