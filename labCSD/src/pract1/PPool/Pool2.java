// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    private int nanos = 0, instructores = 0;
    private int max_kids = 0;
    public void init(int ki, int cap) {
        max_kids = ki;
    }

    public synchronized void kidSwims() throws InterruptedException {
        while (instructores == 0 
            || nanos >= max_kids * instructores) {
            log.waitingToSwim();
            wait();
        }
        ++nanos;
        log.swimming();
    }

    public synchronized void kidRests() {
        --nanos;
        notifyAll();
        log.resting();
    }

    public synchronized void instructorSwims() {
        ++instructores;
        notifyAll();
        log.swimming();
    }

    public synchronized void instructorRests() throws InterruptedException {
        while (nanos > max_kids * (instructores - 1)) {
            log.waitingToRest();
            wait();   
        }
        --instructores;
        log.resting();
    }
}
