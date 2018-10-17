package controleur;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ihm.FenetreHote;
import ihm.Touches;
import jeu.cartes.carte.Carte;
import jeu.cartes.carte.ICarte;
import jeu.speed.Speed;
import serialiseur.SerialiseurSpeed;

public class Controleur implements KeyListener {

	private static final int ERREUR_FRAPPE = KeyEvent.VK_A;
	private static final int TOUCHE1_J1 = Touches.TOUCHE1_J1;
	private static final int TOUCHE2_J1 = Touches.TOUCHE2_J1;
	private static final int TOUCHE3_J1 = Touches.TOUCHE3_J1;
	private static final int TOUCHE1_J2 = Touches.TOUCHE1_J2;
	private static final int TOUCHE2_J2 = Touches.TOUCHE2_J2;
	private static final int TOUCHE3_J2 = Touches.TOUCHE3_J2;

	private static final int POS_C1_J1 = 3;
	private static final int POS_C2_J1 = 4;
	private static final int POS_C3_J1 = 5;
	private static final int POS_C1_J2 = 6;
	private static final int POS_C2_J2 = 7;
	private static final int POS_C3_J2 = 8;

	private static boolean stop = false;


	private static Speed jeu;
	private FenetreHote ihm ;

	/**
	 * Le constructeur du contr�leur initialise la partie (deux joueurs = deux paquets) et deux sommets de tas.
	 * Puis, il cr�e une fen�tre h�te et lui transmet l'information � afficher.
	 */
	public Controleur() {
		super();
		jeu = new Speed("Bobby", "Jackson");
		
		System.out.println(jeu.getJoueur1().size());
		System.out.println(jeu.getJoueur2().size());

		SerialiseurSpeed ss = new SerialiseurSpeed();
		String jString = ss.serialiser(jeu);
		System.out.println(jString);
		
		ihm = new FenetreHote();
		this.afficherCartes();

		ihm.setNoms(jeu.getJoueur1().getNom(), jeu.getJoueur2().getNom());

		/* Activer le contr�leur de touche frapp�e */
		ihm.addKeyListener(this);
	}

	private void afficherCartes() {
		ihm.setSommets(jeu.getSommet1().getMotif().toString(), Controleur.getCouleur(jeu.getSommet1()), jeu.getSommet1().getValeur(), jeu.getSommet2().getMotif().toString(), getCouleur(jeu.getSommet2()), jeu.getSommet2().getValeur());
		ICarte carte1=jeu.getJoueur1().get(0);
		ICarte carte2=jeu.getJoueur1().get(1);
		ICarte carte3=jeu.getJoueur1().get(2);
		ihm.setCartesJoueur1(carte1.getMotif().toString(), getCouleur(carte1), carte1.getValeur(), carte2.getMotif().toString(), getCouleur(carte2), carte2.getValeur(), carte3.getMotif().toString(), getCouleur(carte3), carte3.getValeur());
		carte1=jeu.getJoueur2().get(0);
		carte2=jeu.getJoueur2().get(1);
		carte3=jeu.getJoueur2().get(2);
		ihm.setCartesJoueur2(carte1.getMotif().toString(), getCouleur(carte1), carte1.getValeur(), carte2.getMotif().toString(), getCouleur(carte2), carte2.getValeur(), carte3.getMotif().toString(), getCouleur(carte3), carte3.getValeur());
	}

	private static Color getCouleur(ICarte carte) {
		return carte.getCouleur();
	}

