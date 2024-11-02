#!/bin/bash

javac --module-path /home/as-aravinthakshan/Desktop/javafx-sdk-17.0.13/lib --add-modules javafx.controls,javafx.graphics -cp ".:json-20231013.jar:json-simple-1.1.1.jar" -d bin src/*.java

cp -r src/resources bin/ 2>/dev/null || true

java --module-path /home/as-aravinthakshan/Desktop/javafx-sdk-17.0.13/lib --add-modules javafx.controls,javafx.graphics -cp "bin:json-20231013.jar:json-simple-1.1.1.jar" MainApp
