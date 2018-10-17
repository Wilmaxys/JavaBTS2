package serialiseur;

import jeu.cartes.carte.ICarte;
import jeu.joueur.IJoueur;
import jeu.speed.ISpeed;
import jeu.speed.Speed;

import java.util.StringTokenizer;

public class SerialiseurSpeed implements ISerialiseur<ISpeed> {

    private static final String SEPARATEUR = "!";

    @Override
    public String serialiser(ISpeed element) {
        String s = "";
        ISerialiseur<IJoueur> sj = new SerialiseurJoueur();
        s += sj.serialiser(element.getJoueur1());
        s += SEPARATEUR;
        s += sj.serialiser(element.getJoueur2());
        s += SEPARATEUR;
        ISerialiseur<ICarte> sc = new SerialiseurCarte();
        s += sc.serialiser(element.getSommet1());
        s += SEPARATEUR;
        s += sc.serialiser(element.getSommet2());
        return s;
    }

    @Override
    public ISpeed deserialiser(String s) {
        ISpeed speed = null;

        StringTokenizer st = new StringTokenizer(s, SEPARATEUR);
        String tokentCourant;

        ISerialiseur<IJoueur> sj = new SerialiseurJoueur();
        tokentCourant = st.nextToken();
        IJoueur j1 = sj.deserialiser(tokentCourant);
        tokentCourant = st.nextToken();
        IJoueur j2 = sj.deserialiser(tokentCourant);

        ISerialiseur<ICarte> sc = new SerialiseurCarte();
        tokentCourant = st.nextToken();
        ICarte s1 = sc.deserialiser(tokentCourant);
        tokentCourant = st.nextToken();
        ICarte s2 = sc.deserialiser(tokentCourant);

        speed = new Speed(j1, j2, s1, s2);

        return speed;
    }

    @Override
    public String getSeparateur() {
        return SEPARATEUR;
    }
}
