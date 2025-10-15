public class CompteBancaire {

	/**
	 * nom du propriétaire du compte
	 */
	private String nom;
	/**
	 * solde du compte
	 */
	private float solde;
	/**
	 * numero du compte 
	 */
	private String numero;

	/**
	 * Constructeur de CompteBancaire avec les valeurs données en
	 * paramètre.
	 * 
	 * @param nom
	 *            nom du propriétaire
	 * @param solde
	 *            solde du compte
	 * @param numero
	 *            numero du compte
	 */
	public CompteBancaire(String nom, float solde, String numero) {
		this.nom = nom;
		this.solde = solde;
		this.numero = numero;
	}

	/**
	 * Constructeur vide de CompteBancaire, solde = 0.
	 * 
	 * @param nom
	 *            le nom du propriétaire
	 * @param numero
	 *            le numero du compte
	 */
	public CompteBancaire(String nom, String numero) {
		this.nom = nom;
		this.solde = 0;
		this.numero = numero;
	}

	public String toString() {
		return new String("Compte de nom  " + nom + " de solde " + solde
				+ "  de numero " + numero);
	}

	/**
	 * getter du nom du propriétaire
	 * 
	 * @return le nom du propriétaire
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * getter du numéro du compte
	 * 
	 * @return le numéro du compte
	 */
	public String getNumero() {
		return this.numero;
	}

	/**
	 * getter du solde du compte
	 * 
	 * @return le solde du compte
	 */
	public float getSolde() {
		return this.solde;
	}

	/**
	 * setter du nom du propriétaire du compte par parametre nom
	 * 
	 * @param nom
	 * nouveau nom du propriétaire
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * setter du solde du compte par parametre solde
	 * 
	 * @param solde
	 * nouveau solde du compte
	 */
	public void setSolde(float solde) {
		this.solde = solde;
	}

	/**
	 * ajoute une somme au solde du compte si elle est positive,
	 * sinon affiche un message d'erreur
	 * 
	 * @param somme somme à ajouter
	 * @return false si somme est négative, true sinon
	 */
	public boolean ajouteSolde(float somme) {
		if (somme >= 0) {
			this.solde += somme;
			return true;
		} else {
			System.out.println("ajout negatif !");
			return false;
		}
	}

	/**
	 * retire une somme (retrait) au solde si le retrait est positif et si le compte a un solde suffisant, 
	 * sinon affiche un message d'erreur
	 * 
	 * @param retrait somme à retirer
	 */
	public boolean retireSolde(float retrait) {
		if (retrait >= 0) {
			if (this.getSolde() >= retrait)	{
				this.getSolde() -= retrait;
				return true;
				}
			else {	System.out.println("retrait refusé, fonds manquant");
				return false;
				}		
		} else {
			System.out.println("retrait negatif !");
			return false;
		}
	}

	/**
	 * compare si deux comptes en banque (de la classe CompteBancaire) ont le même nom de propriétaire
	 * 
	 * @param cb1 objet CompteBancaire à comparer
	 * @return false si cb1 est null, si cb1 n'est pas un compte bancaire ou si les deux propriétaires ont un nom différent, sinon true
	 */
   	public boolean Equals(CompteBancaire cb1){
      	if (cb1 == null || cb1.getClass() != this.getClass()){
			return false;
		}
      	return this.getNom().equals(cb1.getNom()) && this.getNumero() == cb1.getNumero();
   }
}
