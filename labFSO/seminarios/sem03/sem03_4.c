#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
int main(void)
{
pid_t pid=fork();
switch (pid) {
case -1:
 printf("No se ha podido crear el proceso hijo\n");
 break;
case 0:
 printf("Soy el hijo con PID %ld y PPID %ld\n", (long)getpid(), (long)getppid());
 break;
default:
 printf("Soy el padre con PID %ld y mi hijo es %d\n", (long)getpid(), pid);
}
sleep(5);
return 0;
}
