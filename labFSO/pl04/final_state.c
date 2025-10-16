#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define NPROCESOS 4

int main(int argc, char *argv[]) {
    int i;
    int final_state;
    pid_t pid[NPROCESOS];
    pid_t val_return;
    for (i=0; i<NPROCESOS; i++)
    {val_return= fork();
        if (val_return==0)
        {printf("Son %ld created in iteration %d\n ",(long) getpid(),i);
        }
        else
        { /*father out of the loop for */
            printf("Father %ld, iteration %d\n ", (long)getpid(),i);
            printf("I have created a son %ld\n ",(long) val_return);
            break;
        }
    }
    /*wait all my sons and make exit*/
    while(wait(&final_state)>0) {
        printf("Father %ld iteration %d ",(long) getpid(),i);
        printf("My son said %d\n ",WEXITSTATUS(final_state));
        printf("My son said %d\n ", final_state/256);
    }
    exit(i);
}
