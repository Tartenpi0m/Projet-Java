package src.musichub.main;

import src.musichub.main.*;
import src.musichub.util.*;


public class MainTestTmp {

	public static void main(String[] args) {

		try {
		GestionStructureMusicale.addAlbum("No Album", Entree.giveId(), "No Artiste", 2020, 0);
		GestionStructureMusicale.addPlaylist("NoPlaylist", Entree.giveId());						  
		} catch(AlreadyExistException e) {
		}
		
					  
		XMLelement XML = new XMLelement();
		XML.readXML();


		String s;

		while(true) {
			
			do {
				s = Entree.askCommande();
				if(s.equals(Commande.EXIT.getCommande())) {return;}

			}while(s.equals("error"));

			System.out.println("AAA");
			Entree.makeCommande(s);
			//Entree.addSong();



		}
		






















		/*

		GestionStructureMusicale.printListPlaylist();

		GestionStructureMusicale.printElementOfPlaylist("Beurre");

		GestionStructureMusicale.addSongToPlaylist("Black Betty", "Beurre");
		GestionStructureMusicale.addLivreAudioToPlaylist("Harry Potter II", "Beurre");

		GestionStructureMusicale.printElementOfPlaylist("Beurre");

		GestionStructureMusicale.removePlaylist("Beure");
		GestionStructureMusicale.printListPlaylist();



		*/



		/*
		//System.out.println(GestionStructureMusicale.ListeAlbum.get(1).Titre);

		
		//GestionStructureMusicale.sortListeAlbumByDate();
		//GestionStructureMusicale.printListAlbum();
		System.out.println("Chanson du premier album d'indice 0");
		GestionStructureMusicale.printSongOfAlbum("No Album");
		System.out.println(" ");
		System.out.println("Chanson du deuxieme album d'indice 1");
		GestionStructureMusicale.printSongOfAlbum("Peach");


		System.out.println("------------------------");



		GestionStructureMusicale.addSongToAlbum("Look Away", "Peach");
		GestionStructureMusicale.addSongToAlbum("Black Betty", "Peach");
		GestionStructureMusicale.addSongToAlbum("Hold the Line", "Peach");
		//GestionStructureMusicale.printSongOfAlbum("Peach");

		//GestionStructureMusicale.sortSongOfAlbumByGenre("Peach");

		GestionStructureMusicale.sortSongOfAlbumByGenre("Peach");


		System.out.println("------------------------");
		//GestionStructureMusicale.sortListeAlbumByDate();
		//GestionStructureMusicale.printListAlbum();
		System.out.println("Chanson du premier album d'indice 0");
		GestionStructureMusicale.printSongOfAlbum("No Album");
		System.out.println(" ");
		System.out.println("Chanson du deuxieme album d'indice 1");
		GestionStructureMusicale.printSongOfAlbum("Peach");

		*/


		
	}


}
