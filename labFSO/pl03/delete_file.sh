#!/bin/bash
if test -d $1
then
	echo "Es un directorio"
fi
if test -f $1
then
	rm $1
	echo "El archivo '$1' pasó a mejor vida"
else
	echo "El archivo '$1' no existe"
fi
