package main;

/**
*Enumeration des différents genres de musique
* (Jazz, Classique, Hip-Hop, Rock, Pop, Rap)
*@author Antoine R.
*
*/

public enum Genre {

	JAZZ("Jazz"), CLASSIQUE("Classique"), HIPHOP("Hip-Hop"), ROCK("Rock"), POP("Pop"), RAP("Rap"), INCONNU("Inconnu");

	private String gen;

	private Genre(String gen) {

		this.gen = gen;
	}

	public String getGenre() {

		return this.gen;
	}
}