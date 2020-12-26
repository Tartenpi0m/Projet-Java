package main;


import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.InputMismatchException;
import java.io.InputStream;



public abstract class Entree {

	private static Scanner sc = new Scanner(System.in);

	private static Commande[] tableauCommande = Commande.values();

	private static int Id = 0;


	public static int giveId() {

		Id ++;
		return Id;

	}

	private static String ask() {

		String s;
		s = sc.nextLine();
		return s;
 	}

 	private static String askRequired(String info) {

 		String s;

 		do {
 			System.out.print(info + " : ");
 			s = ask();
 		} while(s.equals(""));

 		return s;
 	}

 	private static String askNonRequired(String info) {

 		String s;
 		System.out.print(info + " : ");
 		s = ask();
 		if(s.equals("")) {
 			s = "Inconnu";
 		}

 		return s;
 	}


 	private static int askIntNonRequired(String info) {

 		int i;
 		while(true) {

 		System.out.print(info + " : ");
 		try {
 			i = sc.nextInt();
 		} catch (InputMismatchException ex) {
 			System.out.println("Valeur numérique demandée");
 			sc.next(); //permet de se débarraser de la chaîne de charactère entrée par l'utilisateur
 			continue;
 		}
 		break;

 		}
 		return i;
 	}

	public static String askCommande() {
		System.out.print(">>>>>> : ");
		String s = ask();

		for(Commande c : tableauCommande) {

			if(c.getCommande().equals(s)) {
				return s;
			}
		}


		System.out.println("La commande " + "\""+ s + "\"" + " n'existe pas" );
		return "error";



	} 

	public static Genre askGenre() {

		Genre[] tableauMonEnum = Genre.values();
		String s; 

		for(Genre e : tableauMonEnum) {
			System.out.print(e.getGenre() + " / ");
		}

		System.out.println("");

		while(true) {

			System.out.print("Genre : ");
			s = ask();
			for(Genre e : tableauMonEnum) {
				if(e.getGenre().equals(s)) {

					return e;
				}
			}

			if(s.equals("")) {

				return Genre.INCONNU;
			}
			System.out.println("Genre incorrect, tapez entrée pour ne pas spécifier le genre");
		}

	}
	public static Categorie askCategorie() {
			
		Categorie[] tableauMonEnum = Categorie.values();
		String s;

		for(Categorie e : tableauMonEnum) {
			System.out.print(e.getCategorie() + " / ");
		}

		System.out.println("");

		while(true) {

			System.out.print("Catégorie : ");
			s = ask();
			for(Categorie e : tableauMonEnum) {
				if(e.getCategorie().equals(s)) {

					return e;
				}
			}

			if(s.equals("")) {

				return Categorie.INCONNU;
			}
			System.out.println("Catégorie incorrect, tapez entrée pour ne pas spécifier la catégorie");
		}
	}

	public static Langue askLangue() {

		Langue[] tableauMonEnum = Langue.values();
		String s;
		
		for(Langue e : tableauMonEnum) {
			System.out.print(e.getLangue() + " / ");
		}

		System.out.println("");

		while(true) {

			System.out.print("Langue : ");
			s = ask();
			for(Langue e : tableauMonEnum) {
				if(e.getLangue().equals(s)) {

					return e;
				}
			}

			if(s.equals("")) {

				return Langue.INCONNU;
			}
			System.out.println("Langue incorrect, tapez entrée pour ne pas spécifier la langue");
		}
	}


	public static void makeCommande(String s) {
		
		if(s.equals(tableauCommande[0].getCommande())) {
			addSong();
 
		} else if(s.equals(tableauCommande[1].getCommande())) {

			addAlbum();
 
		} else if(s.equals(tableauCommande[2].getCommande())) {
			
 			addSongToAlbum();
		} else if(s.equals(tableauCommande[3].getCommande())) {
			
 			addLivreAudio();
		} else if(s.equals(tableauCommande[4].getCommande())) {
			
 			addPlaylist();
		} else if(s.equals(tableauCommande[5].getCommande())) {
			
 			removePlaylist();
		} 

	

	}



