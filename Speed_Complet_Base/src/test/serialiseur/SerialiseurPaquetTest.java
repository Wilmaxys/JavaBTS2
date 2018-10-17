package test.serialiseur;

import jeu.cartes.IPaquet;
import jeu.cartes.PaquetCartes;
import jeu.cartes.carte.Carte;
import jeu.cartes.carte.ICarte;
import jeu.cartes.carte.Symbole;
import org.junit.jupiter.api.Test;
import serialiseur.ISerialiseur;
import serialiseur.SerialiseurPaquet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SerialiseurPaquetTest {

    @Test
    void serialiser() {
        ISerialiseur<IPaquet> sp = new SerialiseurPaquet();
        PaquetCartes p1 = new PaquetCartes();
        PaquetCartes p2 = new PaquetCartes();
        assertEquals(sp.serialiser(p1), sp.serialiser(p2));
    }

    @Test
    void deserialiser() {
        ISerialiseur<IPaquet> sp = new SerialiseurPaquet();
        List<ICarte> cartes = new ArrayList<>(Arrays.asList(new Carte(Color.RED, 1, Symbole.CASSEROLE), new Carte(Color.RED, 2, Symbole.CASSEROLE)));
        IPaquet<ICarte> p1 = new PaquetCartes(cartes);
        IPaquet<ICarte> p2 = sp.deserialiser(sp.serialiser(p1));
        assertEquals(sp.serialiser(p1), sp.serialiser(p2));
    }
}