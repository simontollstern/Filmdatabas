/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmdatabas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Simon Tollstern
 */
public class Form extends Pane {
    
    private TextField title;
    private TextField genre;
    private TextField director;
    private TextField rating;

    private Label formHead;
    private Label titleLabel;
    private Label genreLabel;
    private Label direcLabel;
    private Label ratingLabel;

    private Button addBtn;
    private Button cancelBtn;

    public Form() {
        this.setLayoutX(200);
        this.setLayoutY(0);
        this.setPrefWidth(300);
        this.setPrefHeight(576);
        this.setMaxWidth(300);
        this.setMaxHeight(576);
        this.setId("formPane");

        formHead = new Label("Lägg till film");
        titleLabel = new Label("Titel: ");
        title = new TextField();
        genreLabel = new Label("Genre: ");
        genre = new TextField();
        direcLabel = new Label("Regissör: ");
        director = new TextField();
        ratingLabel = new Label("Omdöme: ");
        rating = new TextField();
        addBtn = new Button("Lägg till");
        cancelBtn = new Button("Avbryt");

        this.getChildren().addAll(formHead, titleLabel, title, genreLabel, genre, direcLabel, director, ratingLabel, rating, addBtn, cancelBtn);

        formHead.setTranslateX(20);
        formHead.setTranslateY(40);
        formHead.setStyle("-fx-font: 24 System");

        titleLabel.setTranslateX(30);
        titleLabel.setTranslateY(100);
        
        genreLabel.setTranslateX(30);
        genreLabel.setTranslateY(140);
        
        direcLabel.setTranslateX(30);
        direcLabel.setTranslateY(180);
        
        ratingLabel.setTranslateX(30);
        ratingLabel.setTranslateY(220);
        
        title.setTranslateX(110);
        title.setTranslateY(100);
        
        genre.setTranslateX(110);
        genre.setTranslateY(140);
        
        director.setTranslateX(110);
        director.setTranslateY(180);
        
        rating.setTranslateX(110);
        rating.setTranslateY(220);
        
        addBtn.setTranslateX(70);
        addBtn.setTranslateY(300);
        addBtn.setId("addBtn");
        
        cancelBtn.setTranslateX(170);
        cancelBtn.setTranslateY(300);
        addBtn.setId("cancelBtn");
    }
}
