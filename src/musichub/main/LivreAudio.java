package main;
/**
* * This class represent an audio book from the user's perspective.
*/

public class LivreAudio extends ElementMusicale {

	private String Auteur;
	private Langue Langue;
	private Categorie Categorie;


	/**
	* Audio book's constructor, initialize informations in parameter.
	* @param Titre String Title
	* @param Id int ID
	* @param Auteur String author
	* @param Langues Langue Languages
	* @param Categorie Categorie types
	*/
	public LivreAudio(String Titre, int Id, String Auteur, Langue Langue, Categorie Categorie) {
	
		this.Titre = Titre;
		this.Id = Id;
		this.Auteur = Auteur;
		this.Langue = Langue;
		this.Categorie = Categorie;

	}

	/** 
	* print title and auhor of the audio book.
	*/
	public void print() {

		System.out.println(this.Titre + " de " + this.Auteur);

	}

}