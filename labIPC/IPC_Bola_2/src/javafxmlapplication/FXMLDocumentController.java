/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import static javafxmlapplication.Utils.*;
import javafx.scene.paint.Color;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Circle bola;
    
    private double X0;
    private double Y0;
    @FXML
    private SplitPane splitPane;
    @FXML
    private VBox vBox;
    @FXML
    private ImageView imagen;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Slider slider;
    @FXML
    private ColorPicker colorPicker;
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bola.strokeProperty().bind(colorPicker.valueProperty());
        bola.fillProperty().bind(colorPicker.valueProperty());
        
        bola.radiusProperty().bind(
            Bindings.min(
            grid.widthProperty().divide(5).divide(2),
            grid.heightProperty().divide(5).divide(2)).
               
                multiply(slider.valueProperty())
        );
        
        toggleButton.selectedProperty().addListener( (obj, old, newValue) -> {
            if(newValue) { // sí está seleccionado
                bola.fillProperty().unbind();
                bola.setFill(Color.TRANSPARENT);
            } else { //si no está seleccionado
                bola.fillProperty().bind(colorPicker.valueProperty());
            }
            });
    }    
    
    

    @FXML
    private void moverBola(KeyEvent event) {
        KeyCode teclaPulsada = event.getCode();
        Integer fila = grid.getRowIndex(bola);
        Integer columna = grid.getColumnIndex(bola);
        switch (teclaPulsada) {
            case UP:
                fila = fila - 1;
                fila = rowNorm(grid, fila);
                break;
            case DOWN:
                fila = fila + 1;
                fila = rowNorm(grid, fila);
                break;
            case LEFT:
                columna = columna - 1;
                columna = columnNorm(grid, columna);
                break;
            case RIGHT:
                columna = columna + 1;
                columna = columnNorm(grid, columna);
                break;
        }
        grid.setConstraints(bola, columna, fila);
    }

    @FXML
    private void saltaBola(MouseEvent event) {
//        double sceneX =event.getSceneX();
//        double sceneY = event.getSceneY();
        Point2D coordenaLocal = grid.sceneToLocal(event.getSceneX(), event.getSceneY());
//        int columna = columnCalc(grid, sceneX);
//        int fila = rowCalc(grid, sceneY);
        int columna = columnCalc(grid, coordenaLocal.getX());
        int fila = rowCalc(grid, coordenaLocal.getY());
        grid.setConstraints(bola, columnNorm(grid, columna), 
                                    rowNorm(grid,fila));
        event.consume();
    }

    @FXML
    private void sueltaBola(MouseEvent event) {
        
        bola.setTranslateX(0);
        bola.setTranslateY(0); 
        saltaBola(event);
    }

    @FXML
    private void arrastraBola(MouseEvent event) {
        bola.setTranslateX(event.getSceneX()- X0);
        bola.setTranslateY(event.getSceneY()- Y0);
    }

    @FXML
    private void pulsaBola(MouseEvent event) {
         X0 = event.getSceneX();
         Y0 = event.getSceneY();
         event.consume();
    }
    
}
