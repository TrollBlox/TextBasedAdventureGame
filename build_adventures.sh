#!/bin/bash
set -e

# Set paths
SRC_DIR="src"
OUT_DIR="build"
ADVENTURES_DIR="$SRC_DIR/game/adventures"
CLASS_DIR="$OUT_DIR/adventures_classes"
CORE_JAR="$OUT_DIR/TextBasedAdventureGame.jar"

# Clean previous builds
rm -rf "$CLASS_DIR"
mkdir -p "$CLASS_DIR"

# Loop through each .java file in src/game.adventures/
for file in "$ADVENTURES_DIR"/*.java; do
    [ -e "$file" ] || continue
    filename=$(basename -- "$file")
    classname="${filename%.*}"
    out_jar="$OUT_DIR/${classname}.jar"
    out_class_dir="$CLASS_DIR/$classname"

    mkdir -p "$out_class_dir"

    echo "Compiling $filename..."

    javac -cp "$CORE_JAR:$SRC_DIR" -d "$out_class_dir" "$file"

    jar cf "$out_jar" -C "$out_class_dir" .

    echo "Created $out_jar"
done

rm -rf $CLASS_DIR
rm -rf "$OUT_DIR/classes"
rm -f "$OUT_DIR/manifest.txt"

echo "All adventure modules compiled."
