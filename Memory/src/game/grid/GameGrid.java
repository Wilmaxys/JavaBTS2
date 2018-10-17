package game.grid;

import game.grid.card.Card;
import game.grid.card.Symbol;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameGrid implements IGrid<Card> {

    private static final int NB_CARDS = 52;

    private List<Card> cards;

    public GameGrid() {
        this.cards = new ArrayList<>();

        Symbol[] symbols = Symbol.getSymbols();

        for (int family = 0; family < Symbol.getNumberOfSymbols(); family++){
            for (int value = 0; value < 13; value++){
                this.cards.add(new Card(value + 1, symbols[family] == Symbol.CLUB || symbols[family] == Symbol.SPADE ? Color.black : Color.red, symbols[family]));
            }
        }

        shuffle();
    }

    @Override
    public Card pick(int index) {
        cards.get(index).activate();
        return cards.get(index);
    }

    @Override
    public Card delete(int index) {
        return cards.remove(index);
    }

    @Override
    public Card set(int index, Card c) {
        return cards.set(index, c);
    }

    @Override
    public void activate(int index) {
        cards.get(index).activate();
    }

    @Override
    public void sleep(int index) {
        cards.get(index).sleep();
    }

    public void sleepAll(){
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).sleep();
        }
    }

    public boolean allDisplayed(){
        boolean allDisplayed = false;

        int i = 0;
        while (!allDisplayed && i < cards.size()){
            allDisplayed = cards.get(i).toDisplay();

            i++;
        }

        return allDisplayed;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        String returnString = "";

        for (int i = 0; i < cards.size(); i++) {
            returnString += String.format("%02d", (i + 1)) + cards.get(i).toString() + " ";

            if ((i + 1)%13 == 0){
                returnString += "\n";
            }
        }


        return returnString;
    }
}
