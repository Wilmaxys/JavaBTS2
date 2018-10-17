package game;

import game.grid.GameGrid;
import game.grid.card.Card;
import game.player.Player;
import game.tools.Keyboard;

public class Controller {

    private Player[] players;
    private GameGrid gameGrid;

    public Controller() {
        players = new Player[2];
        gameGrid = new GameGrid();

        System.out.println(gameGrid);

        gameGrid.sleepAll();

        // Get players
        System.out.println("Entrez le prenom du premier joueur :");
        players[0] = new Player(Keyboard.getString());

        System.out.println("Entrez le prenom du deuxième joueur :");
        players[1] = new Player(Keyboard.getString());

        // Enter main game loop
        gameLoop();
    }

    private void gameLoop() {
        int i = 0;
        while (gameGrid.allDisplayed()){

            int firstCardIndex;
            int secondCardIndex;
            boolean compatible;
            do {
                printGrid(i);

                firstCardIndex = getCardIndex(-1);
                printGrid(i);

                secondCardIndex = getCardIndex(firstCardIndex);
                printGrid(i);

                compatible = gameGrid.pick(firstCardIndex).isCompatible(gameGrid.pick(secondCardIndex));

                if (compatible){
                    players[i].incrementScore();
                    gameGrid.set(firstCardIndex, new Card());
                    gameGrid.set(secondCardIndex, new Card());

                    System.out.println("Bravo, les cartes sont bonnes ! Vous avez maintenant " + players[i].getScore() + " points");
                }
                else {
                    gameGrid.sleep(firstCardIndex);
                    gameGrid.sleep(secondCardIndex);

                    System.out.println("Dommage...");
                }
                Keyboard.getString();

            }while (compatible);

            i = i == 1 ? 0 : 1;
        }


        System.out.println(gameGrid);

        if (players[0].getScore() == players[1].getScore()){
            System.out.println("Bravo vous deux c'est l'égalité !");
        }
        else if (players[0].getScore() < players[1].getScore()){
            System.out.println("Bravo " + players[1].getName() + " vous avez gagné avec un score de " + players[1].getScore() + " points !");
        }
        else {
            System.out.println("Bravo " + players[0].getName() + " vous avez gagné avec un score de " + players[0].getScore() + " points !");
        }
    }

    private int getCardIndex(int previousIndex) {
        int i;
        do {
            System.out.println("Veuillez saisir l'identifiant de la carte souhaîtée :");
            i = Keyboard.getInt();
            i--;

            if (i > 51 || i < 0){
                System.out.println("Veuillez saisir un nombre entier entre 1 et 52");
                i = -1;
            }
            else if (!gameGrid.pick(i).toDisplay()) {
                System.out.println("Veuillez choisir une carte qui n'a pas déjà été joué");
                i = -1;
            }
            else if (previousIndex != -1 && i == previousIndex){
                System.out.println("Veuillez choisir une autre carte que celle choisie la première fois");
                i = -1;
            }
        }while (i < 0);

        return i;
    }

    private void printGrid(int playerIndex) {
        System.out.println("C'est à " + players[playerIndex].getName() + " de jouer :\n");
        System.out.println(gameGrid);
    }
}
