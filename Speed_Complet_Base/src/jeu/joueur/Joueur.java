package jeu.joueur;

import jeu.cartes.IPaquet;
import jeu.cartes.PaquetCartes;
import jeu.cartes.carte.ICarte;

public class Joueur implements IJoueur {

    private static final int PENALITE_MAX = 3;
    private static final int PENALITE_MIN = 0;

    private String nom;
    private IPaquet<ICarte> paquet;
    private int penalite;

    public Joueur(String nom, IPaquet<ICarte> paquet){
        this.nom = nom;
        this.paquet = paquet;
        this.penalite = 0;
    }

    public Joueur(String nom, IPaquet<ICarte> paquet, int penalite) {
        this.nom = nom;
        this.paquet = paquet;
        this.penalite = penalite;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getPenalite() {
        return penalite;
    }

    @Override
    public IPaquet getPaquet(){
        return paquet;
    }

    @Override
    public boolean add(ICarte c) {
        return paquet.add(c);
    }

    @Override
    public ICarte get(int i) {
        return (ICarte) paquet.get(i);
    }

    @Override
    public ICarte remove(int i) {
        return (ICarte) paquet.remove(i);
    }

    @Override
    public int size() {
        return paquet.size();
    }

    @Override
    public ICarte testerCarteSommet(int i, ICarte c, IJoueur j2) {
        return ((PaquetCartes) paquet).testerCarteSommet(i, c, j2);
    }

    @Override
    public void gererErreur(IJoueur j2) {
        this.penalite = PENALITE_MAX;
        j2.annulerPenalite();
    }

    @Override
    public void oterEventuellementUnePenalite() {
        if (!sansPenalite()){
            penalite--;
        }
    }

    @Override
    public boolean gagne() {
        return ((PaquetCartes) paquet).estVide();
    }

    @Override
    public boolean sansPenalite() {
        return penalite <= PENALITE_MIN;
    }

    @Override
    public void annulerPenalite() {
        penalite = PENALITE_MIN;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                paquet +
                '}';
    }
}
