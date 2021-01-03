package src.musichub.main;

/**
* This enumeration lists all available types of audio books.
* (Jeunesse, Roman, Théatre, Discours,  Documentaire)
*@author Antoine R.
*
*/

public enum Categorie {

	JEUNESSE("Jeunesse"), ROMAN("Roman"), THEATRE("Théatre"), DISCOURS("Discours"), DOCUMENTAIRE("Documentaire"), INCONNU("Inconnu");
	
	private String cat;

	/*
	* The enumeration's constructor.
	*/
	private Categorie(String cat) {

		this.cat = cat;
	}

	/**
	* The enumeration's accessor.
	*/
	public String getCategorie() {

		return this.cat;
	}

}
