package src.musichub.main;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
* This class contains all musical structure's and can operate on them and on the song and Livre audio they contains.
* This class can add elements and structures, print and sort them.
* In ListeAlbum, the first one have "No Album" as title. It contains song who doesn't have album (yet).
* 
*/
public abstract class GestionStructureMusicale implements OperationSurObjetMusicale {

	private static List<Album> ListeAlbum = Collections.synchronizedList(new CopyOnWriteArrayList<Album>());

	private static List<Playlist> ListePlaylist = Collections.synchronizedList(new CopyOnWriteArrayList<Playlist>());

	/**
	* Add a song in the first album (called "No Album") of ListeAlbum using the song's constructor.
	* @param Titre String Title give to the constructor.
	* @param Id int ID give to the constructor.
	* @param Artiste String Artist give to the constructor.
	* @param Genre Genre types give to the constructor.
	*/
	public static void addSong(String Titre, int Id, String Artiste, Genre Genre, int Duree) {
		System.out.println("La chanson " + Titre + " a bien été ajoutée");
		Chanson c = new Chanson(Titre, Id, Artiste, Genre, Duree);
		ListeAlbum.get(0).MusiqueListe.add(c);
		
	}


	/**
	* Add an album using the album's constructor.
	* @throws AlreadyExistException If the Album already exist, throws AlreadyExistException
	* @param Titre String Title give to the constructor.
	* @param Id int ID give to the constructor.
	* @param Artiste String Artist give to the constructor.
	* @param Date int Date give to the constructor.
	*/
	public static void addAlbum(String Titre, int Id, String Artiste, int Date, int Duree) throws AlreadyExistException {

		for(Album play : GestionStructureMusicale.ListeAlbum) {
			if(play.Titre.equals(Titre)) {
				throw new AlreadyExistException("L'album " + Titre + " existe déjà.");
			}
		}
		
		Album a = new Album(Titre, Id, Artiste, Date, Duree);
		ListeAlbum.add(a);
		System.out.println("L'album " + Titre + " a bien été ajouté.");

	}

	/**
	* Print the list of all Album (which are contains in ListeAlbum) except the "No Album".
	*/
	public static void printListAlbum() {

		System.out.println("LISTE DES ALBUMS");
		for(Album a : ListeAlbum) {
			
			if(!a.Titre.equals("No Album")) {

				a.printTitle();
			}
		}
		System.out.println("--------------------");

	}

	/**
	* Sort albums (of ListeAlbum) by date.
	*/
	public static void sortListAlbumByDate() {

		Collections.sort(ListeAlbum, Album.ComparatorDate);

		for(Album a : ListeAlbum) {

			if(a.Titre == "No Album") {
				ListeAlbum.remove(a);
				ListeAlbum.add(0, a);
							}
		}
	}

	/**
	* Print all songs.
	*/
	public static void printAllSong() {
		System.out.println("");
		System.out.println("LISTE DE TOUTES LES CHANSONS");
		for(Album a : ListeAlbum) {
			a.printAllSong();
		}
		System.out.println("--------------------");
	}

	/**
	* Add the specified song to the specified Album.
	* @throws NotExistException Throws NotExistException if the song or the album doesn't exist.
	* @param S String title song to add.
	* @param A String title album to store the S song.
	*/
	public static void addSongToAlbum(String S,String A) throws NotExistException {
		boolean FindAlbum = true;
		boolean FindSong = true;
		int Duree;

		quit:for(Chanson s : ListeAlbum.get(0).MusiqueListe) {
			if(s.Titre.equals(S)) {
				FindSong = false;
				Duree = s.getDuree();

				for(Album a : ListeAlbum) {

					if(a.Titre.equals(A)) {
						FindAlbum = false;
						a.MusiqueListe.add(s);
						ListeAlbum.get(0).MusiqueListe.remove(s);
						System.out.println("La chanson " + S +" a bien été ajoutée à l'album " + A);
						a.setDuree(a.getDuree() + Duree); 
						break quit;
					}
				}
			}
		}

		for(Album a : ListeAlbum) {

			if(a.Titre.equals(A)) {
				FindAlbum = false;
			}	
		}
		String s1 = "";
		String s2 = "";
		if(FindAlbum) {
			s1 = "L'album " + A + " n'existe pas. ";	
		}
		if(FindSong) {
			s2 = "La chanson " + S + " n'existe pas ou la chanson est peut-être déjà dans un album.";
		}
		if(FindAlbum || FindSong) {
			throw new NotExistException(s1 + s2);
		}

		return;
	}

