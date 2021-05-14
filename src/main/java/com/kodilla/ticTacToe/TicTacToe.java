package com.kodilla.ticTacToe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private double XorY = 500;      //długość
    private boolean isTurnX = true;
    private int matrixSize = 3;
    private Image imageback = new Image("file:src/main/resources/plansza.png");   //plansza
    private Image x = new Image("file:src/main/resources/X.png");   //X
    private Image o = new Image("file:src/main/resources/O.png");   //O
    private Button button[][] = new Button[3][3];
    private String combo[][] = new String[3][3];
    private GridPane grid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

            /*for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    Button button = new Button();
                    button.setMinSize(135, 135);

                    //button.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;");

                    //zapisywanie w pamięci X/O
                    if (isTurnX){
                        combo[i][j] = "X";
                    } else {
                        combo[i][j] = "O";
                    }

                    button.setOnMouseClicked(event -> {
                        if (isTurnX) {
                            //button.setGraphic(new ImageView(x));
                            button.setText("X");
                            button.setFont(Font.font(70));

                        } else {
                            //button.setGraphic(new ImageView(o));
                            button.setText("O");
                            button.setFont(Font.font(70));

                        }
                        isTurnX = !isTurnX;

                    });
                    grid.add(button, i, j);


                }
            }*/


        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(createInside()));
        primaryStage.show();

    }

    public Parent createInside() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setBackground(background);
        grid.setPrefSize(450, 450);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CreateField field = new CreateField();
                field.setTranslateX(i *  150);
                field.setTranslateY(j * 150);
                grid.getChildren().add(field);

                String lol = field.ValueOfButton(); //zmienić nazwę zmiennej
                combo[i][j] = lol;
                //dodać klasę checkWinner
            }
        }
        return grid;
    }

    private class CreateField extends StackPane {

        Button butt = new Button();

        public CreateField() {

            butt.setMinSize(150, 150);
            butt.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;");

            getChildren().addAll(butt);

            butt.setOnMouseClicked(event -> {
                if (isTurnX) {
                    butt.setText("X");

                } else {
                    butt.setText("O");

                }
                butt.setFont(Font.font(70));
                isTurnX = !isTurnX;

            });
        }

        public String ValueOfButton() {
            return butt.getText();
        }
    }

    //zmienić na klasę children
    public String checkWinner() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                combo[i][j] = button[i][j].getText();
            }
        }
        String winner = "remis";

        for (int i = 0; i < 3; i++) {

            //wygrane poziome
            if (combo[i][0] == combo[i][1] && combo[i][0] == combo[i][2]) {
                winner = combo[i][0];
            }

            //wygrane pionowe
            else if (combo[0][i] == combo[1][i] && combo[0][i] == combo[2][i]) {
                winner = combo[0][i];
            }

            //wygrane skośne
            else if (combo[0][0] == combo[1][1] && combo[0][0] == combo[2][2]) {
                winner = combo[0][0];
            }
            else if (combo[0][2] == combo[1][1] && combo[0][2] == combo[2][0]) {
                winner = combo[0][2];
            }
        }
        return winner;
    }
}

//https://www.youtube.com/watch?v=Uj8rPV6JbCE