package serialiseur;

import jeu.cartes.IPaquet;
import jeu.cartes.carte.ICarte;
import jeu.joueur.IJoueur;
import jeu.joueur.Joueur;

import java.util.StringTokenizer;

public class SerialiseurJoueur implements ISerialiseur<IJoueur> {

    private static final String SEPARATEUR = "#";

    @Override
    public String serialiser(IJoueur element) {
        SerialiseurPaquet serialiseurPaquet = new SerialiseurPaquet();

        return element.getNom() + SEPARATEUR + serialiseurPaquet.serialiser(element.getPaquet()) + SEPARATEUR + element.getPenalite();
    }

    @Override
    public IJoueur deserialiser(String s) {
        IJoueur j = null;

        StringTokenizer st = new StringTokenizer(s, SEPARATEUR);
        String tokenCourant;
        tokenCourant = st.nextToken();
        String nom = tokenCourant;

        ISerialiseur<IPaquet> serialiseurPaquet = new SerialiseurPaquet();
        tokenCourant = st.nextToken();
        IPaquet<ICarte> paquet = serialiseurPaquet.deserialiser(tokenCourant);

        tokenCourant = st.nextToken();
        int penalite = Integer.parseInt(tokenCourant);

        j = new Joueur(nom, paquet, penalite);

        return j;
    }

    @Override
    public String getSeparateur() {
        return SEPARATEUR;
    }
}
