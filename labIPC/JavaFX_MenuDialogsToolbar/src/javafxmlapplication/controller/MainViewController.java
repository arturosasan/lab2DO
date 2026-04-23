/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.controller;

import java.net.URL;
import java.util.*;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author sovacu
 */
public class MainViewController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private RadioMenuItem ebayOpt;
    @FXML
    private RadioMenuItem amzOpt;
    @FXML
    private Label state;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exitMenu(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Vas a salir del programa");
        alert.setContentText("¿Seguro que quieres salir?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) { // Si se muestra la ventana && el usuario le de a "Aceptar" / "Ok"
            Platform.exit();
        }
    }
    @FXML
    private void amazonClick(ActionEvent event) {
            if (!amzOpt.isSelected()) {
                Alert alert = new Alert(AlertType.INFORMATION); // Al poner information solo aparece "Aceptar" como único botón
                alert.setTitle("Error en la selección");
                alert.setHeaderText("No se puede comprar en Amazon");
                alert.setContentText("Por favor, cambie la seleccion actual en el menú Opciones");
                
                Optional<ButtonType> result= alert.showAndWait();
            } else {
                WebView webView = new WebView();
                webView.getEngine().load("https://www.amazon.es");
                borderPane.setCenter(webView);
                // LIMPIAR LABEL DE ABAJO
                state.setText("");
            }
    } 
   
    @FXML
    private void ebayClick(ActionEvent event) {
        if (!ebayOpt.isSelected()) {
                Alert alert = new Alert(AlertType.INFORMATION); // Al poner information solo aparece "Aceptar" como único botón
                alert.setTitle("Error en la selección");
                alert.setHeaderText("No se puede comprar en Ebay");
                alert.setContentText("Por favor, cambie la seleccion actual en el menú Opciones");
                Optional<ButtonType> result= alert.showAndWait();
            } else {
                WebView webView = new WebView();
                webView.getEngine().load("https://www.ebay.es");
                borderPane.setCenter(webView);
                
                // LIMPIAR LABEL DE ABAJO
                state.setText("");
                
        }
    }

    @FXML
    private void clickBlogger(ActionEvent event) {
        
        List<String> blogs = new ArrayList<>();
        blogs.add("El blog de Athos"); blogs.add("El blog de Porthos"); blogs.add("El blog de Aramis");
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("El blog de Athos", blogs); // Por defecto
        dialog.setTitle("Selecciona un blog");
        dialog.setHeaderText("¿Qúe blog quieres visitar?");
        dialog.setContentText("Elige:");
        
        Optional<String> result= dialog.showAndWait();
        result.ifPresent(blogElegido -> state.setText("Visitando " + blogElegido));
        
    }

    @FXML
    private void clickCaralibro(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("Pepe"); // Por defecto
        dialog.setTitle("Introduce tu nombre");
        dialog.setHeaderText("¿Con que usuario quieres escribir en Facebook?");
        dialog.setContentText("Introduce tu nombre:");
        
        Optional<String> result= dialog.showAndWait();
        result.ifPresent(usuarioElegido -> state.setText("Mensaje enviado como " + usuarioElegido));
    }
}
