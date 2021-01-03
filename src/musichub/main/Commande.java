package src.musichub.main;

/**
* This enumeration list all available terminal commands which are used by the user to manipulate "ObjetMusicale" and and her daughter classes. 
*/
public enum Commande {

	C("c"), A("a"), PLUS("+"), L("l"), P("p"), MOINS("-"), S("s"), H("h"), EXIT("!exit"), AFFICHEALBUM("afficheAlbum");

	private String commande;


	/*
	* The enumeration's constructor.
	*/
	private Commande(String commande) {
		this.commande = commande;
	}

	/**
	* The enumeration's accessor.
	*/
	public String getCommande() {

		return commande;
	}
}
