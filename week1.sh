#!/bin/bash

####################
# Execution Script #
####################

./build.sh

echo "======================================================================"
echo "EXECUTE Tests Week 1"
echo "======================================================================"

java -cp bin/sources.jar com.week1.test.fibonacci.FibonacciTest
java -cp bin/sources.jar com.week1.test.fraction.FractionTest

echo "======================================================================"
echo "SUCCESS"
echo "======================================================================"