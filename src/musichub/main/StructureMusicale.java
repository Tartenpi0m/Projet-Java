package main;


import java.util.List;

/**
*Abstract class which groups together the common attributes of <strong>Album</strong> et <strong>Playlist</strong>
*@author Antoine R.
*
*/

abstract public class StructureMusicale extends ObjetMusicale{
	
	public List<ElementMusicale> MusiqueListe; 

	/**
	* Print majors structure's information.
	*/
	public  abstract void printTitle();

	/** 
	* Print the content's structure.
	*/
	public  abstract void printAllSong();
}