#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#define NPROCESOS 4
int main(void)
{ pid_t pid;
 int i;
 for (i=0; i<NPROCESOS; i++) {
pid=fork();
if (pid!=0) break;
printf("Soy el hijo con PID %ld con padre %ld\n",
 (long)getpid(), (long)getppid());
 }
 sleep(5);
 return 0;
}
