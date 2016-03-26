#!/bin/sh

#
# This is free and unencumbered software released into the public domain.
#
# Anyone is free to copy, modify, publish, use, compile, sell, or
# distribute this software, either in source code form or as a compiled
# binary, for any purpose, commercial or non-commercial, and by any
# means.
#

/bin/rm -f **/*.class
/bin/rm -f *.jar

pushd libfoo
javac **/*.java
popd
jar cvf libfoo.jar -C libfoo foo

pushd libbar
javac **/*.java
popd
jar cvf libbar.jar -C libbar bar

pushd main
javac -cp ../libfoo:../libbar **/*.java
popd
jar cvf main.jar -C main testpkg

echo ""
echo "===> execute testpkg.Main from filesystem"
java -cp ./libfoo:./libbar:./main testpkg.Main

echo ""
echo "===> execute testpkg.Main from jar"
java -cp libfoo.jar:libbar.jar:main.jar testpkg.Main

