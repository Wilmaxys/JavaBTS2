package jeu.cartes.carte;

import java.awt.Color;
import java.io.Serializable;

public interface ICarte extends Serializable {

	public Color getCouleur() ;

	public int getValeur() ;

	public Symbole getMotif();
	
	public boolean estCompatible(ICarte c);
	
	public boolean isVide();

}
