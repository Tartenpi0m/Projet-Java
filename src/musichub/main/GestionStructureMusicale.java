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

	private static List<Playlist> ListePlaylist = Collections.synchronizedList(new CopyOnWriteArrayList<Playlist>());


	public static void addSong(String Titre, int Id, String Artiste, Genre Genre) {
		System.out.println("La chanson " + Titre + " a bien été ajoutée");
		Chanson c = new Chanson(Titre, Id, Artiste, Genre);
		ListeAlbum.get(0).MusiqueListe.add(c);
		
	}



	public static void addAlbum(String Titre, int Id, String Artiste, int Date) {
		System.out.println("L'album " + Titre + " a bien été ajouté.");
		Album a = new Album(Titre, Id, Artiste, Date);
		ListeAlbum.add(a);

	}

	public static void printListAlbum() {

		System.out.println("LISTE DES ALBUMS");
		for(Album a : ListeAlbum) {
			a.printTitle();
		}
		System.out.println("--------------------");

	}

	public static void sortListeAlbumByDate() {

		Collections.sort(ListeAlbum, Album.ComparatorDate);

		for(Album a : ListeAlbum) {

			if(a.Titre == "No Album") {
				ListeAlbum.remove(a);
				ListeAlbum.add(0, a);
							}
		}
	}

	public static void printAllSong() {
		System.out.println("LISTE DE TOUTES LES CHANSONS");
		for(Album a : ListeAlbum) {
			a.printAllSong();
		}
		System.out.println("--------------------");
	}

	public static void addSongToAlbum(String S,String A) {
		boolean FindAlbum = true;
		boolean FindSong = true;

		quit:for(Chanson s : ListeAlbum.get(0).MusiqueListe) {
			if(s.Titre == S) {
				FindSong = false;

				for(Album a : ListeAlbum) {

					if(a.Titre == A) {
						FindAlbum = false;
						a.MusiqueListe.add(s);
						ListeAlbum.get(0).MusiqueListe.remove(s);
						System.out.println("La chanson " + S +" a bien été ajoutée à l'album " + A);
						break quit;
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
		System.out.println("LISTE DES CHANSONS DE L'ALBUM " + A);
		for(Album a : ListeAlbum) {
			if(a.Titre == A) {
				
				a.printAllSong();
			}
		}
		System.out.println("--------------------");
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

	//On parours tt les album pour trouver la chanson et ainsi l'ajouter a la playlist
	public static void addSongToPlaylist(String S, String P) {

		boolean FindPlaylist = true;
		boolean FindSong = true;

		quit:for(Album a : ListeAlbum) {

			for(Chanson s : a.MusiqueListe) {

				if(s.Titre == S) {

					FindSong = false;

					for(Playlist p : ListePlaylist) {

						if(p.Titre == P) {

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

			if(p.Titre == P) {
				FindPlaylist = false;
			}	
		}

		if(FindPlaylist) {
			System.out.println("La playlist " + P + " n'existe pas");
		}
		if(FindSong) {
			System.out.println("La chanson " + S + " n'existe pas");
		}
	}


	//On cherche dans toutes les playlist si le Livre audio existe avant de l'ajouter et de le retirer de la No Playlist
	public static void addLivreAudioToPlaylist(String L, String P) {

		boolean FindPlaylist = true;
		boolean FindLivreAudio = true;


		quit:for(Playlist p : ListePlaylist) {

			for(ElementMusicale l : p.MusiqueListe) {

				if(l instanceof LivreAudio) {

					if(l.Titre == L) {

						for(Playlist p2 : ListePlaylist) {

							if(p2.Titre == P) {

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

				if(l.Titre == L) {

					ListePlaylist.get(0).MusiqueListe.remove(l);
				}
			}
		}

		if(FindPlaylist) {
			System.out.println("La playlist " + P + " n'existe pas");
		}



	}



	public static void printListPlaylist() {

		System.out.println("LISTE DES PLAYLIST");
		for(Playlist p : ListePlaylist) {
			p.printTitle();
		}
		System.out.println("--------------------");

	}


	public static void printElementOfPlaylist(String P) {
		System.out.println("LISTE DES CHANSONS ET LIVRES AUDIO DE LA PLAYLIST " + P);
		for(Playlist p : ListePlaylist) {
			if(p.Titre == P) {
				
				p.printAllSong();
			}
		}
		System.out.println("--------------------");
	}


	public static void removePlaylist(String P) {
		
		for(Playlist p : ListePlaylist) {

			if(p.Titre == P) {
				ListePlaylist.remove(p);
				System.out.println("La playlist " + P + " a bien été supprimée");
				return;
			}
		}

		System.out.println("La playlist " + P + " n'existe pas");
	}


	

}