#!/bin/bash

SOURCE=$1
DESTINATION=$2

for file in $(find $SOURCE -name "*.js" -not -name "*.min.js"); do
	fileNoPath="${file##*/}" 
        echo "Now minifyin $fileNoPath"
	uglifyjs $file -o $DESTINATION/$fileNoPath -c -m
done