	/**
	 * Cette proc�dure indique la marche � suivre quand une touche est enfonc�e
	 */
	public void keyPressed (final KeyEvent e)
	{
		// La gestion du bool�en stop permet d'�viter de garder une touche enfonc�e
		if (!stop) {
			stop = true;
			int toucheFrappee=e.getKeyCode();

			/* La fonction getPositionCarte permet de transformer une touche frapp�e,
			un caract�re, en une des positions figur�es sur la table de jeu.
			 */

			int position = Controleur.getPositionCarte(toucheFrappee);

			/*
			position indique quelle carte a �t� choisie par le clavier.
			Les cartes du joueur 1 sont en position 3-5,
			celles du joueur 2 en position 6-8

			Avant de prendre en compte le choix d'une carte, on v�rifie qu'elle
			est compatible avec la carte du sommet d'un des tas. Si c'est le cas,
			on la supprime de la main du joueur et on la place sur le sommet du tas.

			Il est possible que les deux tas puissent accueillir la carte jou�e.
			Le programme privil�gie le tas 1 pour le joueur 1 et le tas 2 pour le joueur 2
			 */
			switch (position) {
			case POS_C1_J1 :
				// nouveauSommet va prendre la valeur de la 1�re carte du joueur 1 si celle-ci peut �tre pos�e sur le sommet 1 courant.
				// nouveauSommet reste null sinon.
				ICarte nouveauSommet = jeu.getJoueur1().testerCarteSommet(0,jeu.getSommet1(),jeu.getJoueur2());
				if (nouveauSommet==null) {
					// nouveauSommet va prendre la valeur de la 1�re carte du joueur 1 si celle-ci peut �tre pos�e sur le sommet 2 courant.
					// nouveauSommet reste null sinon.
					nouveauSommet = jeu.getJoueur1().testerCarteSommet(0,jeu.getSommet2(),jeu.getJoueur2());
					if (nouveauSommet==null) {
						// le joueur a fait une erreur, il va �tre p�nalis� et le joueur 2 va perdre toute �ventuelle p�nalit�
						jeu.getJoueur1().gererErreur(jeu.getJoueur2());
					}
					else {
						// la carte va sur le sommet 2 et a �t� �t�e du jeu du joueur 1
						jeu.setSommet2(nouveauSommet);
					}
				}
				else {
					// la carte va sur le sommet 1 et a �t� �t�e du jeu du joueur 1
					jeu.setSommet1(nouveauSommet);
				}
				break;

			case POS_C2_J1 :
				nouveauSommet = jeu.getJoueur1().testerCarteSommet(1,jeu.getSommet1(),jeu.getJoueur2());
				if (nouveauSommet==null) {
					nouveauSommet = jeu.getJoueur1().testerCarteSommet(1,jeu.getSommet2(),jeu.getJoueur2());
					if (nouveauSommet==null) {
						jeu.getJoueur1().gererErreur(jeu.getJoueur2());
					}
					else {
						jeu.setSommet2(nouveauSommet);
					}
				}
				else {
					jeu.setSommet1(nouveauSommet);
				}

				break;
			case POS_C3_J1 :
				nouveauSommet = jeu.getJoueur1().testerCarteSommet(2,jeu.getSommet1(),jeu.getJoueur2());
				if (nouveauSommet==null) {
					nouveauSommet = jeu.getJoueur1().testerCarteSommet(2,jeu.getSommet2(),jeu.getJoueur2());
					if (nouveauSommet==null) {
						jeu.getJoueur1().gererErreur(jeu.getJoueur2());
					}
					else {
						jeu.setSommet2(nouveauSommet);
					}
				}
				else {
					jeu.setSommet1(nouveauSommet);
				}


				break;
			case POS_C1_J2 :
				nouveauSommet = jeu.getJoueur2().testerCarteSommet(0,jeu.getSommet1(),jeu.getJoueur1());
				if (nouveauSommet==null) {
					nouveauSommet = jeu.getJoueur2().testerCarteSommet(0,jeu.getSommet2(),jeu.getJoueur1());
					if (nouveauSommet==null) {
						jeu.getJoueur2().gererErreur(jeu.getJoueur1());
					}
					else {
						jeu.setSommet2(nouveauSommet);
					}
				}
				else {
					jeu.setSommet1(nouveauSommet);
				}

				break;
			case POS_C2_J2:
				nouveauSommet = jeu.getJoueur2().testerCarteSommet(1,jeu.getSommet1(),jeu.getJoueur1());
				if (nouveauSommet==null) {
					nouveauSommet = jeu.getJoueur2().testerCarteSommet(1,jeu.getSommet2(),jeu.getJoueur1());
					if (nouveauSommet==null) {
						jeu.getJoueur2().gererErreur(jeu.getJoueur1());
					}
					else {
						jeu.setSommet2(nouveauSommet);
					}
				}
				else {
					jeu.setSommet1(nouveauSommet);
				}
				break;
			case POS_C3_J2 :
				nouveauSommet = jeu.getJoueur2().testerCarteSommet(2,jeu.getSommet1(),jeu.getJoueur1());
				if (nouveauSommet==null) {
					nouveauSommet = jeu.getJoueur2().testerCarteSommet(2,jeu.getSommet2(),jeu.getJoueur1());
					if (nouveauSommet==null) {
						jeu.getJoueur2().gererErreur(jeu.getJoueur1());
					}
					else {
						jeu.setSommet2(nouveauSommet);
					}
				}
				else {
					jeu.setSommet1(nouveauSommet);
				}
				break;
			default: // C'�tait une mauvaise touche ou la touche d'un joueur bloqu�
				break;
			}
			// les mains sont �ventuellement compl�t�es par des cartes vides pour toujours pouvoir afficher 3 cartes, ce qui simplifie la lecture du code et permet de varier les constructeurs sur ce TP
			if (jeu.getJoueur1().size()==2) {				jeu.getJoueur1().add(new Carte());			}
			if (jeu.getJoueur2().size()==2) {				jeu.getJoueur2().add(new Carte());			}
			if (jeu.getJoueur1().gagne()) {ihm.setGagnant(jeu.getJoueur1().getNom());}
			if (jeu.getJoueur2().gagne()) {ihm.setGagnant(jeu.getJoueur2().getNom());}

			this.afficherCartes();
			
			// Cet appel permet de r�afficher l'�tat actuel du jeu.
			ihm.repaint();
		}
	}

	/**
	 * Cette proc�dure indique la marche � suivre lorsqu'une touche est rel�ch�e.
	 */
	public void keyReleased (final KeyEvent e)
	{

		//code pour un key released  

		// Si personne n'a gagn�, on permet de retaper une touche au clavier -stop passe � faux-
		// sinon, la partie est termin�e.
		if (!jeu.getJoueur1().gagne() && !jeu.getJoueur2().gagne()) {stop=false;}
	}

	public void keyTyped (final KeyEvent e)
	{
		//code pour un key typed  
	} 

	
	
	/**
	 * La touche est prise en compte si le joueur n'a pas de penalite. 
	 * @param toucheFrappee
	 * @return
	 */
	private static int getPositionCarte(final int toucheFrappee) {
		int rep = ERREUR_FRAPPE;
		if (jeu.getJoueur1().sansPenalite()) {
			switch (toucheFrappee) {
			case TOUCHE1_J1 : rep = POS_C1_J1;			break;
			case TOUCHE2_J1 : rep = POS_C2_J1;			break;
			case TOUCHE3_J1 : rep = POS_C3_J1;			break;
			default: break;
			}
		}

		if (jeu.getJoueur2().sansPenalite()) {
			switch (toucheFrappee) {
			case TOUCHE1_J2 : rep = POS_C1_J2;			break;
			case TOUCHE2_J2 : rep = POS_C2_J2;			break;
			case TOUCHE3_J2 : rep = POS_C3_J2;			break;
			default: break;
			}
		}

		return rep;
	}


}
