echo off

cd %~dp0

rmdir /S /Q "build"

cd "code"

call gradle desktop:dist

cd "../"

call perl chain/pak.pl C:/Users/Dev/Documents/GitHub/woa2016/pack C:/Users/Dev/Documents/GitHub/woa2016/build/runic
call perl chain/pak.pl C:/Users/Dev/Documents/GitHub/woa2016/code/desktop/build/libs C:/Users/Dev/Documents/GitHub/woa2016/build

rmdir /S /Q "tmp"
rmdir /S /Q "dependency-cache"
rmdir /S /Q "classes"
rmdir /S /Q "libs"

cd "build"

ren "desktop-1.0.jar" "runic.jar"

cd "runic"

move "thirdparty.txt" "../"