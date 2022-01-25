#!/bin/sh

if [ ! -d "builds/lists" ]
then
	mkdir "builds/lists"
	echo "made lists directory"
fi

javac -d builds @sources.txt

echo "Manifest-Version: 1.0" > ./builds/manifest.mf
echo "Main-Class: Main" >> ./builds/manifest.mf

jar cfm ./builds/PCPartList.jar ./builds/manifest.mf *.class

sleep 0.5