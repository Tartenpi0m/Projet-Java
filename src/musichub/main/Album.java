package src.musichub.main;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

/**
* This class represent an album from the user's perspective.
* This class contains "Chanson" in a CopeOnWriteArrayList.
*/
public class Album extends StructureMusicale {

	private String Artiste;
	//private Inconnue Date; ??????????????????
	private int Date;
	List<Chanson> MusiqueListe = Collections.synchronizedList(new CopyOnWriteArrayList<Chanson>());

	/**
	* Album's constructor, initialize informations in parameter.
	* @param Titre String Title
	* @param Id int ID
	* @param Artiste String Artist
	* @param Date int Date
	*/
	public Album(String Titre, int Id, String Artiste, int Date, int Duree) {

		this.Titre = Titre;
		this.Id = Id;
		this.Artiste = Artiste;
		this.Date = Date;
		this.Duree = Duree;

	}

	/**
	* Print Title and Artist of album.
	*/
	public void printTitle() {
		System.out.println(this.Titre + " de " + Artiste);
	}

	/** 
	* Print all album's songs.
	*/
	public void printAllSong() {

		for(Chanson c : MusiqueListe) {
			c.print();
		}

	}

	/**
	* Comparator which is used to sort albums by date.
	*/
	public static Comparator<Album> ComparatorDate = new Comparator<Album>() {
     
        @Override
        public int compare(Album a1, Album a2) {
            return a1.Date - a2.Date; //doit etre de type int 
        }
    };

    /**
    * Sort album's songs by types.
    */
    public void sortSongByGenre() {

    	Collections.sort(MusiqueListe, Chanson.ComparatorGenre);
    }



}
