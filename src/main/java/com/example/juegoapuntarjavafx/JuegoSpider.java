package com.example.juegoapuntarjavafx;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoSpider{

    boolean mostrarBonus = false;
    int bonusActual;
    int contador = 0;
    double refresh = 20;//ms
    double addBallDuration = 10000;//ms
    double temp = 0;

    public void start(Stage theStage, IntValue points) {

        theStage.setTitle( "Click the Target!" );

        points.reseteaPuntos();
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 1280, 720 );


        root.getChildren().add( canvas );

        Circle targetData = new Circle(640,360,30);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(refresh), e->moveBalls(theStage,points)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final double[] x = new double[1];
        final double[] y = new double[1];

        theScene.setOnMouseClicked(
                new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent e) {
                        if (targetData.contains(e.getX(), e.getY())) {
                            if (contador%2 == 0){
                                x[0] = 640;
                                y[0] = 360;
                            }else{
                                x[0] = 50 + 1000 * Math.random();
                                y[0] = 50 + 500 * Math.random();
                            }

                            targetData.setCenterX(x[0]);
                            targetData.setCenterY(y[0]);

                            if (points.getBonusRacha() >= 0 && points.getBonusRacha() < 3) {
                                points.sumaPunto(1);
                                points.sumaBonus(1);
                            } else if (points.getBonusRacha() >= 3 && points.getBonusRacha() < 8) {
                                points.sumaPunto(2);
                                points.sumaBonus(1);
                                mostrarBonus = true;
                                bonusActual = 2;
                            } else if (points.getBonusRacha() >= 8) {
                                points.sumaPunto(3);
                                bonusActual = 3;
                            }
                            contador++;
                        }else {
                            points.restaPunto(bonusActual);
                            points.reseteaBonus();
                            bonusActual = 1;
                            mostrarBonus = false;
                        }
                        points.jugado = true;
                    }
                });


        Font theFont = Font.font( "League Gothic", FontWeight.BOLD, 32);
        gc.setFont( theFont );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Image pelota = new Image( "bullseye.png" );
        Image fondo = new Image("background.png");

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas
                gc.drawImage(fondo,0,0);

                /*gc.setFill( new Color(0.85, 0.85, 1.0, 1.0) );*/
                /*gc.fillRect(0,0, 1280,720);*/


                gc.setFill( Color.RED );
                gc.drawImage( pelota,
                        targetData.getCenterX() - targetData.getRadius(),
                        targetData.getCenterY() - targetData.getRadius() );

                String pointsText = "Points: " + points.puntos;
                String bonusRacha = "BONUS POR RACHA!";
                String bonusText = "+" + bonusActual + " Por hacierto!";

                int segundos = 10;

                if (temp >= 0){segundos=0;}
                if (temp >= 1000){segundos = 9;}
                if (temp >= 2000){segundos = 8;}
                if (temp >= 3000){segundos = 7;}
                if (temp >= 4000){segundos = 6;}
                if (temp >= 5000){segundos = 5;}
                if (temp >= 6000){segundos = 4;}
                if (temp >= 7000){segundos = 3;}
                if (temp >= 8000){segundos = 2;}
                if (temp >= 9000){segundos = 1;}
                if (temp >= 10000){segundos = 0;}

                String tiempo ="Tiempo" + segundos;

                gc.fillText(tiempo,50,50);
                gc.fillText( pointsText, 1100, 36 );
                gc.strokeText( pointsText, 1100, 36 );


                if (mostrarBonus){gc.fillText(bonusRacha,950,100);gc.fillText(bonusText,1000,130);}
                gc.setFill(Color.RED);
                gc.setFont( theFont );
                gc.setStroke( Color.BLACK );
                gc.setLineWidth(1);
            }
        }.start();

        theStage.show();
    }

    private void moveBalls(Stage stage, IntValue points) {
        temp = temp + refresh;
        points.cambiarJugado();

        if (temp > addBallDuration) {
            temp = 0;
            points.setRecord();
            points.reseteaBonus();
            stage.hide();
        }
    }

}
