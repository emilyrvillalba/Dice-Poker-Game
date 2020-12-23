package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.*;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

public class Main extends Application {
    Dice die1 = new Dice();
    Dice die2 = new Dice();
    Dice die3 = new Dice();
    Dice die4 = new Dice();
    Dice die5 = new Dice();

    ArrayList<Dice> diceList=new ArrayList<Dice>();

    private int[] value = new int[]{1,1,1,1,1};

    private int overallScore = 0;
    private int roundScore = 0;
    private int rollsRemaining = 3;
    private int round = 1;
    private Label overallScoreLabel;
    private Label roundLabel;
    private Button rollButton;
    private Label roundScoreLabel;
    private Label rollsRemainingLabel;


    @Override
    public void start(Stage primaryStage) throws Exception {
        diceList.add(die1);
        diceList.add(die2);
        diceList.add(die3);
        diceList.add(die4);
        diceList.add(die5);

        overallScoreLabel = new Label("o v e r a l l  s c o r e : 0");
        overallScoreLabel.setPadding(new Insets(5));
        roundLabel = new Label("r o u n d  " + round);
        overallScoreLabel.setStyle(" -fx-border-style: dotted; ");
        rollButton = new Button("r o l l  d i c e");
        roundScoreLabel = new Label("r o u n d  s c o r e : 0");
        rollsRemainingLabel = new Label("r o l l s  r e m a i n i n g : " + rollsRemaining);

        rollButton.setOnAction(new rollButtonHandler());

        die1.getDiceView().setOnMouseClicked((MouseEvent e) -> {
            if (rollsRemaining < 3) {
                if (!die1.getIfHeld())
                    die1.setDiceView(true);
                else
                    die1.setDiceView(false);
            }
        });

        die2.getDiceView().setOnMouseClicked((MouseEvent e) -> {
            if (rollsRemaining < 3) {
                if (!die2.getIfHeld())
                    die2.setDiceView(true);
                else
                    die2.setDiceView(false);
            }
        });

        die3.getDiceView().setOnMouseClicked((MouseEvent e) -> {
            if (rollsRemaining < 3) {
                if (!die3.getIfHeld())
                    die3.setDiceView(true);
                else
                    die3.setDiceView(false);
            }
        });

        die4.getDiceView().setOnMouseClicked((MouseEvent e) -> {
            if (rollsRemaining < 3) {
                if (!die4.getIfHeld())
                    die4.setDiceView(true);
                else
                    die4.setDiceView(false);
            }
        });

        die5.getDiceView().setOnMouseClicked((MouseEvent e) -> {
            if (rollsRemaining < 3) {
                if (!die5.getIfHeld())
                    die5.setDiceView(true);
                else
                    die5.setDiceView(false);
            }
        });


        HBox diceDisplay = new HBox(die1.getDiceView(), die2.getDiceView(), die3.getDiceView(), die4.getDiceView(), die5.getDiceView());
        diceDisplay.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, overallScoreLabel, roundLabel, diceDisplay, rollButton, roundScoreLabel, rollsRemainingLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("mystyles.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dice Game");
        primaryStage.show();
    }

    public class rollButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //rolling the dice as long as there are rolls remaining
            if (rollsRemaining > 0){

                //rolls dice
                for (int i = 0 ; i < 5 ; i++){
                    if (!diceList.get(i).getIfHeld()) {
                        value[i] = diceList.get(i).roll();
                    }
                }

                //update how many rolls are remaining
                rollsRemaining--;
                rollsRemainingLabel.setText("r o l l s  r e m a i n i n g : "  + rollsRemaining);
                if (rollsRemaining == 0)
                    rollButton.setText("p l a y  a g a i n");
            }
            else {
                //reset the game for another round
                round++;
                roundLabel.setText("r o u n d  " + round);
                rollButton.setText("r o l l  d i c e");
                rollsRemainingLabel.setStyle("-fx-font-weight: normal");
                roundScore = 0;
                rollsRemaining = 3;
                roundScoreLabel.setText("r o u n d  s c o r e : " + roundScore);
                rollsRemainingLabel.setText("r o l l s  r e m a i n i n g : "  + rollsRemaining);
                for (int i = 0; i <5; i++) {
                    diceList.get(i).setRegularView(1);
                    value[i] = 1;
                }
            }

