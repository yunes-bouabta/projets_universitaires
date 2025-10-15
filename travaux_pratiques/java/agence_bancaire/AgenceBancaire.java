import java.util.*;

public class AgenceBancaire {
   private String nomAgence; 	//nom agence
   private String ville; //adresse agence
   private ArrayList<CompteBancaire> ensComptesBancaires; // ArrayList de comptes bancaires de l'agence


    /**
	* Constructeur champ à champ d'une AgenceBancaire avec son nom et adresse.
    * L'ArrayList de CompteBancaire est créée vide.
    * 
    * @param nom nom du propriétaire
    *            
    * @param adresse adresse du compte
    *            
    */

    public AgenceBancaire(String nom,String adresse){
        this.nomAgence=nom;
        this.ville=adresse;
        this.ensComptesBancaires=new ArrayList<CompteBancaire>();
    }

    public String toString() {
        return "adresse : " + this.ville + "\nnom de l'agence : " + this.nomAgence
        + "\nnombre de comptes : "  + this.ensComptesBancaires.size();
    }

    /**
    * Ajoute un compte bancaire (objet classe CompteBancaire)
    * à l'ArrayList des comptes bancaires de l'agence bancaire si le compte n'y est pas déjà
    * 
    * @param cb objet CompteBancaire à ajouter
    * 
    */
    public void add(CompteBancaire cb)  {
        if(!(ensComptesBancaires.contains(cb))){
        this.ensComptesBancaires.add(cb);}
    }

    public boolean compteExiste(CompteBancaire cb){
        return ensComptesBancaires.contains(cb);
    }

    /**
    * Verifie si le nom passé en parametre possède un compte bancaire dans l'ArrayList des objets CompteBancaire de l'Agence
    * @param nom nom à vérifier si compte bancaire dans l'agence
    * 
    * @return true si le nom passé en paramètre à un compte bancaire à son nom dans l'agence, false sinon
    */
    public boolean comptePersonneExiste(String nom){
        for (CompteBancaire compte : ensComptesBancaires) {
            if (compte.getNom().equals(nom)) {return true;}}
            return false; 
    } // on parcourt tous les comptes bancaires de l'ArrayList pour verifier le nom de chacun afin de le comparer à celui en parametre

    /**
     * Crée une ArrayList vide qui sera remplie de comptes bancaires (objet classe CompteBancaire) 
     * ayant comme nom de propritaire le nom passé en paramètre.
     * 
     * @param nom nom de propriétaire
     * 
     * @return ArrayList des comptes bancaires de ce propriétaire
     */
    public ArrayList<CompteBancaire> lesComptesDe(String nom) {
        ArrayList<CompteBancaire> comptesDePersonne = new ArrayList<>();
        
        for (CompteBancaire compte : ensComptesBancaires) {
            if (compte.getNom().equals(nom)) {
                comptesDePersonne.add(compte);
            }
        } 
        /*On parcourt tous les comptes en banque de l'ArrayList de l'Agence
        et on verifie si le nom de chaque compte correspond au nom passé en parametre.
        Dans ce cas là, on ajoute le compte bancaire à l'ArrayList des comptes bancaires de ce propriétaire*/

        if (comptesDePersonne.isEmpty()) {
            System.out.println("Aucun compte trouvé pour " + nom);
        } // verifie si le nom possède au moins un compte dans l'agence

        return comptesDePersonne;
    }
}
