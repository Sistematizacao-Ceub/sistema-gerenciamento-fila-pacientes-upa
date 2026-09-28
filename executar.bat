@echo off
REM Compila e executa SEM Maven (Windows). Requer JDK 17+.
REM -sourcepath faz o javac compilar App.java e TODAS as classes que ele usa.
chcp 65001 >nul
cd /d "%~dp0"
if exist out rmdir /s /q out
javac -encoding UTF-8 -d out -sourcepath src\main\java src\main\java\br\ceub\poo\upa\App.java
if errorlevel 1 (
  echo.
  echo ERRO DE COMPILACAO. Corrija os erros acima.
  pause
  exit /b 1
)
java -Dstdout.encoding=UTF-8 -cp out br.ceub.poo.upa.App
pause
