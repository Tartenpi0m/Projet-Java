#!/bin/bash


javac src/musichub/main/*.java -d bin/musichub 2>/dev/null
javac src/musichub/business/*.java -d bin/musichub 2>/dev/null
javac src/musichub/util/*.java -d bin/musichub 2>/dev/null
