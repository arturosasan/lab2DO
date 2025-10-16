#include <stdio.h>

int main(int argc, char *argv[]) {
    for (int i = 1; i < argc; i++) {
	    printf("Argumento %d es ",i);
            switch (argv[i][1]) {
                case 'c':
                    printf("Compilar \n");
                    break;
                case 'e':
                    printf("Ejecutar \n");
                    break;
                case 'i':
                    printf("Incluir %s\n",argv[++i]);
                    break;
            	default:
            	printf("Opción inválida");
      	}
    }
}

