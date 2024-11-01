#!/bin/bash

# Compile
javac --module-path /home/as-aravinthakshan/Desktop/javafx-sdk-17.0.13/lib --add-modules javafx.controls,javafx.graphics -cp ".:json-20231013.jar:json-simple-1.1.1.jar" -d bin src/*.java

# Copy resources
cp -r src/resources bin/ 2>/dev/null || true

# Run
java --module-path /home/as-aravinthakshan/Desktop/javafx-sdk-17.0.13/lib --add-modules javafx.controls,javafx.graphics -cp "bin:json-20231013.jar:json-simple-1.1.1.jar" MainApp
