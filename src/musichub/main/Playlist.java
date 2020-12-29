package main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;

/**
* /**
* This class represent an Playlist from the user's perspective.
* This class can contains some "Chanson" and "LivreAudio" in a CopeOnWriteArrayList.
* 
*/
public class Playlist extends StructureMusicale {


	List<ElementMusicale> MusiqueListe = Collections.synchronizedList(new CopyOnWriteArrayList<ElementMusicale>());
	

	/**
	* Playlist Constructor, named the playlist with the specified String.
	*/
	public Playlist(String Titre) {

		this.Titre = Titre;

	}


	/**
	* Print the playlist's name.
	*/
	public void printTitle() {
		System.out.println(this.Titre);
	}


	/**
	* Print all the playlist's song.
	*/
	public void printAllSong() {

		for(ElementMusicale e : MusiqueListe) {
			e.print();
		}

	}

}