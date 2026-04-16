/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class VistaPersonaController implements Initializable {

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    
    
    private boolean OkPressed = false;
    
    private Persona persona;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Este initialize me la pela porque ya está creado e inicializado en el otro (VistaPersonaController_1.java)
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        
        String nombre = nombreTextField.getText();          // Nombre persona a insertar
        String apellidos = apellidosTextField.getText();    // Apellidos ""
        if(!nombre.equals("") && !apellidos.equals("")){    // Vacío?
            OkPressed = true;                               // Reactivamos botón
            nombreTextField.getScene().getWindow().hide();  // Quito esta ventana
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        nombreTextField.getScene().getWindow().hide();      // Quito la ventana
    }

    public boolean isOkPressed(){
        return OkPressed;               
    }
    
    public Persona getPersona(){
        return persona;
    }
    
    public void initPersona(Persona p){                 
        persona = p;
        nombreTextField.setText(p.getNombre());
        apellidosTextField.setText(p.getApellidos());
    }
}
