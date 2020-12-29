package main;


import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.io.InputStream;


/**
* This class provides all the existing features of GestionStructureMusicale to the user.
* The user can use GestionStructureMusicale witheout using code by the way of this class.
*/
public abstract class Entree {

	private static Scanner sc = new Scanner(System.in);

	private static Commande[] tableauCommande = Commande.values();

	private static int Id = 0;

	/**
	* Gives ID to Musicale Object
	* return an ID (int value and increase the static int Id variable of 1
	* @return int value
	*/
	public static int giveId() {

		Id ++;
		return Id;

	}

	/**
	* Ask to the user a string
	* @return A String
	*/
	private static String ask() {

		String s;
		s = sc.nextLine();
		return s;
 	}

 	/**
	* Ask to the user a string which is not empty.
	* @return A not empty String.
	*/
 	private static String askRequired(String info) {

 		String s;

 		do {
 			System.out.print(info + " : ");
 			s = ask();
 		} while(s.equals(""));

 		return s;
 	}

 	/**
	* Ask to the user a string. If the user sent an empty string, return "Inconnu".
	* @return A String.
	*/
 	private static String askNonRequired(String info) {

 		String s;
 		System.out.print(info + " : ");
 		s = ask();
 		if(s.equals("")) {
 			s = "Inconnu";
 		}

 		return s;
 	}

 	/**
	* Ask to the user a int. If the user sent an empty answer, return 0.
	* @return A int.
	*/
 	private static int askIntNonRequired(String info) {

 		int i = 0;
 		while(true) {

 		System.out.print(info + " : ");
 		try {
 			i = sc.nextInt(); 
 		} catch (InputMismatchException ex) {
 			System.out.println("Valeur numérique demandée");
 			sc.next(); //permet de se débarraser de la chaîne de charactère entrée par l'utilisateur
 			continue;
 		} catch(NoSuchElementException ec) {
 			return 0;
 		}
 		break;

 		}
 		return i;
 	}

 	/**
 	* Ask to the user a String.
 	* @return Return the (String) input of the user if this String is in the Commande enum, else return "error". 
 	*/
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

	/**
	* Ask to the user a String which exist in the Genre enumération.
	* @return A string of Genre, or "Inconnu" if the input is empty.
	*/
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

	/**
	* Ask to the user a String which exist in the Categorie enumération.
	* @return A string of Categorie, or "Inconnu" if the input is empty.
	*/
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

	/**
	* Ask to the user a String which exist in the Langue enumération.
	* @return A string of Langue, or "Inconnu" if the input is empty.
	*/
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

	/**
	* Execute the specified commande.
	* @param s String, specified command.
	*/
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


	/**
	* Ask to the user parameters needed to execute the method with the same name in class GestionStructureObjet and execute it.
	*/ 
	public static void addSong() {

		System.out.println("--AJOUT D'UNE NOUVELLE CHANSON--");
		String Titre = askRequired("Titre");
		if(Titre.equals(tableauCommande[8].getCommande())) {return;}
		String Artiste = askNonRequired("Artiste");
		Genre Genre = askGenre();
		GestionStructureMusicale.addSong(Titre, giveId(), Artiste, Genre);

	}

	/**
	* Ask to the user parameters needed to execute the method with the same name in class GestionStructureObjet and execute it.
	*/ 
	public static void addAlbum() {
	
		System.out.println("--AJOUT D'UN NOUVEL ALBUM--");
		String Titre = askRequired("Titre");
		if(Titre.equals(tableauCommande[8].getCommande())) {return;}
		for(Album play : GestionStructureMusicale.ListeAlbum) {
			if(play.Titre.equals(Titre)) {
				System.out.println("Un album du même nom existe déjà");
				return;
			}
		}
		String Artiste = askNonRequired("Artiste");
		int Date = askIntNonRequired("Date (année)");
		GestionStructureMusicale.addAlbum(Titre, giveId(), Artiste, Date);
		GestionStructureMusicale.sortListAlbumByDate();
	}

	/**
	* Ask to the user parameters needed to execute the method with the same name in class GestionStructureObjet and execute it.
	*/ 
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

	/**
	* Ask to the user parameters needed to execute the method with the same name in class GestionStructureObjet and execute it.
	*/ 
	public static void addLivreAudio() {

		System.out.println("--AJOUT D'UN NOUVEAU LIVRE AUDIO--");
		String Titre = askRequired("Titre");
		if(Titre.equals(tableauCommande[8].getCommande())) {return;}
		String Auteur = askNonRequired("Auteur");
		Langue Langue = askLangue();
		Categorie Categorie = askCategorie();
		GestionStructureMusicale.addLivreAudio(Titre, giveId(), Auteur, Langue, Categorie);
	}

	/**
	* Ask to the user parameters needed to execute the method with the same name in class GestionStructureObjet and execute it.
	*/ 
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

	/**
	* Ask to the user parameters needed to execute the method with the same name in class GestionStructureObjet and execute it.
	*/ 
	public static void removePlaylist() {
		System.out.println("--SUPPRESSION D'UNE PLAYLIST--");
		String NomPlaylist = askRequired("Playlist");
		if(NomPlaylist.equals(tableauCommande[8].getCommande())) {return;}

		while(GestionStructureMusicale.removePlaylist(NomPlaylist) == false) {
			NomPlaylist = askRequired("Playlist");
		}
	} 



}