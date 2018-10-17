package serialiseur;

import jeu.cartes.IPaquet;
import jeu.cartes.PaquetCartes;
import jeu.cartes.carte.ICarte;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SerialiseurPaquet implements ISerialiseur<IPaquet> {

    private static final String SEPARATEUR = "\n";

    @Override
    public String serialiser(IPaquet element) {
        String returnString = "";

        ISerialiseur<ICarte> serialiseurCarte = new SerialiseurCarte();

        for (int i = 0; i < element.size() - 1; i++){
            returnString += serialiseurCarte.serialiser((ICarte) element.get(i)) + SEPARATEUR;
        }

        returnString += serialiseurCarte.serialiser((ICarte) element.get(element.size() - 1));

        return returnString;
    }

    @Override
    public IPaquet deserialiser(String s) {
        StringTokenizer st = new StringTokenizer(s, SEPARATEUR);
        ISerialiseur<ICarte> serialiseurCarte = new SerialiseurCarte();

        List<ICarte> paquet = new ArrayList<ICarte>();

        while (st.hasMoreTokens()){
            paquet.add(serialiseurCarte.deserialiser(st.nextToken()));
        }

        return new PaquetCartes(paquet);
    }

    @Override
    public String getSeparateur() {
        return SEPARATEUR;
    }
}
