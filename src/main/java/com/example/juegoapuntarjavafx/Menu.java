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
import javafx.stage.Stage;

public class Menu extends Application {



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

        IntValue points = new IntValue();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Button juegoA = new Button("Juego aleatorio");
        Button juegoS = new Button("Juego spider");

        root.getChildren().add(juegoA);
        root.getChildren().add(juegoS);
        Stage stage = new Stage();
        juegoA.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        juegoAleatorio.start(stage);
                    }
                });

        juegoS.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        juegoSpider.start(stage);
                    }
                });

        Image fondo = new Image("fondoMenu.png");

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.drawImage(fondo,0,0);

                String pointsText = "Has conseguido: " + points.puntos;

                if (points.jugado == true){
                    gc.fillText(pointsText,640,360);

                }

            }
        }.start();
        theStage.show();
    }
}
