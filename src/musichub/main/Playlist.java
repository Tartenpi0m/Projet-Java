package src.musichub.main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;

/**
*Classe Playlist 
*@author Antoine R.
*/
public class Playlist extends StructureMusicale {


	List<ElementMusicale> MusiqueListe = Collections.synchronizedList(new CopyOnWriteArrayList<ElementMusicale>());
	

	public Playlist(String Titre) {

		this.Titre = Titre;

	}


	public void printTitle() {
		System.out.println(this.Titre);
	}

	public void printAllSong() {

		for(ElementMusicale e : MusiqueListe) {
			e.print();
		}

	}

}
