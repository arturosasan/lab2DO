#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
int main(void)
{ printf("Proceso %ld crea otro proceso\n", (long)getpid());
fork();
printf("Proceso %ld con padre %ld\n",(long)getpid(),(long)getppid());
sleep(5);
return 0;
}
