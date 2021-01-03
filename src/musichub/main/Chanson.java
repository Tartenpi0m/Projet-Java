package src.musichub.main;

import java.util.Comparator;

/**
* This class represent a song from the user's perspective.
*/
public class Chanson extends ElementMusicale {

	private String Artiste;
	private Genre Genre;

	/**
	* Chanson's constructor, initialize informations in parameter.
	* @param Titre String Title
	* @param Id int ID
	* @param Artiste String Artist
	* @param Genre Genre types
	*/
	public Chanson(String Titre, int Id, String Artiste, Genre Genre, int Duree) {

		this.Titre = Titre;
		this.Id = Id;
		this.Artiste = Artiste;
		this.Genre = Genre;
		this.Duree = Duree;
	}

	/**
	* Print Title and Artist of the song.
	*/
	public void print() {

		System.out.println(this.Titre + " de " + this.Artiste);

	}

	/**
	* Comparator who is used to sort songs by Genre.
	*/
    public static Comparator<Chanson> ComparatorGenre = new Comparator<Chanson>() {
     
        @Override
        public int compare(Chanson c1, Chanson c2) {
            return c1.Genre.compareTo(c2.Genre);
        }
    };
}
