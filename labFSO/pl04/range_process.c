#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define NPROCESOS 5

int main(int argc, char *argv[]) {
    pid_t pid[NPROCESOS];
    int i, status;

    for (i=0; i<NPROCESOS; i++) {
        pid[i]=fork();
        if(pid[i] == 0) {
            printf("Hijo creado en iteración=%d \n",i);
            exit(i);
        }
    }
    sleep(10);
    exit(0);
}
