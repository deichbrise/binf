#!/bin/bash

./build.sh
java -cp bin/sources.jar com.week1.test.fibonacci.FibonacciTest
java -cp bin/sources.jar com.week1.test.fraction.FractionTest