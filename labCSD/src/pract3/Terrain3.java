package pract3;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class Terrain3 implements Terrain {
    Viewer v;
    private final Lock lock = new ReentrantLock();
    private final Condition[][] conditions;
    private final int tamaño;
    private final boolean[][] celdaO;


    public Terrain3 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        tamaño = t;
        conditions = new Condition[tamaño][tamaño];
        celdaO = new boolean[tamaño][tamaño];

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                conditions[i][j] = lock.newCondition();
            }
        }
    }

    public synchronized void hi (int a) {
        lock.lock();
        try {
            v.hi(a);
            Pos pAux = v.getPos(a);
            celdaO[pAux.x][pAux.y] = true;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void bye (int a) {
        lock.lock();
        try {
            v.bye(a);
            Pos pAux = v.getPos(a);
            celdaO[pAux.x][pAux.y] = false;
            conditions[pAux.x][pAux.y].signalAll(); // EQUIVALENTE A notifyAll() EN LA LIBRERÍA DE LOCKS
        } finally {
            lock.unlock();
        }
    }

    public synchronized void move (int a) throws InterruptedException {
        lock.lock();
        try {
            v.turn(a);
            Pos dest = v.dest(a);
            Pos act = v.getPos(a);
            while (celdaO[dest.x][dest.y]) { // MIENTRAS QUE HAYA UNA HORMIGA AHÍ
                if (!conditions[dest.x][dest.y].await(300, TimeUnit.MILLISECONDS)) { // SI SE ESPERA + DE 300 MILISEGUNDOS
                    v.chgDir(a);
                    dest = v.dest(a);
                }
                v.retry(a);
            }
            celdaO[act.x][act.y] = false;
            celdaO[dest.x][dest.y] = true;
            v.go(a);
            conditions[act.x][act.y].signalAll();
        } finally {
            lock.unlock();
        }
    }
}
