package main;
/**
*Enumeration des différentes Langues
* (Français, Anglais, Italien, Espagnol, Allemand)
*@author Antoine R.
*
*/

public enum Langue {

	FRANCAIS("Français"), ANGLAIS("Anglais"), ITALIEN("Italien"), ESPAGNOL("Espagnol"), ALLEMAND("Allemand");

	private String lan;

	private Langue(String lan) {

		this.lan = lan;
	}


	public String getLangue() {

		return this.lan;
	}

}