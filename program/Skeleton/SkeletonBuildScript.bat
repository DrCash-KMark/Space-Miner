mkdir ./bin
javac -d ./bin src/main/*.java
jar cfve Skeleton.jar main.Main -C ./bin/ .