	/**
	* Print songs of the specified Album.
	* @param A String title's album
	*/
	public static void printSongOfAlbum(String A) {
		if(A.equals("No Album")) {
			System.out.println("LISTE DES CHANSONS SANS ALBUM");
		} else {

			System.out.println("LISTE DES CHANSONS DE L'ALBUM " + A);
		}
		for(Album a : ListeAlbum) {
			if(a.Titre == A) {
				
				a.printAllSong();
			}
		}
		System.out.println("--------------------");
	}

	/**
	* Sort song of the specified album by types.
	* @param A String title of the album.
	*/
	public static void sortSongOfAlbumByGenre(String A) {

		for(Album a : ListeAlbum) {
			if(a.Titre == A) {
				
				a.sortSongByGenre();
			}
		}
	}




	/**
	* Add Livre Audio in the first playlist (called "No Playlist") using the LivreAudio's constructor.
	* @param Titre String Title
	* @param Id int ID
	* @param Auteur String author
	* @param Langues Langue Languages
	* @param Categorie Categorie types
	*/
	public static void addLivreAudio(String Titre, int Id, String Auteur, Langue Langue, Categorie Categorie, int Duree) {

		LivreAudio la = new LivreAudio(Titre, Id, Auteur, Langue, Categorie, Duree);
		System.out.println("Le LivreAudio " + Titre + " a bien été ajouté");
		ListePlaylist.get(0).MusiqueListe.add(la);

	}

	/**
	* Create a new playlist with the specified title.
	* @throws AlreadyExistException If a playlist with same name exist, throws an AlreadyExistException.
	* @param Titre String title of the playlist to create.
	*/
	public static void addPlaylist(String Titre, int Id) throws AlreadyExistException {

		for(Playlist play : ListePlaylist) {
			if(play.Titre.equals(Titre)) {
				throw new AlreadyExistException("La playlist " + Titre + " existe déjà.");
			}
		}

		Playlist p = new Playlist(Titre, Id);
		System.out.println("La PlayList " + Titre + " a bien été ajoutée");
		ListePlaylist.add(p);
		System.out.println("La playlist " + Titre + " a bien été ajoutée");
	}


	/**
	* Add the specified song to the specified playlist
	* @throws NotExistException Throws NotExistException if the song or the playlist doesn't exist.
	* @param S String title song to add.
	* @param P String title Playlist to store the S song.
	*/
	public static void addSongToPlaylist(String S, String P) throws NotExistException {
	//On parours tt les album pour trouver la chanson et ainsi l'ajouter a la playlist

		boolean FindPlaylist = true;
		boolean FindSong = true;

		quit:for(Album a : ListeAlbum) {

			for(Chanson s : a.MusiqueListe) {

				if(s.Titre.equals(S)) {

					FindSong = false;

					for(Playlist p : ListePlaylist) {

						if(p.Titre.equals(P)) {

							FindPlaylist = false;
							p.MusiqueListe.add(s);
							System.out.println("La chanson " + S +" a bien été ajoutée à la playlist " + P);
							break quit;
						}
					}
				}
			}
		}

		for(Playlist p : ListePlaylist) {

			if(p.Titre.equals(P)) {
				FindPlaylist = false;
			}	
		}

		String s1 = "";
		String s2 = "";
		if(FindPlaylist) {
			s1 ="La playlist " + P + " n'existe pas.";
		}
		if(FindSong) {
			s2 = "La chanson " + S + " n'existe pas.";
		}
		if(FindPlaylist || FindSong) {
			throw new NotExistException(s1 + " " +s2);
		}


		return;
	}

