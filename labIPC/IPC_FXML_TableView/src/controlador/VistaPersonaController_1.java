/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsole
 */
public class VistaPersonaController_1 implements Initializable {

    
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private ComboBox<String> imagenCombo; // IMPORTANTE, ESTO ES EL COMBOBOX / DROPDOWN DE LAS IMAGENES,
                                          // AQUÍ HAY QUE METER LAS IMAGENES AL INICIALIZAR EL PROYECTO
   
    private boolean okPressed = false;
    private Persona persona;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imagenCombo.getItems().addAll("/resources/images/Lloroso.png", "/resources/images/Pregunta.png", "/resources/images/Sonriente.png"); // AÑADIR LOS RECURSOS PARA QUE SE MUESTREN EN LAS OPCIONES DEL COMBOBOX
        imagenCombo.setCellFactory(c -> new ImagenTabCell());   // PARA QUE SE MUESTREN IMAGENES Y NO STRINGS
        imagenCombo.setButtonCell(new ImagenTabCell());         // "" 
       
    }      
    public void initPersona(Persona p){ // METODO INICIALIZADOR DE LAS PERSONAS CREADAS AL EJECUTAR EL TRABAJO (Y PARA LAS QUE SE CREEN)
        persona = p;
        nombreTextField.setText(p.getNombre());
        apellidosTextField.setText(p.getApellidos());
        String imagenPath = p.getImagenPath();
        if (imagenPath != null && !imagenPath.isEmpty()) {
            imagenCombo.getSelectionModel().select(imagenPath);
        }
       
    }
    public boolean isOkPressed(){
        return okPressed;
    }
    public Persona getPersona(){
        return persona;
    }
    
    @FXML
    private void aceptar2(ActionEvent event) { // AUNQUE PONGA QUE NO SE USE ES PORQUE ESTÁ EN LA VENTANA DEL NUEVO BOTON
        String nombre = nombreTextField.getText();                  //
        String apellidos = apellidosTextField.getText();            // GETERS PARA LA VENTANA DE AÑADIR CON EL NUEVO COMBOBOX 
        String imagen = imagenCombo.getValue();                     //

    if (!nombre.isEmpty() && !apellidos.isEmpty() && imagen != null && !imagen.isEmpty()) { // COMPROBACIÓN DE LOS CAMPOS
        persona = new Persona(nombre, apellidos, imagen);  // CONSTRUCTOR DE PERSONA
        okPressed = true;                                  // BOTÓN OK  
        nombreTextField.getScene().getWindow().hide();     // ESCONDER VENTANA
    }
        
    }

    @FXML
    private void cancelar2(ActionEvent event ) { // LO MISMO QUE ACEPTAR2
        nombreTextField.getScene().getWindow().hide();  // ESCONDER VENTANA
    }
    
    class ImagenTabCell extends ComboBoxListCell<String> { // COPIA Y PEGA DEL PDF, PARA QUE SE VEAN LAS IMAGENES
        private ImageView view = new ImageView();
        private Image imagen;

        @Override
        public void updateItem(String t, boolean bln) {
            super.updateItem(t, bln); 
            if (t == null || bln) {
                setText(null);
                setGraphic(null);
            } else {
                imagen = new Image(t,25,25,true,true);
                view.setImage(imagen);
                setGraphic(view);
                setText(null);
            }
        }
    }
    
    
}
