#include <stdio.h>
#include <sys/types.h>
int main(void)
{
int status;
pid_t pid=fork();
char* argumentos [] = { "ls", "-l", 0 };
 char* orden [] = { "ls", "-l", 0 };
switch (pid) {
case -1:
printf("No se ha podido crear el proceso hijo\n");
break;
case 0:
printf("Soy el hijo con PID %ld y voy a listar el directorio\n", (long)getpid());
if (execvp("ls",argumentos)==-1){
printf("Error en exec\n");
exit(0);
}
break;
default:
printf("Soy el padre con PID %ld y mi hijo es %d.\n", (long)getpid(), pid);
}
return 0;
}
