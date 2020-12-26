package main;



public enum Commande {

	C("c"), A("a"), PLUS("+"), L("l"), P("p"), MOINS("-"), S("s"), H("h"), EXIT("!exit");

	private String commande;

	private Commande(String commande) {
		this.commande = commande;
	}

	public String getCommande() {

		return commande;
	}
}