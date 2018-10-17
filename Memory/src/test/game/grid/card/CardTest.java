package test.game.grid.card;

import game.grid.card.Card;
import game.grid.card.Symbol;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card c1;
    private Card c2;

    @org.junit.jupiter.api.Test
    void toDisplay() {
        c1 = new Card();
        assertFalse(c1.toDisplay());
        c1 = new Card(2, Color.black, Symbol.CLUB);
        assertTrue(c1.toDisplay());
    }

    @org.junit.jupiter.api.Test
    void isCompatible() {
        c1 = new Card(2, Color.black, Symbol.CLUB);
        c2 = new Card(2, Color.black, Symbol.SPADE);
        assertTrue(c1.isCompatible(c2));
        c2 = new Card(5, Color.black, Symbol.SPADE);
        assertFalse(c1.isCompatible(c2));
    }
}