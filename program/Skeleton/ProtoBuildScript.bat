mkdir ./bin
javac -d ./bin src/main/*.java
jar cfve Proto.jar main.Main -C ./bin/ .
