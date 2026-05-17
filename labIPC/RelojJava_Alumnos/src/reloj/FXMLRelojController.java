package reloj;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLRelojController implements Initializable {

    @FXML private Label  labelReloj;
    @FXML private Button btnIniciar;
    @FXML private Button btnParar;

    /**
     * Servicio reutilizable (Service<Void>) que encapsula la tarea del reloj.
     * Cada vez que se inicia o reinicia, createTask() construye una nueva Task.
     */
    private Service<Void> relojService;

    // Formateador para mostrar HH:mm:ss
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss");

    // -----------------------------------------------------------------------
    // Inicialización
    // -----------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // --- Definición del Service -------------------------------------------
        relojService = new Service<Void>() {

            /**
             * createTask() se llama automáticamente cada vez que el servicio
             * arranca o se reinicia (start / restart).
             */
            @Override
            protected Task<Void> createTask() {

                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {

                        // Bucle principal: se ejecuta hasta que la tarea
                        // sea cancelada desde el hilo principal.
                        while (!isCancelled()) {

                            // Platform.runLater: actualiza la UI desde el
                            // hilo secundario de forma segura.
                            Platform.runLater(() -> {
                                labelReloj.setText(LocalTime.now().format(FORMATO));
                            });

                            try {
                                // Esperamos 1 segundo antes de refrescar de nuevo
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                // Si nos interrumpen durante el sleep y la
                                // tarea ha sido cancelada, salimos del bucle.
                                if (isCancelled()) {
                                    break;
                                }
                            }
                        }
                        return null; // Void → devolvemos null
                    }
                };
            }
        };

        // --- Bindings de los botones con el estado del servicio ---------------

        // "Parar"   solo habilitado mientras el servicio ESTÁ en ejecución
        btnParar.disableProperty().bind(
                Bindings.not(relojService.runningProperty()));

        // "Iniciar" solo habilitado mientras el servicio NO está en ejecución
        btnIniciar.disableProperty().bind(
                relojService.runningProperty());
    }

    // -----------------------------------------------------------------------
    // Manejadores de botones
    // -----------------------------------------------------------------------

    /**
     * Botón Iniciar: arranca el servicio la primera vez, o lo reinicia si
     * fue parado previamente (cancel lo deja en estado CANCELLED).
     */
    @FXML
    void handleIniciar(ActionEvent event) {

        // La primera vez el servicio está en READY → start()
        // Las veces siguientes estará en CANCELLED → restart()
        if (relojService.getState() == Worker.State.READY) {
            relojService.start();
        } else {
            relojService.restart(); // cancel + reset + start internamente
        }
    }

    /**
     * Botón Parar: cancela el servicio (y la Task interna).
     * La Task comprueba isCancelled() en el bucle y sale de él.
     */
    @FXML
    void handleParar(ActionEvent event) {
        relojService.cancel();
    }
}
