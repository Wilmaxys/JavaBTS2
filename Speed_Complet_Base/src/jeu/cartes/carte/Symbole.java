package jeu.cartes.carte;

public enum Symbole {
    MOUSTACHE("mustach"), ETOILE("etoile"), SPAQUETTI("spaget"), CHAT("chaton"), CASSEROLE("caserol"), POIVRON("povron"), KIWI("kiwiii"), VIDE("_vide_");

    private String affichage;

    private static final Symbole[] symboles = Symbole.values();

    Symbole(String affichage) {
        this.affichage = affichage;
    }

    public static Symbole[] getSymboles() {
        return symboles;
    }

    public static int getIndice(Symbole motif){
        int i = 0;

        while (symboles[i] != motif && i < Symbole.values().length){
            i++;
        }

        return i;
    }

    @Override
    public String toString() {
        return affichage;
    }
}
