#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h> // sleep

void *thread_fn(void *arg) {
    int id = *(int*)arg;
    pthread_t self = pthread_self();
    printf("Hilo %d: arrancando (pthread_t=%llu)\n", id, (unsigned long long) self);
    sleep(1 + id); // simula trabajo
    long *ret = malloc(sizeof(long));
    *ret = 100L + id;
    printf("Hilo %d: terminando, devuelve %ld\n", id, *ret);
    // devuelve un puntero al heap (para mostrar pthread_exit)
    pthread_exit((void*) ret);
    // no se alcanza código después de pthread_exit
    return NULL;
}

int main(void) {
    pthread_attr_t attr;
    pthread_t t_joinable, t_detached;
    int arg1 = 1, arg2 = 2;
    int rc;

    // 1) inicializar atributos
    rc = pthread_attr_init(&attr);
    if (rc != 0) { perror("attr_init"); exit(1); }

    // 2) comprobar estado por defecto (joinable)
    int state;
    pthread_attr_getdetachstate(&attr, &state);
    printf("Estado inicial attr: %s\n",
           state == PTHREAD_CREATE_DETACHED ? "DETACHED" : "JOINABLE");

    // 3) crear hilo joinable con attr por defecto (joinable)
    rc = pthread_create(&t_joinable, &attr, thread_fn, &arg1);
    if (rc != 0) { perror("create joinable"); exit(1); }

    // 4) cambiar atributo a detached
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
    pthread_attr_getdetachstate(&attr, &state);
    printf("Estado attr después de set: %s\n",
           state == PTHREAD_CREATE_DETACHED ? "DETACHED" : "JOINABLE");

    // 5) crear hilo detached usando el attr modificado
    rc = pthread_create(&t_detached, &attr, thread_fn, &arg2);
    if (rc != 0) { perror("create detached"); exit(1); }

    // 6) destruir el objeto attr (ya no se necesita)
    pthread_attr_destroy(&attr);

    // 7) intentar hacer join sobre el hilo joinable: sí
    void *res1;
    rc = pthread_join(t_joinable, &res1);
    if (rc != 0) {
        perror("join joinable");
    } else {
        long *val = (long*) res1;
        printf("Main: join sobre joinable devolvió %ld\n", *val);
        free(val);
    }

    // 8) no hacemos join sobre t_detached (sería error). Dormimos un poco para que termine
    printf("Main: no voy a hacer join sobre el hilo detached.\n");
    sleep(3); // esperar para que el hilo detached imprima su mensaje antes de salir

    printf("Main: fin. Salimos.\n");
    return 0; // si hubiésemos querido que otros hilos siguieran vivos, podríamos usar pthread_exit(NULL);
}