            //after the final roll, determine the best hand and add the scores to overall and round score
            if (rollsRemaining == 0){
                rollsRemainingLabel.setStyle("-fx-font-weight: bold");
                insertionSort(value);
                if (FiveOfAKind(value)) {
                    overallScore += 10;
                    roundScore += 10;
                    rollsRemainingLabel.setText("F I V E  O F  A  K I N D !");

                }
                else if (Straight(value)){
                    overallScore += 8;
                    roundScore += 8;
                    rollsRemainingLabel.setText("S T R A I G H T !");

                }
                else if (FourOfAKind(value)){
                    overallScore += 7;
                    roundScore += 7;
                    rollsRemainingLabel.setText("F O U R  O F  A  K I N D !");

                }
                else if (FullHouse(value)){
                    overallScore += 6;
                    roundScore += 6;
                    rollsRemainingLabel.setText("F U L L  H O U S E !");

                }
                else if (ThreeOfAKind(value)){
                    overallScore += 5;
                    roundScore += 5;
                    rollsRemainingLabel.setText("T H R E E  O F  A  K I N D !");

                }
                else if (TwoPair(value)){
                    overallScore += 4;
                    roundScore += 4;
                    rollsRemainingLabel.setText("T W O  P A I R S !");

                }
                else if (TwoOfAKind(value)){
                    overallScore += 1;
                    roundScore += 1;
                    rollsRemainingLabel.setText("T W O  O F  A  K I N D !");

                }
                else{
                    rollsRemainingLabel.setText("N O  H A N D  F O U N D !");
                }

                overallScoreLabel.setText("o v e r a l l  s c o r e : " + overallScore);
                roundScoreLabel.setText("r o u n d  s c o r e : " + roundScore);
            }
        }
    }


    public void insertionSort(int array[]) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public boolean FiveOfAKind(int[] array){
        boolean match = false;
        for (int i=0; i<4; i++){
            if (value[i] == value[i+1])
                match = true;
            else
                match = false;
            if (!match)
                break;
        }
        return match;
    }

    public boolean Straight(int[] array){
        boolean straight = false;
        for (int i=0; i<4; i++){
            if (value[i] == value[i+1]-1)
                straight = true;
            else
                straight = false;
            if (!straight)
                break;
        }
        return straight;
    }

    public boolean FourOfAKind(int[] array){
        boolean four = false;
        //[x x x x 0]
        for (int i=0; i<3; i++){
            if (value[i] == value[i+1])
                four = true;
            else
                four = false;
            if (!four)
                break;
        }
        //[0 x x x x]
        if (!four){
            for (int i=1; i<4; i++){
                if (value[i] == value[i+1])
                    four = true;
                else
                    four = false;
                if (!four)
                    break;
            }
        }
        return four;
    }

    public boolean FullHouse(int[] array){
        boolean full = false;
        //[x x x y y]
        if (value[0] == value[1] && value[1]==value[2]) {
            if (value[3] == value[4])
                full = true;
        }
        //[x x y y y]
        if(!full) {
            if (value[0] == value[1]) {
                if (value[2] == value[3] && value[3] == value[4])
                    full = true;
            }
        }
        return full;
    }

    public boolean ThreeOfAKind(int[] array){
        boolean three = false;
        //[x x x y z] || [x y y y z] || [x y z z z]
        if (value[0] == value[1] && value[1]==value[2]
            || value[1] == value[2] && value[2]==value[3]
            || value[2] == value[3] && value[3]==value[4]) {
            three = true;
        }
        return three;
    }

    public boolean TwoPair(int[] array){
        boolean pair = false;
        //[x x y y z] || [x y y z z] || [x x y z z]
        if (value[0] == value[1] && value[2] == value[3]
            || value[1] == value[2] && value[3] == value[4]
            || value[0] == value[1] && value[3] == value[4]) {
            pair = true;
        }
        return pair;
    }

    public boolean TwoOfAKind(int[] array){
        boolean two = false;
        //[w w x y z] || [w x x y z] || [w x y y z] || [w x y z z]
        if (value[0] == value[1]
            || value[1] == value[2]
            || value[2] == value[3]
            || value[3] == value[4]) {
            two = true;
        }
        return two;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
