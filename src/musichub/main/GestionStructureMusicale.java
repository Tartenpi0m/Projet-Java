package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
*Classe abstraite Structuremusicale qui regroupe les attributs communs entre la classe <strong>Album</strong> et <strong>Playlist</strong>
*@author Antoine R.
*
*/

public class GestionStructureMusicale {

	public static ArrayList<Album> ListeAlbum = new ArrayList<Album>();

	private static ArrayList<Album> ListeLivreAudio = new ArrayList<Album>();


	public static void addSong(String Titre, int Id, String Artiste, Genre Genre) {
		Chanson c = new Chanson(Titre, Id, Artiste, Genre);
		ListeAlbum.get(0).MusiqueListe.add(c);
		
	}



	public static void addAlbum(String Titre, int Id, String Artiste, int Date) {

		Album a = new Album(Titre, Id, Artiste, Date);
		ListeAlbum.add(a);

	}

	public static void printListAlbum() {

		for(Album a : ListeAlbum) {
			a.printTitle();
		}

	}

	public static void sortListeAlbumByDate() {

		Collections.sort(ListeAlbum, Album.ComparatorDate);
	}

	public static void printAllSong() {

		for(Album a : ListeAlbum) {
			a.printAllSong();
		}
	}




	


}