#!/usr/bin/env bash
# Compila e executa SEM Maven (Linux/macOS). Requer JDK 17+.
# -sourcepath faz o javac compilar App.java e TODAS as classes que ele usa.
set -e
cd "$(dirname "$0")"
rm -rf out
javac -encoding UTF-8 -d out -sourcepath src/main/java src/main/java/br/ceub/poo/upa/App.java
java -Dstdout.encoding=UTF-8 -cp out br.ceub.poo.upa.App