	public static void addSong() {

		System.out.println("--AJOUT D'UNE NOUVELLE CHANSON--");
		String Titre = askRequired("Titre");
		if(Titre.equals(tableauCommande[8].getCommande())) {return;}
		String Artiste = askNonRequired("Artiste");
		Genre Genre = askGenre();
		GestionStructureMusicale.addSong(Titre, giveId(), Artiste, Genre);

	}


	public static void addAlbum() {
	
		System.out.println("--AJOUT D'UN NOUVEL ALBUM--");
		String Titre = askRequired("Titre");
		if(Titre.equals(tableauCommande[8].getCommande())) {return;}
		String Artiste = askNonRequired("Artiste");
		int Date = askIntNonRequired("Date (année)");
		GestionStructureMusicale.addAlbum(Titre, giveId(), Artiste, Date);
		GestionStructureMusicale.sortListAlbumByDate();
	}


	public static void addSongToAlbum() {

		System.out.println("--AJOUT D'UNE CHANSON A UN ALBUM--");
		String TitreChanson;
		String TitreAlbum;

		do {
			GestionStructureMusicale.printSongOfAlbum("No Album");
			TitreChanson = askRequired("Chanson");
			if(TitreChanson.equals(tableauCommande[8].getCommande())) {return;}
			GestionStructureMusicale.printListAlbum();
			TitreAlbum = askRequired("Album");
			if(TitreAlbum.equals(tableauCommande[8].getCommande())) {return;}
			System.out.println(TitreChanson + " : " + TitreAlbum);
		
		} while(GestionStructureMusicale.addSongToAlbum(TitreChanson, TitreAlbum) == false);

	}


	public static void addLivreAudio() {

		System.out.println("--AJOUT D'UN NOUVEAU LIVRE AUDIO--");
		String Titre = askRequired("Titre");
		if(Titre.equals(tableauCommande[8].getCommande())) {return;}
		String Auteur = askNonRequired("Auteur");
		Langue Langue = askLangue();
		Categorie Categorie = askCategorie();
		GestionStructureMusicale.addLivreAudio(Titre, giveId(), Auteur, Langue, Categorie);
	}


	public static void addPlaylist() {

		System.out.println("--AJOUT D'UNE NOUVELLE PLALYIST");
		String NomPlaylist = askRequired("Nom"); 
		if(NomPlaylist.equals(tableauCommande[8].getCommande())) {return;}
		GestionStructureMusicale.addPlaylist(NomPlaylist);


		String Titre;
		String choix = askRequired("Ajouter des Chansons à la playlist (y/n)");
		
		if(choix.equals("y")) {
			GestionStructureMusicale.printAllSong();
		}

		while(choix.equals("y")) {


			Titre = askRequired("Chansons");
			if(Titre.equals(tableauCommande[8].getCommande())) {return;}
			
			if(GestionStructureMusicale.addSongToPlaylist(Titre, NomPlaylist) == false) {
				continue;
			}


			choix = askRequired("Ajouter des Chansons à la playlist (y/n)");
			
		} 


		choix = askRequired("Ajouter des Livres Audio à la playlist (y/n)");
		
		if(choix.equals("y")) {
			GestionStructureMusicale.printAllLivreAudio();
		}

		while(choix.equals("y")) {


			Titre = askRequired("Livre Audio");
			if(Titre.equals(tableauCommande[8].getCommande())) {return;}
			
			if(GestionStructureMusicale.addLivreAudioToPlaylist(Titre, NomPlaylist) == false) {
				continue;
			}


			choix = askRequired("Ajouter des Livres Audio à la playlist (y/n)");
			
		} 

	}


	public static void removePlaylist() {
		System.out.println("--SUPPRESSION D'UNE PLAYLIST--");
		String NomPlaylist = askRequired("Playlist");
		if(NomPlaylist.equals(tableauCommande[8].getCommande())) {return;}

		while(GestionStructureMusicale.removePlaylist(NomPlaylist) == false) {
			NomPlaylist = askRequired("Playlist");
		}
	} 



}