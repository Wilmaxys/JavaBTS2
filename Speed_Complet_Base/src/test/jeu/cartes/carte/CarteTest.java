package test.jeu.cartes.carte;

import jeu.cartes.carte.Carte;
import jeu.cartes.carte.Symbole;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    private Carte c1;
    private Carte c2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void estCompatible() {
        System.out.println("Test de EstCompatible");
        c1 = new Carte(Color.black, 2, Symbole.CASSEROLE);
        c2 = new Carte(Color.black, 2, Symbole.CASSEROLE);
        assertTrue(c1.estCompatible(c2));
        c1 = new Carte();
        assertFalse(c1.estCompatible(c2));
    }

    @org.junit.jupiter.api.Test
    void isVide() {
        System.out.println("Test de isVide");
        c1 = new Carte(Color.black, 2, Symbole.CASSEROLE);
        assertFalse(c1.isVide());
        c1 = new Carte();
        assertTrue(c1.isVide());
    }
}