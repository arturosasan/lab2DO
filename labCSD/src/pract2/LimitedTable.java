// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    public LimitedTable(StateManager state) {super(state);}
    
    int filo = 0;
    
    public synchronized void enter (int id) throws InterruptedException {
        while (filo >= 4) {
            state.wenter(id);
            wait();
        }
        filo++;
        state.enter(id);
    }
    
    public synchronized void exit (int id) {
        filo--;
        state.exit(id);
        notifyAll();
    }
}