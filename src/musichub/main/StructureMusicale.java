package main;


import java.util.List;

/**
*Classe abstraite Structuremusicale qui regroupe les attributs communs entre la classe <strong>Album</strong> et <strong>Playlist</strong>
*@author Antoine R.
*
*/

abstract public class StructureMusicale extends ObjetMusicale{
	
	private List<ElementMusicale> MusiqueListe; //= new List<ElementMusicale>();
	//Probleme List est une classe abstraite, impossible donc d'en créer une instance
	// Alors comment général avec le type List ??
}