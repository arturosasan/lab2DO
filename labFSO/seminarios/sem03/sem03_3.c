#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
int main() {
pid_t val;
int var = 0;
printf("PID antesfork(): %d\n", (long) getpid());
val=fork();
if (val > 0) {
printf("Padre PID: %d\n", (long) getpid());
var++;
} else {
printf("HijoPID: %d\n", (long) getpid());
}
printf("Proceso [%d]-> var=%d\n", (long) getpid(), var);
return 0;
}
