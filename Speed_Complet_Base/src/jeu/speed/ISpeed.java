package jeu.speed;

import jeu.cartes.carte.ICarte;
import jeu.joueur.IJoueur;

public interface ISpeed {

    public IJoueur getJoueur1();

    public IJoueur getJoueur2();

    public ICarte getSommet1();

    public void setSommet1(ICarte sommet1);

    public ICarte getSommet2();

    public void setSommet2(ICarte sommet2);
}
