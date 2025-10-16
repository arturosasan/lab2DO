#include <stdio.h> 

int a = 0; /* variable global */ 


// Esta función incrementa el valor de la variable global a en 1
void inc_a(int a) {
    a++;
}

// Esta function devuelve el valor anterior 
// y guarda el Nuevo valor v
int valor_anterior(int v) {
      int temp;
      int s;
	  int b;
      temp = s;
      s = v;
      return b;
}

int main() {
	int b = 2; /* variable local */
	inc_a(b);
	valor_anterior(a);
	printf("a= %d, b= %d\n", a, b);
	a++;
	b++;
	inc_a(a);
	b = valor_anterior(b);
	printf("a= %d, b= %d\n", a, b);
} 

