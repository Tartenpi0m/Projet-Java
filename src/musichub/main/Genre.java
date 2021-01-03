package src.musichub.main;

/**
* This enumeration lists all available types of music. 
* (Jazz, Classique, Hip-Hop, Rock, Pop, Rap)
*/

public enum Genre {

	JAZZ("Jazz"), CLASSIQUE("Classique"), HIPHOP("Hip-Hop"), ROCK("Rock"), POP("Pop"), RAP("Rap"), INCONNU("Inconnu");

	private String gen;

	/*
	* The enumeration's constructor.
	*/
	private Genre(String gen) {

		this.gen = gen;
	}

	/**
	* The enumeration's accessor.
	*/
	public String getGenre() {

		return this.gen;
	}
}
