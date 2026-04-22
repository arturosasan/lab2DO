package aplicaciones.hospital;

public class ServidorColaPrioridadPlus extends ServidorColaPrioridad implements ServidorQuirofanoPlus {
    private int talla;

    public int numPacientes() {
        return talla;
    }

    public Paciente transferirPaciente() {
        Paciente aTransferir = cP.recuperarMin();
        cP.eliminarMin();
        talla--;
        return aTransferir;
    }

    public void distribuirPacientes(ServidorQuirofanoPlus s) {
        Paciente[] elArray = new Paciente[talla];

        for (int i = 0; i < elArray.length; i++) {
            elArray[i] = this.transferirPaciente();
        }

        for (int j = 0; j < elArray.length; j++) {
            elArray[j] = s.transferirPaciente();
        }
    }

    @Override
    public void insertarEnEspera(Paciente p) {
        talla++;
        super.insertarEnEspera(p);
    }

    @Override
    public Paciente operarPaciente(int h) {
        talla--;
        return super.operarPaciente(h);
    }
}
