package jeu.speed;

import jeu.cartes.PaquetCartes;
import jeu.cartes.carte.ICarte;
import jeu.joueur.IJoueur;
import jeu.joueur.Joueur;

public class Speed implements ISpeed {

    private IJoueur joueur1;
    private IJoueur joueur2;
    private ICarte sommet1;
    private ICarte sommet2;
    
    public Speed(String nom1, String nom2){

        PaquetCartes pc = new PaquetCartes();
        joueur1 = new Joueur(nom1, new PaquetCartes(pc, pc.size()/2));
        joueur2 = new Joueur(nom2, new PaquetCartes(pc, pc.size()));

        sommet1=joueur1.remove(0);
        sommet2=joueur2.remove(0);
    }

    public Speed(IJoueur j1, IJoueur j2, ICarte s1, ICarte s2){
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.sommet1 = s1;
        this.sommet2 = s2;
    }

    public  IJoueur getJoueur1() {
        return joueur1;
    }

    public  IJoueur getJoueur2() {
        return joueur2;
    }

    public  ICarte getSommet1() {
        return sommet1;
    }

    public  void setSommet1(ICarte sommet1) {
        this.sommet1 = sommet1;
    }

    public  ICarte getSommet2() {
        return sommet2;
    }

    public  void setSommet2(ICarte sommet2) {
        this.sommet2 = sommet2;
    }
}
