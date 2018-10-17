package game.grid.card;

import java.awt.*;

public class Card implements ICard {

    private int value;
    private Color color;
    private Symbol symbol;
    private boolean active;


    public Card(){
        this.value = -1;
        this.color = Color.darkGray;
        this.symbol = Symbol.EMPTY;

        this.active = false;
    }

    public Card(int value, Color color, Symbol symbol){
        this.value = value;
        this.color = color;
        this.symbol = symbol;

        this.active = true;
    }

    @Override
    public void activate() {
        active = true;
    }

    @Override
    public void sleep() {
        active = false;
    }

    @Override
    public boolean toDisplay() {
        return this.value != -1 || this.color != Color.darkGray || this.symbol != Symbol.EMPTY;
    }

    @Override
    public boolean isCompatible(ICard c) {
        return this.value == ((Card) c).getValue();
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        String returnString;

        if (toDisplay()){
            if (active){
                returnString = "[" + String.format("%02d", value) + "|" + symbol + "]";
            }
            else {
                returnString = "[" + Symbol.EMPTY + "|" + Symbol.EMPTY + "]";
            }
        }
        else {
            returnString = "[--X--]";
        }

        return returnString;
    }
}
