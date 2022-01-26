#!/bin/sh

#GNU Public Licence v3, 2022, Ruby-Dragon

if [ ! -d "builds/lists" ]
then
	mkdir "builds/lists"
	echo "made lists directory"
fi

javac -d builds @sources.txt

echo "Manifest-Version: 1.0" > ./builds/manifest.mf
echo "Main-Class: Main" >> ./builds/manifest.mf

rm ./builds/PCPartList.jar

cd builds

jar cfm PCPartList.jar manifest.mf *.class

sleep 0.5