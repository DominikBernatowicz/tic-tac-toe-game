package com.kodilla.ticTacToe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToe extends Application {

    private final Image imageback = new Image("file:src/main/resources/plansza.png");   //plansza
//    private final Image x = new Image("file:src/main/resources/x.png");
//    private final Image o = new Image("file:src/main/resources/o.png");
    private final GridPane grid = new GridPane();
    private final String[][] valueOfButtons = new String[3][3];
    private final String[] pola = new String[10];
    private final Random generator = new Random();
    private boolean isTurnX = true;
    private boolean yourTurn = true; // kolejka na początek gry
    private String wybierzXczyO = "";
    private int a = 0;
    private int a1 = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setBackground(background);
        grid.setPrefSize(450, 450);

        startApp();


        Scene scene = new Scene(grid, 450, 500);

        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void sprawdzenieWygranej(int finalI, int finalJ) {

        //wygrane poziome
        if (valueOfButtons[finalI][0] != null && valueOfButtons[finalI][0].equals(valueOfButtons[finalI][1])
                && valueOfButtons[finalI][0].equals(valueOfButtons[finalI][2])) {

            System.out.println("Winner is: " + valueOfButtons[finalI][0] + " player.");
            Platform.exit(); //zastąpić zatrzymaniem programu
        }

        //wygrane pionowe
        else if (valueOfButtons[0][finalJ] != null && valueOfButtons[0][finalJ].equals(valueOfButtons[1][finalJ])
                && valueOfButtons[0][finalJ].equals(valueOfButtons[2][finalJ])) {

            System.out.println("Winner is: " + valueOfButtons[0][finalJ] + " player.");
            Platform.exit(); //zastąpić zatrzymaniem programu
        }

        //wygrane skośne
        else if (valueOfButtons[0][0] != null && valueOfButtons[0][0].equals(valueOfButtons[1][1])
                && valueOfButtons[0][0].equals(valueOfButtons[2][2])) {

            System.out.println("Winner is: " + valueOfButtons[0][0] + " player.");
            Platform.exit(); //zastąpić zatrzymaniem programu
        }
        else if (valueOfButtons[0][2] != null && valueOfButtons[0][2].equals(valueOfButtons[1][1])
                && valueOfButtons[0][2].equals(valueOfButtons[2][0])) {

            System.out.println("Winner is: " + valueOfButtons[0][2] + " player.");
            Platform.exit(); //zastąpić zatrzymaniem programu
        }
    }

    public void wyswietl() {

        System.out.println("----New move----");
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                if (valueOfButtons[i][j] == null) {
                    System.out.print("-\t");
                } else {
                    System.out.print(valueOfButtons[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public void rozgrywka() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setMinSize(150, 150);
               // button.setStyle("-fx-background-color:transparent;-fx-padding:0;-fx-background-size:0;");


                int finalJ = i;
                int finalI = j;
                button.setOnMouseClicked(event -> {
                    if (isTurnX) {
//                        button.setGraphic(new ImageView(x));
                        button.setText("X");
                        button.setFont(Font.font(70));
                        button.setDisable(true);

                        yourTurn = !yourTurn;
                        valueOfButtons[finalI][finalJ] = "X";

                        wyswietl();
                    } else {
//                        button.setGraphic(new ImageView(o));
                        button.setText("O");
                        button.setFont(Font.font(70));
                        button.setDisable(true);

                        yourTurn = !yourTurn;
                        valueOfButtons[finalI][finalJ] = "O";

                        wyswietl();
                    }
                    sprawdzenieWygranej(finalI, finalJ);
                    isTurnX = !isTurnX;

                });
                grid.add(button, i, j);
            }
        }
    }

    public void startApp() {
        grid.setAlignment(Pos.BOTTOM_CENTER);

        Button start = new Button("START");
        Label label = new Label("\n\n\n\n\n\n  Uwaga zaczyna X!");

        label.setFont(Font.font(22));
        label.setStyle("-fx-text-fill: FIREBRICK; -fx-font-weight: bold" );

        start.setFont(Font.font(50));
        start.setOnMouseClicked(event -> {
            grid.getChildren().clear();

            wybierzXczyO();
        });

        grid.addRow(1,start);
        grid.addRow(2, label);
    }

    public void wybierzXczyO() {
        grid.setAlignment(Pos.CENTER);

        Button X = new Button("X");
        Button O = new Button("O");

        X.setFont(Font.font(50));
        O.setFont(Font.font(50));

        X.setOnMouseClicked(e1 -> {
            yourTurn = true;
            wybierzXczyO = "X";

            grid.getChildren().clear();
            grid.setAlignment(Pos.TOP_CENTER);

            rozgrywka();
            restartApp();
            quitApp();

            System.out.println("Good luck!");
        });
        O.setOnMouseClicked(e2 -> {
            yourTurn = false;
            wybierzXczyO = "O";

            grid.getChildren().clear();
            grid.setAlignment(Pos.TOP_CENTER);

            rozgrywka();
            restartApp();
            quitApp();

            System.out.println("Good luck!");
        });

        grid.addColumn(0, X);
        grid.addColumn(1, O);
    }

    public void restartApp() {
        Button newGame = new Button("NEW GAME");

        newGame.setMinSize(150,50);
        newGame.setOnMouseClicked(event -> {
            grid.getChildren().clear();

            isTurnX = true;

            rozgrywka();
            restartApp();
            quitApp();

            System.out.println("You started a new game!");
        });

        grid.add(newGame,0,3);
    }

    private void quitApp() {
        Button quit = new Button("QUIT");

        quit.setMinSize(150, 50);
        quit.setOnMouseClicked(event -> {
            Platform.exit();

            System.out.println("Goodbye!");
        });

        grid.add(quit,2,3);
    }

    private void computerLogic() {

        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pola[k] = valueOfButtons[i][j];

                k ++;
            }
        }

        if (yourTurn == false) {
            if (isTurnX == true) {
                do {
                    a = 0;
                    if (wybierzXczyO != pola[a]) {
                        a1 = a;
                    }
                } while (wybierzXczyO != pola[a]);
            }
        } else {
            if (isTurnX == false) {
                do {
                    a = generator.nextInt(10);
                    if (wybierzXczyO != pola[a]) {
                        a1 = a;
                    }
                } while (wybierzXczyO != pola[a]);
            }
        }
        grid.getChildren().sorted();
    }
}