// CSD feb 2013 Juansa Sendra

public class Pool4 extends Pool { //kids cannot enter if there are instructors waiting to exit
    private int nanos = 0, instructores = 0;
    private int max_kids = 0, max_cap = 0;
    private int inst_rest = 0;
    public void init(int ki, int cap) {
        max_kids = ki;
        max_cap = cap;
    }

    public synchronized void kidSwims() throws InterruptedException {
        while (instructores == 0 
            || nanos >= max_kids * instructores  
            || (instructores * nanos) >= max_cap
            || inst_rest > 0){
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

    public synchronized void instructorSwims() throws InterruptedException {
        while ((instructores + nanos) >= max_cap) {
            log.waitingToSwim();
            wait();
        }
        ++instructores;
        notifyAll();
        log.swimming();
    }

    public synchronized void instructorRests() throws InterruptedException {
        while (nanos > max_kids * (instructores - 1)) {
            log.waitingToRest();
            ++inst_rest;
            wait();   
        }
        --instructores;
        log.resting();
    }
}
