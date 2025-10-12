import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partie {
    private Joueur joueurBlanc;
    private Joueur joueurNoir;
    private Echiquier echiquier;
    private Joueur joueurActuel;

    public Partie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom joueur blanc : ");
        joueurBlanc = new Joueur(scanner.nextLine(), "blanc");

        System.out.print("Nom joueur noir : ");
        joueurNoir = new Joueur(scanner.nextLine(), "noir");

        joueurActuel = joueurBlanc;
        echiquier = new Echiquier();
    }

    private boolean estEchec(Joueur joueur) {
        Case roiCase = null;

        // Trouver case du roi
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = echiquier.getCase(x, y).getPiece();
                if (p != null && p instanceof Roi && p.getCouleur().equals(joueur.getCouleur())) {
                    roiCase = echiquier.getCase(x, y);
                }
            }
        }

        if (roiCase == null)
            return true; // plus de roi donc mat

        // Piece qui attaque roi?
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece attaquant = echiquier.getCase(x, y).getPiece();
                if (attaquant != null && !attaquant.getCouleur().equals(joueur.getCouleur())) {
                    if (attaquant.deplacement(roiCase)) {
                        return true; // roi attaqué
                    }
                }
            }
        }

        return false;
    }

    private boolean aUnCoupLegal(Joueur joueur) {
        for (int depuisX = 0; depuisX < 8; depuisX++) {
            for (int depuisY = 0; depuisY < 8; depuisY++) {
                Case depart = echiquier.getCase(depuisX, depuisY);
                Piece piece = depart.getPiece();

                if (depart.estVide() || !piece.getCouleur().equals(joueur.getCouleur())) {
                    continue;
                } // Si la case actuelle est vide ou appartient a l'adversaire, on l'ignore.

                for (int versX = 0; versX < 8; versX++) {
                    for (int versY = 0; versY < 8; versY++) {
                        Case arrivee = echiquier.getCase(versX, versY);
                        if (arrivee == depart)
                            continue; // Pas de deplacement sur soi-même.

                        // On sauvegarde la piece dans la case actuelle de la boucle
                        Piece cible = arrivee.getPiece();

                        if (piece.deplacement(arrivee) &&
                                (cible == null || !cible.getCouleur().equals(piece.getCouleur()))) {

                            // Simuler coup si la case ciblée est vide ou si sa couleur est celle de
                            // l'adversaire afin de savoir si échec
                            depart.setPiece(null);
                            arrivee.setPiece(piece);
                            piece.setPosition(arrivee);

                            boolean echecApres = estEchec(joueur);

                            // Annuler coup simulé
                            depart.setPiece(piece);
                            arrivee.setPiece(cible);
                            piece.setPosition(depart);

                            if (!echecApres) {
                                return true; // coup légal car pas d'échec
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean cheminEstLibre(Piece piece, Case destination) {
        // On recupere les coordonnées d'arrivée et départ.
        int depuisX = piece.getPosition().getX();
        int depuisY = piece.getPosition().getY();
        int versX = destination.getX();
        int versY = destination.getY();

        int x = versX - depuisX;
        int y = versY - depuisY;

        // On établit le sens de la direction, -1 pour aller à gauche, 0 rester droit et
        // 1 à droite
        int directionX;
        if (x > 0)
            directionX = 1;
        else if (x < 0)
            directionX = -1;
        else
            directionX = 0;

        int directionY;
        if (y > 0)
            directionY = 1;
        else if (y < 0)
            directionY = -1;
        else
            directionY = 0;

        // Le cavalier passe au dessus des pièces
        if (piece instanceof Cavalier)
            return true;

        // Si le pion progresse de 2 cases
        if (piece instanceof Pion) {
            int direction;
            if (piece.getCouleur().equals("blanc"))
                direction = 1; // blanc en bas donc on monte, la valeur de y augmente
            else
                direction = -1; // noir en haut donc on descend, la valeur de y diminue

            if (Math.abs(versY - depuisY) == 2) {
                int entreY = depuisY + direction; // la ligne entre les deux cases parcourue par le pion
                Case caseMilieu = echiquier.getCase(depuisX, entreY);
                if (caseMilieu != null && !caseMilieu.estVide()) {
                    return false; // false est retourné quand la case du milieu n'est pas vide et a une piece.
                }
            }
            return true;
        }

        // Pour le Fou, la Dame et la Tour
        int x2 = depuisX + directionX;
        int y2 = depuisY + directionY;

        while (x2 != versX || y2 != versY) {
            Case intermediaire = echiquier.getCase(x2, y2);
            if (intermediaire != null && !intermediaire.estVide()) {
                return false;
            }
            x2 += directionX;
            y2 += directionY;
        } /*
           * Tant qu'on est pas arrivé à la ligne ou colonne d'arrivée, on recupere chaque
           * case parcourue
           * pour verifier si elle est vide, si c'est pas le cas, on retourne false
           */

        return true;
    }

    public void demarrer() {
        Scanner scanner = new Scanner(System.in);
        List<String> historiqueCoups = new ArrayList<>();
        while (true) {
            echiquier.afficher();
            System.out.println("Tour de " + joueurActuel.getNom() + " (" + joueurActuel.getCouleur() + ")");
            System.out
                    .print("Saisissez la case de départ, espace puis la case d'arrivée,\n'coups' pour avoir la liste de tous les coups joués,\nou 'exit' pour quitter : ");
            String saisie = scanner.nextLine().toLowerCase().trim();

            if (saisie.equals("exit")) {
                System.out.println("Partie finie.");
                break;
            }
            if (saisie.equals("coups")) {
                for (String coup : historiqueCoups) {
                    System.out.println(coup);
                }
                continue;
            }
            /*
             * Verifie la combinaison écrite, doit être de 5 caracteres,
             * la 1ere case doit être une lettre, la 2e un chiffre, la 3e un espace,
             * la 4e une lettre et la 5e un chiffre.
             */
            if (saisie.length() != 5 ||
                    saisie.charAt(2) != ' ' ||
                    saisie.charAt(0) < 'a' || saisie.charAt(0) > 'h' ||
                    saisie.charAt(1) < '1' || saisie.charAt(1) > '8' ||
                    saisie.charAt(3) < 'a' || saisie.charAt(3) > 'h' ||
                    saisie.charAt(4) < '1' || saisie.charAt(4) > '8') {

                System.out.println("Saisie invalide. exemple de saisie valide : e2 e4");
                continue;
            }
            
            /*
             * On utilise la soustraction des lettres par a et des chiffres par 1
             * pour obtenir un chiffre correspondant à la ligne 1 ou la colonne 2 par
             * exemple.
             * Si on écrit e2 e4, depuisX='e'-'a'=4-1=3 et e est bien la colonne d'index 3
             */
            int depuisX = saisie.charAt(0) - 'a';
            int depuisY = saisie.charAt(1) - '1';
            int versX = saisie.charAt(3) - 'a';
            int versY = saisie.charAt(4) - '1';

            Case depart = echiquier.getCase(depuisX, depuisY);
            Case arrivee = echiquier.getCase(versX, versY);

            if (depart == null || arrivee == null) {
                System.out.println("Case hors échiquier.");
                continue;
            }

            if (depart.estVide()) {
                System.out.println("Pas de pièce ici.");
                continue;
            }

            Piece piece = depart.getPiece();

            if (!piece.getCouleur().equals(joueurActuel.getCouleur())) {
                System.out.println("Pas votre pièce.");
                continue;
            }

            if (!piece.deplacement(arrivee)) {
                System.out.println("Déplacement impossible pour cette pièce.");
                continue;
            }

            if (!arrivee.estVide()) {
                Piece cible = arrivee.getPiece();
                if (cible.getCouleur().equals(piece.getCouleur())) {
                    System.out.println("C'est votre propre pièce.");
                    continue;
                } else {
                    System.out.println("Mangée !");
                }
            }

            if (!cheminEstLibre(piece, arrivee)) {
                System.out.println("Une pièce bloque le chemin.");
                continue;
            }

            // Déplacement après conditions vérifiées
            arrivee.setPiece(piece);
            depart.setPiece(null);
            piece.setPosition(arrivee);
            historiqueCoups.add(saisie+"  ("+joueurActuel.getNom()+")"); //coup validé donc ajout dans le tableau de l'historique des coups

            // Changement de joueur
            if (joueurActuel == joueurBlanc) {
                joueurActuel = joueurNoir;
            } else {
                joueurActuel = joueurBlanc;
            }
            // Vérification de fin de partie
            if (estEchec(joueurActuel)) {
                if (!aUnCoupLegal(joueurActuel)) {
                    if (joueurActuel == joueurBlanc) {
                        System.out.println("Échec et mat ! " + joueurNoir.getNom() + " a gagné !");
                    } else {
                        System.out.println("Échec et mat ! " + joueurBlanc.getNom() + " a gagné !");
                    }
                    break; // fin de condition car partie finie
                } else {
                    System.out.println("Échec !");
                }
            } else {
                if (!aUnCoupLegal(joueurActuel)) {
                    System.out.println("Pat ! Match nulle."); // Si joueur pas en echec mais n'a pas de coup légal, 
                    break;
                }
            }
        }
    }
}
