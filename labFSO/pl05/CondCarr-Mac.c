/*                                                                      */
/*  CondCarr.c   - VERSION MAC-ARM                                                       */
/*  Observación de condiciones de carrera y soluciones                  */
/*  Fundamentos de los Sistemas Operativos  (sesión 5)                  */
/*  Grupo de Sistemas Operativos DISCA (UPV)                            */
/*  Última revisión: 08/11/2024                                         */
/*                                                                      */


#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <semaphore.h>
#include <unistd.h>
#include <math.h>


/*
 REPETICIONES : Numero de veces que se suma/resta 1 a V
*/
#define REPETICIONES      20000000

/*
   VARIABLES GLOBALES (COMPARTIDAS) 
*/

long int V = 100;      // Valor inicial

/* 
   FUNCIONES AUXILIARES 
   test_and_set(int * objetivo) : devuelve 1 (cierto) si llave esta siendo utilizada, 
                                  devuelve 0 (falso) si llave esta libre.
 * Funciones test_and_set y release usando un "spinlock" 
 */
int test_and_set(int *objetivo) {
    return (__sync_lock_test_and_set(objetivo, 1));
}
////
void release(int *objetivo) { 
    __sync_lock_release(objetivo);
}

void SeccionRestante(int V)
{
    int i;
    long tot;
  
    tot = 0;
    for (i = 0; i < 300; i++) {
       tot = tot+V;
    }
}


/* 
   FUNCIONES QUE EJECUTAN LAS TAREAS o THREADS
   - Agrega : Ejecuta un bucle donde incrementa la variable V 
   - Resta  : Ejecuta un bucle donde decrementa la variable V
   - Inspecciona : Imprime cada segundo el valor de V
*/

void *agrega (void *argumento) {

  long int cont;
       
  for (cont = 0; cont < REPETICIONES; cont = cont + 1) {
  
      V = V + 1;
    
      SeccionRestante(V);

  }
 
  printf("-------> Fin AGREGA (V = %ld)\n", V);
  pthread_exit(0);
}

void *resta (void *argumento) {

  long int cont;
  
  for (cont = 0; cont < REPETICIONES; cont = cont + 1) {
        
        V = V - 1;
    
        SeccionRestante(V);
    
  }

  printf("-------> Fin RESTA  (V = %ld)\n", V);
  pthread_exit(0);
}

void *inspecciona (void *argumento) {
 
  for (;;) {
    usleep(200000);
    fprintf(stderr, "Inspeccion: Valor actual de V = %ld\n", V);
  } 
}

//*PROGRAMA PRINCIPAL  

int main (int argc, char *argv[]) {
  //Declaracion de  variables 
    pthread_t hiloSuma, hiloResta, hiloInspeccion;
    pthread_attr_t attr;   

  // Inicilizacion de los atributos de los hilos (por defecto)
    pthread_attr_init(&attr);
    
  // EJERCICIO: Cree los tres hilos propuestos con dichos atributos 

  // EJERCICIO: Hilo principal debe espera a que las
  // tareas "agrega" y "resta" finalicen 

    
  // Fin del programa principal
  fprintf(stderr, "-------> VALOR FINAL: V = %ld\n\n", V);
  exit(0);
}
