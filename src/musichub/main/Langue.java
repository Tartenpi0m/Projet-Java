package main;
/**
* This enumeration lists all available Languages.
* (Français, Anglais, Italien, Espagnol, Allemand)
*/

public enum Langue {

	FRANCAIS("Français"), ANGLAIS("Anglais"), ITALIEN("Italien"), ESPAGNOL("Espagnol"), ALLEMAND("Allemand"), INCONNU("Inconnu");

	private String lan;

	/*
	* The enumeration's constructor.
	*/
	private Langue(String lan) {

		this.lan = lan;
	}

	/**
	* The enumeration's accessor.
	*/
	public String getLangue() {

		return this.lan;
	}

}