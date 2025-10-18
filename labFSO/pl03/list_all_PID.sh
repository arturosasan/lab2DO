#!/bin/bash
echo Lista de procesos:
for i in /proc/[0-9]*
do
    pid=$(basename "$i")
    echo "$pid"
done

