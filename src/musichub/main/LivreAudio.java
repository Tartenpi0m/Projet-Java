package src.musichub.main;

/**
*Classe Livre Audio
* 
*@author Antoine R.
*
*/

public class LivreAudio extends ElementMusicale {

	private String Auteur;
	private Langue Langue;
	private Categorie Categorie;


	public LivreAudio(String Titre, int Id, String Auteur, Langue Langue, Categorie Categorie) {
	
		this.Titre = Titre;
		this.Id = Id;
		this.Auteur = Auteur;
		this.Langue = Langue;
		this.Categorie = Categorie;

	}


	public void print() {

		System.out.println(this.Titre + " de " + this.Auteur);

	}

}
