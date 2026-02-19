/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static javafxmlapplication.Utils.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    private Label labelMessage;
    @FXML
    private Circle bola;
    @FXML
    private GridPane celda;
    
    double x_ini;
    double y_ini;
    
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleKeyPressed(KeyEvent event) {
         // recupera la fila en la que se encuentra el nodo
         int row = GridPane.getRowIndex(bola);
         // recupera la columna en la que se encuentra el nodo
         int column = GridPane.getColumnIndex(bola);
         
        if (event.getCode() == KeyCode.UP) {
            row = row - 1;
            GridPane.setRowIndex(bola , columnNorm(celda, row));
        }
        if (event.getCode() == KeyCode.DOWN) {
            row = row + 1;
            GridPane.setRowIndex(bola , columnNorm(celda, row));
        }
        if (event.getCode() == KeyCode.LEFT) {
            column = column - 1;
            GridPane.setColumnIndex(bola, columnNorm(celda, column));
        }
        if (event.getCode() == KeyCode.RIGHT) {
            column = column + 1;
            GridPane.setColumnIndex(bola, columnNorm(celda, column));
        }
    }

    @FXML
    private void handleMousePressed(MouseEvent event) {
        x_ini = event.getSceneX();
        y_ini = event.getSceneY();
    }

    @FXML
    private void handleMouseDragged(MouseEvent event) {
        bola.setTranslateX(event.getSceneX() - x_ini);
        bola.setTranslateY(event.getSceneY() - y_ini);
    }

    @FXML
    private void handleMouseReleased(MouseEvent event) {
        bola.setTranslateX(0);
        bola.setTranslateY(0);
        handleCeldaGrilla(event);
        event.consume();
    }

    @FXML
    private void handleCeldaGrilla(MouseEvent event) {
        double sceneX = event.getSceneX();
        double sceneY = event.getSceneY();
        GridPane.setConstraints(bola, columnNorm(celda, columnCalc(celda, sceneX)),rowNorm(celda, rowCalc(celda, sceneY)));
    }
    
}
