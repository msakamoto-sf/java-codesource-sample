@echo off
rem This is free and unencumbered software released into the public domain.
rem Anyone is free to copy, modify, publish, use, compile, sell, or
rem distribute this software, either in source code form or as a compiled
rem binary, for any purpose, commercial or non-commercial, and by any
rem means.

pushd libfoo
javac foo\*.java
javac foo\d1\*.java
javac foo\d2\*.java
popd
jar cvf libfoo.jar -C libfoo foo

pushd libbar
javac bar\*.java
javac bar\d1\*.java
javac bar\d2\*.java
popd
jar cvf libbar.jar -C libbar bar

pushd main
javac -cp ..\libfoo;..\libbar testpkg\*.java
javac -cp ..\libfoo;..\libbar testpkg\d1\*.java
javac -cp ..\libfoo;..\libbar testpkg\d2\*.java
popd
jar cvf main.jar -C main testpkg

echo.
echo "===> execute testpkg.Main from filesystem"
java -cp .\libfoo;.\libbar;.\main testpkg.Main

echo.
echo "===> execute testpkg.Main from jar"
java -cp libfoo.jar;libbar.jar;main.jar testpkg.Main

