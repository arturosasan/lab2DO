#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>
int main(void)
{
int status;
pid_t pid=fork();
switch (pid) {
case -1:
printf("No se ha podido crear el proceso hijo\n");
break;
case 0:
printf("Soy el hijo con PID %ld y mi padre es %ld\n", (long)getpid(), (long)getppid());
sleep(20);
printf("Ya he terminado\n");
break;
default:
printf("Soy el padre con PID %ld y mi hijo es %d. Esperándolo ...\n", (long)getpid(), pid);
if (wait(&status)!=-1)
printf("Mi hijo ha terminado normalmente\n");
}
return 0;
}
