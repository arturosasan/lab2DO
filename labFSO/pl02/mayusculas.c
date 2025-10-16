#include <stdio.h>
#define TAM_CADENA 200

int main() {
    // Puntero a caracter para copiar las cadenas
    
    char *p1, *p2;
    
    // A) Definir las variables cadena y cadena2
    char cadena[TAM_CADENA];
    char cadena2[TAM_CADENA];

    // B) Leer cadena de consola
    printf("Introduce una cadena: ");
    scanf(" %[^\n]", cadena);

    // C) Convertir a mayúsculas
    p1 = cadena;
    p2 = cadena2;

    while (*p1 != '\0') {
        if (*p1 >= 'a' && *p1 <= 'z') { // Si es minúscula, pasa a mayúscula
            *p2 = *p1 - 32;
        } else { // Si es cualquier otro, se queda igual
            *p2 = *p1;
        }
        p1++;
        p2++;
    }

    // Acordarse de poner el carácter nulo final en cadena2
    *p2 = '\0';

    // D) Sacar por consola la cadena2.
    printf("Cadena en mayúsculas: %s\n", cadena2);

    return 0;
}
