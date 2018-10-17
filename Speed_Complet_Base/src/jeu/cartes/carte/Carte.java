package jeu.cartes.carte;

import java.awt.*;

public class Carte implements ICarte {

    private final Color couleur;
    private final int valeur;
    private final Symbole motif;

    public Carte(){
        this.couleur = Color.darkGray;
        this.valeur = -1;
        this.motif = Symbole.VIDE;
    }

    public Carte(Color couleur, int valeur, Symbole motif){
        this.couleur = couleur;
        this.valeur = valeur;
        this.motif = motif;
    }

    @Override
    public Color getCouleur() {
        return couleur;
    }

    @Override
    public int getValeur() {
        return valeur;
    }

    @Override
    public Symbole getMotif() {
        return motif;
    }

    @Override
    public boolean estCompatible(ICarte c) {
        return this.valeur == c.getValeur()
                || this.couleur.equals(c.getCouleur())
                || this.motif == c.getMotif();
    }

    @Override
    public boolean isVide() {
        return this.couleur.equals(Color.darkGray) || this.valeur == -1 || this.motif == Symbole.VIDE;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof Carte){
            ICarte c = (ICarte) obj;
            equals = this.valeur == c.getValeur()
                    || this.couleur.equals(c.getCouleur())
                    || this.motif == c.getMotif();
        }
        return equals;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "valeur=" + valeur +
                ", couleur=" + couleur +
                ", symbole=" + motif +
                '}';
    }
}
