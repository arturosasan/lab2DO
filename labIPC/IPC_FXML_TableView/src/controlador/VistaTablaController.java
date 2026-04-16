/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class VistaTablaController implements Initializable {

    private ObservableList<Persona> datos = null; // Colección vinculada a la vista.
    @FXML
    private TableColumn<Persona, String> nombreColumn;
    @FXML
    private TableColumn<Persona, String> apellidosColumn;
    @FXML
    private TableView<Persona> personasTableV;
    @FXML
    private TableColumn<Persona, String> imagenPathColumn;

    private void inicializaModelo() {
       
        //Ajustar las columnas al modelo
        nombreColumn.setCellValueFactory((personaFila)->{
            return personaFila.getValue().NombreProperty();
        });
        apellidosColumn.setCellValueFactory(personaFila -> personaFila.getValue().ApellidosProperty());
        //imagenPathColumn.setCellValueFactory(personaFila -> new SimpleStringProperty(personaFila.getValue().getImagenPath()));
        imagenPathColumn.setCellFactory(c -> new ImagenTabCell()); // STRING -> IMG DE LA IMAGEN SEL. PARA QUE SE MUESTRE, NO LO DE ARRIBA
        ArrayList<Persona> misdatos = new ArrayList<Persona>();    // CREACIÓN DEL ARRAY QUE ALMACENA A TODAS LAS PERSONAS
        misdatos.add(new Persona("Pepe", "García", "/resources/images/Sonriente.png")); // PERSONAS QUE APARECEN POR DEFECTO
        misdatos.add(new Persona("María", "Pérez", "/resources/images/Lloroso.png"));   // ""
        
        
        
        imagenPathColumn.setCellValueFactory(personaFila -> new SimpleStringProperty(personaFila.getValue().getImagenPath()));
        datos = FXCollections.observableList(misdatos);
        personasTableV.setItems(datos);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        inicializaModelo();
       
    }
 
    @FXML
    private void añadir(ActionEvent event) throws IOException {
        //FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona.fxml"));
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona_1.fxml")); // SIEMPRE QUE ABRIMOS UNA NUEVA VENTANA
        Stage stage = miCargador.load();                                                              // SE PONEN ESTAS DOS LÍNEAS DE CÓDIGO  
        
        VistaPersonaController_1 controlPersona = miCargador.getController();
        
        stage.setTitle("Añadir persona");                               // 
        stage.initModality(Modality.APPLICATION_MODAL);                 // ATRIBUTOS DE LA VENTANA EMERGENTE
        stage.showAndWait();                                            //
        if(controlPersona.isOkPressed()){
            Persona p = controlPersona.getPersona();
            datos.add(p);
            
        }
        
    }

    @FXML
    private void modificar(ActionEvent event) throws IOException {
        //FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona.fxml"));
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona_1.fxml")); // SIEMPRE QUE ABRIMOS UNA NUEVA VENTANA
        Stage stage = miCargador.load();                                                              // SE PONEN ESTAS DOS LÍNEAS DE CÓDIGO
        
        VistaPersonaController_1 controlPersona = miCargador.getController();                       // 
        Persona persona = personasTableV.getSelectionModel().getSelectedItem();                     // PARA SABER DONDE ESTAMOS Y QUE C*** HAY QUE HACER
        controlPersona.initPersona(persona);                                                        // 
        
        stage.setTitle("Modificar persona");                
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        if(controlPersona.isOkPressed() && controlPersona.getPersona() != null){
            int indice = datos.indexOf(persona);        
             Persona p = controlPersona.getPersona();
             datos.set(indice, p);
        }
    }

    @FXML
    private void borrar(ActionEvent event){
        datos.remove(personasTableV.getSelectionModel().getSelectedIndex());
    }

class ImagenTabCell extends TableCell<Persona, String> {
    private ImageView view = new ImageView();
    private Image imagen;
    @Override
    protected void updateItem(String t, boolean bln) {
        super.updateItem(t, bln);
        if (t == null || bln) {
            setText(null);
            setGraphic(null);
        } else {
            imagen = new Image(t, 25, 25, true, true);
            view.setImage(imagen);
            setGraphic(view);
            }
    }
    }

}
