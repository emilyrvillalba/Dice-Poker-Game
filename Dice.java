package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Dice {
    int currentNum;
    boolean held;
    ImageView diceView;
    Random random = new Random();


    public Dice(){
        currentNum = 1;
        diceView = new ImageView(new Image("file:./res/Dice1.png"));
        this.setSize();
        held = false;
    }

    public void setSize(){
        diceView.setPreserveRatio(true);
        diceView.setFitWidth(100);
    }

    public ImageView getDiceView(){
        return diceView;
    }

    public boolean getIfHeld(){
        return held;
    }

    public void setDiceView(boolean hold) {
        if (!hold) {
            this.setRegularView(currentNum);
            held = false;
        }
        else {
            switch(currentNum){
                case 1:
                    diceView.setImage(new Image("file:./res/Dice1Held.png"));
                    this.setSize();
                    break;
                case 2:
                    diceView.setImage(new Image("file:./res/Dice2Held.png"));
                    this.setSize();
                    break;
                case 3:
                    diceView.setImage(new Image("file:./res/Dice3Held.png"));
                    this.setSize();
                    break;
                case 4:
                    diceView.setImage(new Image("file:./res/Dice4Held.png"));
                    this.setSize();
                    break;
                case 5:
                    diceView.setImage(new Image("file:./res/Dice5Held.png"));
                    this.setSize();
                    break;
                case 6:
                    diceView.setImage(new Image("file:./res/Dice6Held.png"));
                    this.setSize();
                    break;
            }
            held = true;
        }
    }

    public int roll(){
        currentNum = random.nextInt(6)+1;
        this.setRegularView(currentNum);
        return currentNum;
    }

    public int setRegularView(int currentNum){
        held = false;
        switch(currentNum){
            case 1:
                diceView.setImage(new Image("file:./res/Dice1.png"));
                this.setSize();
                break;
            case 2:
                diceView.setImage(new Image("file:./res/Dice2.png"));
                this.setSize();
                break;
            case 3:
                diceView.setImage(new Image("file:./res/Dice3.png"));
                this.setSize();
                break;
            case 4:
                diceView.setImage(new Image("file:./res/Dice4.png"));
                this.setSize();
                break;
            case 5:
                diceView.setImage(new Image("file:./res/Dice5.png"));
                this.setSize();
                break;
            case 6:
                diceView.setImage(new Image("file:./res/Dice6.png"));
                this.setSize();
                break;
        }

        return currentNum;
    }


}
