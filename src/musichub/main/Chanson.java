package main;

/**
*Classe Chanson
* 
*@author Antoine R.
*
*/

public class Chanson extends ElementMusicale {

	private String Artiste;
	private Genre Genre;


	public Chanson(String Titre, int Id, String Artiste, Genre Genre) {

		this.Titre = Titre;
		this.Id = Id;
		this.Artiste = Artiste;
		this.Genre = Genre;
	}

	public void print() {

		System.out.println(this.Titre + " de " + this.Artiste);

	}
}