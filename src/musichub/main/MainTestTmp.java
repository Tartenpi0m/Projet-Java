package main;

public class MainTestTmp {

	public static void main(String[] args) {

		GestionStructureMusicale.addAlbum("No Album", 0, "No Artiste", 2020);

		GestionStructureMusicale.addAlbum("Peach", 1, "Larkin Poe", 2017);
		

		GestionStructureMusicale.addSong("Look Away", 1, "Larkin Poe", Genre.ROCK);
		GestionStructureMusicale.addSong("Black Betty", 2, "Larkin Poe", Genre.ROCK);
		GestionStructureMusicale.addSong("Hold the Line", 3, "Toto", Genre.ROCK);


		GestionStructureMusicale.printListAlbum();
		System.out.println("------------------------");
		GestionStructureMusicale.sortListeAlbumByDate();
		GestionStructureMusicale.printListAlbum();
		



		//GestionStructureMusicale.printAllSong();

		
	}


}