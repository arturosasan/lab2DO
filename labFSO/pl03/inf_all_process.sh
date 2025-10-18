#!/bin/bash
echo Lista de procesos:
echo -e "PID\tPPID\tSTATE\tCMD"

for i in /proc/[0-9]*
do
    pid_process=$(basename "$i")
    ppid=$(awk '/PPid/ {print $2}' "$i/status" 2>/dev/null)
    estado=$(awk '/State/ {print $2}' "$i/status" 2>/dev/null)
    cmd=$(tr -d '\0' <"$i/cmdline" 2>/dev/null)


    echo -e "$pid_process\t$ppid\t$estado\t$cmd"
done
