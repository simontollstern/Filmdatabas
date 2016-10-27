/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmdatabas;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author Simon Tollstern
 */
public class DetailBox extends Pane {
    
    private Pane posterBox;
    
    private Label title;
    private Label description;
    
    public DetailBox() {
        this.setLayoutX(200);
        this.setLayoutY(362);
        this.setPrefWidth(820);
        this.setPrefHeight(210);
        this.setMaxWidth(820);
        this.setMaxHeight(210);
        this.setId("detailBox");
        
        posterBox = new Pane();
        title = new Label("test");
        description = new Label("test");
        
        this.getChildren().addAll(posterBox, title, description);
        
        posterBox.setTranslateX(4);
        posterBox.setTranslateY(4);
        posterBox.setPrefWidth(162);
        posterBox.setPrefHeight(202);
        posterBox.setMaxHeight(162);
        posterBox.setMaxHeight(202);
        posterBox.setStyle("-fx-background-color: #333;");
        
        title.setTranslateX(172);
        title.setTranslateY(4);
        title.setStyle("-fx-font: 24 System");
        
        description.setTranslateX(172);
        description.setTranslateY(54);
        description.setStyle("-fx-font: 18 System");
    }
}
