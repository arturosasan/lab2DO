package pract3;

import java.util.concurrent.locks.*;

public class Terrain1 implements Terrain {
    Viewer v;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    public  Terrain1 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
    }

    public synchronized void hi (int a) {
        lock.lock();
        try {
            v.hi(a);
        } finally {
            lock.unlock();
        }
    }

    public synchronized void bye (int a) {
        lock.lock();
        try {
            v.bye(a);
            condition.signalAll(); // EQUIVALENTE A notifyAll() EN LA LIBRERÍA DE LOCKS
        } finally {
            lock.unlock();
        }
    }

    public synchronized void move (int a) throws InterruptedException {
        lock.lock();
        try {
            v.turn(a);
            Pos dest=v.dest(a);
            while (v.occupied(dest)) {
                condition.await(); // EQUIVALENTE A wait()
                v.retry(a);
            }
            v.go(a);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}
