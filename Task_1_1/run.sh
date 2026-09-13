#!/bin/bash
set -e

SRC_DIR="src/main/java"
OUT_DIR="out"
DOCS_DIR="docs"
JAR_FILE="main.jar"
MAIN_CLASS="org.example.Main"
PACKAGE="org.example"

echo "==> Cleaning"
rm -rf "$OUT_DIR" "$DOCS_DIR" "$JAR_FILE"
mkdir -p "$OUT_DIR" "$DOCS_DIR"

echo "==> Compiling sources (javac)"
find "$SRC_DIR" -name "*.java" > sources.txt
javac -d "$OUT_DIR" @sources.txt
rm sources.txt

echo "==> Generating documentation (javadoc)"
javadoc -d "$DOCS_DIR" -sourcepath "$SRC_DIR" "$PACKAGE"

echo "==> Creating jar"
jar --create --file "$JAR_FILE" --main-class "$MAIN_CLASS" -C "$OUT_DIR" .

echo "==> Running application"
java -jar "$JAR_FILE"

echo "==> Done."