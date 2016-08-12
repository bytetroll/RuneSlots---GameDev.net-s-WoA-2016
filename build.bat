echo off

cd %~dp0

rmdir /S /Q "build"

cd "code"

call gradle desktop:dist

cd "../"

call perl chain/pak.pl E:/Work/Projects/woa2016/pack E:/Work/Projects/woa2016/build/runic
call perl chain/pak.pl E:/Work/Projects/woa2016/code/desktop/build/libs E:/Work/Projects/woa2016/build

rmdir /S /Q "tmp"
rmdir /S /Q "dependency-cache"
rmdir /S /Q "classes"
rmdir /S /Q "libs"

cd "build"

ren "desktop-1.0.jar" "runic.jar"

cd "runic"

move "thirdparty.txt" "../"