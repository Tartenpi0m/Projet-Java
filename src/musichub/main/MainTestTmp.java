package main;

public class MainTestTmp {

	public static void main(String[] args) {

		GestionStructureMusicale.addAlbum("No Album", 0, "No Artiste", 2020);

		GestionStructureMusicale.addAlbum("Peach", 1, "Larkin Poe", 2017);
		

		GestionStructureMusicale.addSong("Look Away", 1, "Larkin Poe", Genre.ROCK);
		GestionStructureMusicale.addSong("Black Betty", 2, "Larkin Poe", Genre.JAZZ);
		GestionStructureMusicale.addSong("Hold the Line", 3, "Toto", Genre.ROCK);


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




		
	}


}