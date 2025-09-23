#!/bin/bash
set -e

# ==== CONFIG ====
SRC_DIR="src"
OUT_DIR="build"
CLASS_DIR="$OUT_DIR/classes"
JAR_NAME="$OUT_DIR/TextBasedAdventureGame.jar"
MAIN_CLASS="game.adventure.main.Main"
MANIFEST_FILE="$OUT_DIR/manifest.txt"

# ==== CLEAN PREVIOUS BUILD ====
rm -rf "$CLASS_DIR" "$JAR_NAME" "$MANIFEST_FILE"
mkdir -p "$CLASS_DIR"

# ==== COMPILE CORE SOURCE FILES ====
find "$SRC_DIR/game/adventure" -name "*.java" > sources.txt
javac -d "$CLASS_DIR" @sources.txt
rm sources.txt

# ==== CREATE MANIFEST FILE ====
echo "Main-Class: $MAIN_CLASS" > "$MANIFEST_FILE"
echo "" >> "$MANIFEST_FILE"  # Ensure newline at end (important!)

# ==== PACKAGE INTO EXECUTABLE JAR ====
jar cfm "$JAR_NAME" "$MANIFEST_FILE" -C "$CLASS_DIR" .

echo "Game core compiled to $JAR_NAME"
