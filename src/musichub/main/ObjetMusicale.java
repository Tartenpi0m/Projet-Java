package src.musichub.main;

/**
* Abstract class which groups together the common attributes of <strong>ElementMusciale</strong> et <strong>StructureMusicale</strong>
*/
abstract public class ObjetMusicale {

	protected String Titre;
	protected int Id;
	protected int Duree;

	/**
	* The accessor of Titre.
	* @return String Titre
	*/
	public String getTitre() {
		return this.Titre;
	}

	/**
	* The accessor of Id.
	* @return int Id
	*/
	public int getId() {
		return this.Id;
	}

	/**
	* The accessor of Duree.
	* @return int Duree
	*/
	public int getDuree() {
		return this.Duree;
	}	


	public void setDuree(int Duree) {
		this.Duree = Duree;
	}

}
