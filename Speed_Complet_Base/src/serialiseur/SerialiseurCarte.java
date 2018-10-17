package serialiseur;

import jeu.cartes.carte.Carte;
import jeu.cartes.carte.ICarte;
import jeu.cartes.carte.Symbole;

import java.awt.*;

import java.util.StringTokenizer;

public class SerialiseurCarte implements ISerialiseur<ICarte> {

    private static final String SEPARATEUR = "-";
    private static final String SEPARATEUR_COULEUR = "/";

    @Override
    public String serialiser(ICarte element) {
        int indexSymbole = Symbole.getIndice(element.getMotif());

        return element.getCouleur().getRed() + SEPARATEUR_COULEUR + element.getCouleur().getGreen() + SEPARATEUR_COULEUR + element.getCouleur().getBlue() + SEPARATEUR + element.getValeur() + SEPARATEUR + indexSymbole;
    }

    @Override
    public ICarte deserialiser(String s) {
        StringTokenizer st = new StringTokenizer(s, SEPARATEUR);

        String tokenCourant = st.nextToken();
        String[] rgb = tokenCourant.split(SEPARATEUR_COULEUR);

        tokenCourant = st.nextToken();
        int value = Integer.parseInt(tokenCourant);

        tokenCourant = st.nextToken();
        Symbole[] symboles = Symbole.getSymboles();
        int indexSymbole = Integer.parseInt(tokenCourant);


        return new Carte(new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])), value, symboles[indexSymbole]);
    }

    @Override
    public String getSeparateur() {
        return SEPARATEUR;
    }
}
