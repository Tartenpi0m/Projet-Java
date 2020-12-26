package main;


import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.InputMismatchException;
import java.io.InputStream;



public class Entree {

	private static Scanner sc = new Scanner(System.in);

	private static Commande[] tableauCommande = Commande.values();

	private static int Id = 0;

	//sc.nextLine();
	public static int giveId() {

		Id ++;
		return Id;

	}

	public static String ask() {

		String s;
		s = sc.nextLine();
		return s;
 	}

 	public static String askRequired(String info) {

 		String s;

 		do {
 			System.out.print(info + " : ");
 			s = ask();
 		} while(s.equals(""));

 		return s;
 	}

 	public static String askNonRequired(String info) {

 		String s;
 		System.out.print(info + " : ");
 		s = ask();
 		if(s.equals("")) {
 			s = "Inconnu";
 		}

 		return s;
 	}


 	public static int askIntNonRequired(String info) {

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
		
		if(s.equals(tableauCommande[1].getCommande())) {
			addSong();
 
		} else if(s.equals(tableauCommande[2].getCommande())) {
			
 
		} else if(s.equals(tableauCommande[3].getCommande())) {
			
 
		} else if(s.equals(tableauCommande[4].getCommande())) {
			
 
		} else if(s.equals(tableauCommande[5].getCommande())) {
			
 
		} else if(s.equals(tableauCommande[6].getCommande())) {
			
 
		} 

	

	}



	public static void addSong() {

		System.out.println("--AJOUT D'UNE NOUVELLE CHANSON--");
		String Titre = askRequired("Titre");
		String Artiste = askNonRequired("Artiste");
		Genre Genre = askGenre();
		GestionStructureMusicale.addSong(Titre, giveId(), Artiste, Genre);

	}




}