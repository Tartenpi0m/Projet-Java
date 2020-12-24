package main;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Collections;

/**
*Classe abstraite Structuremusicale qui regroupe les attributs communs entre la classe <strong>Album</strong> et <strong>Playlist</strong>
*@author Antoine R.
*
*/

public class GestionStructureMusicale {

	public static List<Album> ListeAlbum = Collections.synchronizedList(new CopyOnWriteArrayList<Album>());

	private static List<StructureMusicale> ListePlaylist = Collections.synchronizedList(new CopyOnWriteArrayList<StructureMusicale>());


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

	public static void addSongToAlbum(String S,String A) {
		boolean FindAlbum = true;
		boolean FindSong = true;

		for(Chanson s : ListeAlbum.get(0).MusiqueListe) {
			if(s.Titre == S) {
				FindSong = false;

				for(Album a : ListeAlbum) {

					if(a.Titre == A) {
						FindAlbum = false;
						a.MusiqueListe.add(s);
						ListeAlbum.get(0).MusiqueListe.remove(s);
					}
				}
			}
		}

		for(Album a : ListeAlbum) {

			if(a.Titre == A) {
				FindAlbum = false;
			}	
		}

		if(FindAlbum) {
			System.out.println("L'album " + A + " n'existe pas");
		}
		if(FindSong) {
			System.out.println("La chanson " + S + " n'existe pas");
		}




	}

	public static void printSongOfAlbum(String A) {
		for(Album a : ListeAlbum) {
			if(a.Titre == A) {
				
				a.printAllSong();
			}
		}
	}

	public static void sortSongOfAlbumByGenre(String A) {

		for(Album a : ListeAlbum) {
			if(a.Titre == A) {
				
				a.sortSongByGenre();
			}
		}
	}











	public static void addLivreAudio(String Titre, int Id, String Auteur, Langue Langue, Categorie Categorie) {

		LivreAudio la = new LivreAudio(Titre, Id, Auteur, Langue, Categorie);
		ListePlaylist.get(0).MusiqueListe.add(la);

	}

	public static void addPlaylist(String Titre) {

		Playlist p = new Playlist(Titre);
		ListePlaylist.add(p);
	}



	


}