	/**
	* Add the specified audio book to the specified playlist and remove it of the "No Playlist"
	* @throws NotExistException Throws NotExistException if the song or the playlist doesn't exist.
	* @param L String title audio book to add.
	* @param P String title Playlist to store the L audio book.
	*/
	public static void addLivreAudioToPlaylist(String L, String P) throws NotExistException {
	//On cherche dans toutes les playlist si le Livre audio existe avant de l'ajouter et de le retirer de la No Playlist

		boolean FindPlaylist = true;
		boolean FindLivreAudio = true;


		quit:for(Playlist p : ListePlaylist) {

			for(ElementMusicale l : p.MusiqueListe) {

				if(l instanceof LivreAudio) {

					if(l.Titre.equals(L)) {

						FindLivreAudio = false;

						for(Playlist p2 : ListePlaylist) {

							if(p2.Titre.equals(P)) {

								p2.MusiqueListe.add(l);
								FindPlaylist = false;
								System.out.println("Le LivreAudio" + L +" a bien été ajouté à la playlist " + P);
								break quit;

							}
						}
					}
				}
			}
		}


		
		for(ElementMusicale l : ListePlaylist.get(0).MusiqueListe) {

			if(l instanceof LivreAudio) {

				if(l.Titre.equals(L)) {

					ListePlaylist.get(0).MusiqueListe.remove(l);
				}
			}
		}

		for(Playlist p : ListePlaylist) {

			if(p.Titre.equals(P)) {
				FindPlaylist = false;
			}	
		}

		String s1 = "";
		String s2 = "";
		if(FindPlaylist) {
			s1 = "Le Livre Audio " + P + " n'existe pas";
		}
		if(FindLivreAudio) {

			s2 = "La Livre Audio " + L + " n'existe pas";	
		}
		if(FindPlaylist || FindLivreAudio) {
			throw new NotExistException(s1 + " " + s2);
		}

		return;

	}


	/**
	* Print the list of all Album (which are contains in ListeAlbum) except the "No Album".
	*/
	public static void printListPlaylist() {

		System.out.println("LISTE DES PLAYLIST");
		for(Playlist p : ListePlaylist) {
			
			if(!p.Titre.equals("No Playlist")) {

				p.printTitle();
			}
		}
		System.out.println("--------------------");

	}

	/**
	* Print the songs and audio books of the specified playlist.
	* @param P Stringtitle of the playlist.
	*/
	public static void printElementOfPlaylist(String P) {
		System.out.println("LISTE DES CHANSONS ET LIVRES AUDIO DE LA PLAYLIST " + P);
		for(Playlist p : ListePlaylist) {
			if(p.Titre == P) {
				
				p.printAllSong();
			}
		}
		System.out.println("--------------------");
	}

	/**
	* Remove the specified playlist.
	* @throws NotExistException Throws NotExistException if the specified playlist doesn't exist.
	* @param P String title of the specified playlist.
	*/
	public static void removePlaylist(String P) throws NotExistException {
		
		for(Playlist p : ListePlaylist) {

			if(p.Titre.equals(P)) {
				ListePlaylist.remove(p);
				System.out.println("La playlist " + P + " a bien été supprimée");
				return;
			}
		}

		throw new NotExistException("La playlist " + P + " n'existe pas");
	}

	/**
	* Print all audio books.
	*/
	public static void printAllLivreAudio() {

		List<ElementMusicale> arrayLivreAudio = new CopyOnWriteArrayList<ElementMusicale>();
	

		for(Playlist p : ListePlaylist) {

			for(ElementMusicale L : p.MusiqueListe ) {

				if(L instanceof LivreAudio) {
					arrayLivreAudio.add(L);
				}
			}
		}


		Set<ElementMusicale> monSet = new HashSet<ElementMusicale>(arrayLivreAudio);
		List<ElementMusicale> arrayLivreAudio_2 = new CopyOnWriteArrayList<ElementMusicale>(monSet);

		System.out.println("");
		System.out.println("LISTE DE TOUS LES LIVRES AUDIO");
		for(ElementMusicale e : arrayLivreAudio_2) {
			e.print();
		}
		System.out.println("--------------------");


	}
	

}
