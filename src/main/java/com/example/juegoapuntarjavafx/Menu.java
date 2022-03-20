package com.example.juegoapuntarjavafx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Menu extends Application {
    IntValue points = new IntValue();


    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle( "Game Menu");

        JuegoAleatorio juegoAleatorio = new JuegoAleatorio();
        JuegoSpider juegoSpider = new JuegoSpider();

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 1280, 720 );

        root.getChildren().add( canvas );



        GraphicsContext gc = canvas.getGraphicsContext2D();

        Button juegoA = new Button("Juego aleatorio");
        Button juegoS = new Button("Juego spider");
        juegoA.setMinSize(200,100);
        juegoA.setLayoutX(140);
        juegoA.setLayoutY(450);

        juegoS.setMinSize(200,100);
        juegoS.setLayoutX(140);
        juegoS.setLayoutY(200);

        root.getChildren().add(juegoA);
        root.getChildren().add(juegoS);

        Stage stage = new Stage();
        juegoA.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        juegoAleatorio.start(stage,points);
                    }
                });

        juegoS.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        juegoSpider.start(stage,points);
                    }
                });

        Image fondo = new Image("fondoMenu.png");
        Font theFont = Font.font( "League Gothic", FontWeight.BOLD, 32);
        gc.setFont( theFont );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.drawImage(fondo,0,0);
                String pointsText = "Has conseguido: " + points.puntos;
                String recordText = "Tu record es: " + points.record;

                if (points.jugado == true){
                    gc.fillText(pointsText,600,300);
                    gc.fillText(recordText,600, 400);
                }
                gc.setFill(Color.RED);

            }
        }.start();
        theStage.show();
    }
}
