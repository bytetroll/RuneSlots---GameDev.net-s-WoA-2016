echo Y | rm -r build

cd "code"

gradle desktop:dist

cd "../"

perl ./chain/pak.pl /Volumes/exec/work/projects/woa2016/pack /Volumes/exec/work/projects/woa2016/build/woa2016
perl ./chain/pak.pl /Volumes/exec/work/projects/woa2016/code/desktop/build/libs /Volumes/exec/work/projects/woa2016/build

echo Y | rm -r tmp
echo Y | rm -r dependency-cache
echo Y | rm -r classes
echo Y | rm -r libs

cd "build"

mv "desktop-1.0.jar" "woa2016.jar"