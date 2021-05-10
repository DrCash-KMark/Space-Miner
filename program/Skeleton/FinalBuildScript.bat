mkdir ./bin
javac -d ./bin src/main/*.java
jar cfve Final.jar main.Main -C ./bin/ .
