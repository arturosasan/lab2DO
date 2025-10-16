#include <stdio.h>

#define N 10

int Number;

int many() {
    int i;

    printf("Mi loco, cuantos nº quieres mostrar?");
    scanf("%d", &Number);

    printf(" The first %d natural numbers are: \n", Number);
    for (i=0; i<Number; i++) {
        printf("    %d \n",i);
    }

    printf("BYE\n");
    return 0;
}
