// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool { //no kids alone
    private int nanos = 0, instructores = 0;
    private int ki, cap;
    public void init(int ki, int cap) {
        this.ki = ki;
        this.cap = cap;
    }
    
    public synchronized void kidSwims() throws InterruptedException{
        while (instructores == 0) {
            log.waitingToSwim();
            wait();
        }
        nanos++;
        log.swimming();
    }
    public synchronized void kidRests() {
        this.nanos--;
        log.resting();
        notifyAll();
    }
    public synchronized void instructorSwims() {
        instructores++;
        log.swimming();
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException{
        while (nanos > 0) {
            log.waitingToRest();
            if (instructores > 1) {
                break;   
            } else {
                wait();   
            }
        }
        instructores--;
        log.resting();
    }
}
