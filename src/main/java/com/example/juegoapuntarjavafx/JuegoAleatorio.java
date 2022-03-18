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

public class JuegoAleatorio{

    boolean mostrarBonus = false;
    int bonusActual;
    double refresh = 20;//ms
    double addBallDuration = 10000;//ms
    double temp = 0;

    public void start(Stage theStage) {
        theStage.setTitle( "Click the Target!" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 1280, 720 );

        root.getChildren().add( canvas );

        Circle targetData = new Circle(640,360,30);
        Circle targetData2 = new Circle(50 + 1000 * Math.random(),50 + 500 * Math.random(),30);
        Circle targetData3 = new Circle(50 + 1000 * Math.random(),50 + 500 * Math.random(),30);

        IntValue points = new IntValue();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(refresh), e->moveBalls(theStage)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        GraphicsContext gc = canvas.getGraphicsContext2D();


        theScene.setOnMouseClicked(
                new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent e) {
                        if (targetData.contains(e.getX(), e.getY())) {
                            double x = 50 + 1000 * Math.random();
                            double y = 50 + 500 * Math.random();
                            targetData.setCenterX(x);
                            targetData.setCenterY(y);

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

                        }else if (targetData2.contains(e.getX(), e.getY())) {
                            double x = 50 + 1000 * Math.random();
                            double y = 50 + 500 * Math.random();
                            targetData2.setCenterX(x);
                            targetData2.setCenterY(y);

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

                        }else if (targetData3.contains(e.getX(), e.getY())) {
                            double x = 50 + 1000 * Math.random();
                            double y = 50 + 500 * Math.random();
                            targetData3.setCenterX(x);
                            targetData3.setCenterY(y);

                            if (points.getBonusRacha() >= 0 && points.getBonusRacha() < 3) {
                                points.sumaPunto(1);
                                points.sumaBonus(1);
                                bonusActual = 1;
                            } else if (points.getBonusRacha() >= 3 && points.getBonusRacha() < 8) {
                                points.sumaPunto(2);
                                points.sumaBonus(1);
                                mostrarBonus = true;
                                bonusActual = 2;
                            } else if (points.getBonusRacha() >= 8) {
                                points.sumaPunto(3);
                                bonusActual = 3;
                            }

                        }else {
                            points.restaPunto(bonusActual);
                            points.reseteaBonus();
                            bonusActual = 1;
                            mostrarBonus = false;
                        }
                    }
                });


        Font theFont = Font.font( "League Gothic", FontWeight.BOLD, 32);
        gc.setFont( theFont );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Image pelota = new Image( "bullseye.png" );
        Image fondo = new Image("background.png");

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas
                gc.drawImage(fondo,0,0);
                gc.drawImage( pelota,
                        targetData.getCenterX() - targetData.getRadius(),
                        targetData.getCenterY() - targetData.getRadius() );
                gc.drawImage( pelota,
                        targetData2.getCenterX() - targetData2.getRadius(),
                        targetData2.getCenterY() - targetData2.getRadius() );
                gc.drawImage( pelota,
                        targetData3.getCenterX() - targetData3.getRadius(),
                        targetData3.getCenterY() - targetData3.getRadius() );

                /*gc.setFill( Color.RED );*/

                String pointsText = "Points: " + points.puntos;
                String bonusRacha = "BONUS POR RACHA!";
                String bonusText = "+" + bonusActual + " Por hacierto!";

                int segundos = 10;

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
                gc.fillText( pointsText, 1050, 36 );
                gc.strokeText( pointsText, 1050, 36 );

                if (mostrarBonus){gc.fillText(bonusRacha,950,100);gc.fillText(bonusText,1000,130);}

            }
        }.start();

        theStage.show();
    }

    private void moveBalls(Stage stage) {
        temp = temp + refresh;
        if (temp > addBallDuration) {
            temp = 0;
            stage.hide();
        }
    }
}
