#include <stdio.h>
#include <unistd.h>
int main(void)
{
printf("\nID del proceso: %ld\n", (long)getpid());
printf("ID del padre: %ld\n", (long)getppid());
while(1);
return 0;
}
