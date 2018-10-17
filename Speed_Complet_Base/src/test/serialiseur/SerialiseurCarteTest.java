package test.serialiseur;

import jeu.cartes.carte.Carte;
import jeu.cartes.carte.ICarte;
import jeu.cartes.carte.Symbole;
import org.junit.jupiter.api.Test;
import serialiseur.ISerialiseur;
import serialiseur.SerialiseurCarte;

import java.awt.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SerialiseurCarteTest {

    @Test
    void serialiser() {
        ICarte c = new Carte(Color.RED, 1, Symbole.CASSEROLE);
        ISerialiseur<ICarte> sc = new SerialiseurCarte();
        assertEquals("255/0/0-1-4", sc.serialiser(c));
    }

    @Test
    void deserialiser() {
        ISerialiseur<ICarte> sc = new SerialiseurCarte();
        ICarte c = sc.deserialiser("255/0/0-1-4");
        assertEquals(c, new Carte(Color.RED, 1, Symbole.CASSEROLE));
    }

    @Test
    void exporter(){
        boolean reussite = false;
        try {
            FileOutputStream fos = new FileOutputStream("Carte.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new Carte(Color.RED, 1, Symbole.CASSEROLE));
            oos.writeObject(new Carte(Color.BLUE, 10, Symbole.SPAQUETTI));
            oos.close();
            fos.close();
            reussite = true;
        }
        catch (Exception e){
            // Pleure
        }
        assertTrue(reussite);
    }

    @Test
    void importer(){
        boolean reussite = false;
        Carte c = null;
        try {
            FileInputStream fis = new FileInputStream("Carte.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            c = (Carte) ois.readObject();
            ois.close();
            fis.close();
            reussite = true;
        }
        catch (Exception e){

        }
        System.out.println(c);
        assertTrue(reussite);
    }
}