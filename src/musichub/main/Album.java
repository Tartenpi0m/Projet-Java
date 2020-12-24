package main;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

/**
*Classe Album
*@author Antoine R.
*/


public class Album extends StructureMusicale {

	private String Artiste;
	//private Inconnue Date; ??????????????????
	private int Date;
	List<Chanson> MusiqueListe = Collections.synchronizedList(new CopyOnWriteArrayList<Chanson>());



	public Album(String Titre, int Id, String Artiste, int Date) {

		this.Titre = Titre;
		this.Id = Id;
		this.Artiste = Artiste;
		this.Date = Date;

	}

	public void printTitle() {
		System.out.println(this.Titre + " de " + Artiste);
	}

	public void printAllSong() {

		for(Chanson c : MusiqueListe) {
			c.print();
		}

	}

	public static Comparator<Album> ComparatorDate = new Comparator<Album>() {
     
        @Override
        public int compare(Album a1, Album a2) {
            return a1.Date - a2.Date; //doit etre de type int 
        }
    };

    public void sortSongByGenre() {

    	Collections.sort(MusiqueListe, Chanson.ComparatorGenre);
    }



}