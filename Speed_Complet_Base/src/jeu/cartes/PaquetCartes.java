package jeu.cartes;

import jeu.joueur.IJoueur;
import jeu.cartes.carte.Carte;
import jeu.cartes.carte.ICarte;
import jeu.cartes.carte.Symbole;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaquetCartes extends Paquet<ICarte> {

    private static final int NB_CARTES = 72;
    private static final int NB_VALEURS = 5;
    private static final int NB_SYMBOLES = 5;
    private static final int NB_COULEURS = 5;

    public PaquetCartes() {
        elements = new ArrayList<ICarte>();

        Color[] couleurs = { Color.blue, Color.orange, Color.cyan, Color.black, Color.lightGray };

        Symbole[] motifs = Symbole.getSymboles();

        for (Color c : couleurs) {
            for (int i = 0; i < NB_SYMBOLES; i++) {
                for (int j = 0; j < NB_VALEURS; j++) {
                    elements.add(new Carte(c, j + 1, motifs[i]));
                }
            }
        }

        melanger();

        elements = elements.subList(0, NB_CARTES);
    }

    public PaquetCartes(List<ICarte> list){
        elements = list;
    }

    public PaquetCartes(PaquetCartes p, int nbrCartes) {
        elements = new ArrayList<ICarte>();
        if (nbrCartes <= p.size()){
            for (int i = 0; i < nbrCartes; i++){
                this.add(p.remove(0));
            }
        }
    }

    public ICarte testerCarteSommet(int i, ICarte c, IJoueur j2) {
        ICarte carte = null;
        if (get(i).estCompatible(c)){
            j2.oterEventuellementUnePenalite();
            carte = remove(i);
        }
        return carte;
    }

    public int getNbCartes(){
        return NB_CARTES;
    }

    public boolean estVide(){
        return get(0).isVide();
    }

    @Override
    public String toString() {
        String s = "";
        for (ICarte c : elements) {
            s += "\n" + c;
        }
        s += "\n" + size();
        return s;
    }
}
