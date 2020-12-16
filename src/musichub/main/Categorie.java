package main;
/**
*Enumeration des différentes categorie
* (Jeunesse, Roman, Théatre, Discours,  Documentaire)
*@author Antoine R.
*
*/

public enum Categorie {

	JEUNESSE("Jeunesse"), ROMAN("Roman"), THEATRE("Théatre"), DISCOURS("Discours"), DOCUMENTAIRE("Documentaire");
	
	private String cat;

	/**
	*
	*/
	private Categorie(String cat) {

		this.cat = cat;
	}


	public String getCategorie() {

		return this.cat;
	}

}