package src.musichub.util;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;

import java.util.Scanner;


import src.musichub.main.*;


/** XMLDemo classe qui demontre comment lire et ecrire un fichier XML
 * La classe lit un XML qui contient plusieurs elements <pre>{@code<client>}</pre> (mais n'en ecrit qu'un seul):
 *  <pre>
 * {@code
 *   <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 *      <clients>
 *		   <client>
 *		      <UUID>4f392743-c9ba-4230-9b93-a1c79c0a13c4</UUID>
 *			  <firstName>Brad</firstName>
 *			  <lastName>Pitt</lastName>
 *			  <address>150 Broadway St., New York</address>
 *			</client>
 *		</clients>
 * }
 * </pre>
 *	@author Felicia Ionascu
 */		
public class XMLelement {
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DocumentBuilderFactory documentFactory;
	private DocumentBuilder documentBuilder;


	public XMLelement() {
		try {
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			documentFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentFactory.newDocumentBuilder();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}

	//Scanner scan = new Scanner(System.in);
	/**
	 * Le nom du fichier XML qu'on lit
	 */
	//String fichier = scan.nextLine();

	//private static String XML_INPUT_FILE = "elements.xml";


	private  String XML_INPUT_FILE;
	/**
	 * Le nom du fichier XML dans lequel on écrit
	 */
	private static String XML_OUTPUT_FILE = "sample_out.xml";

	/**
	 * La methode qui transforme le document en memoire en fichier XML sur le disque dur
	 * @param document le document à transformer
	 * @param filePath le chemin (répértoire et nom) du fichier XML à créer 
	 */	
	public void createXMLFile(Document document, String filePath)
	{
		try {
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(filePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging 

			//transform the DOM Object to an XML File
			transformer.transform(domSource, streamResult);

		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		System.out.println("Done creating XML File");
	}

	/**
	 * La methode qui crée le document en memoire
	 * @return le document créé
	 */	
	public Document createXMLDocument()
	{
		return documentBuilder.newDocument();
	}		

	/**
	 * La methode qui lit un fichier XML et le transforme en liste de noeuds en mémoire
	 * @param filePath le chemin (répértoire et nom) du fichier XML à lire 
	 * @return la liste des noeuds lus
	 */	
	public NodeList parseXMLFile (String filePath) {
		NodeList elementNodes = null;
		try {
			Document document= documentBuilder.parse(new File(filePath));
			Element root = document.getDocumentElement();

			elementNodes = root.getChildNodes();	
		}
		catch (SAXException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return elementNodes;
	}

	/**
	 * Methode pour démontrer la lecture d'un fichier XML qui contient plusieurs éléments
	 */
	public void readXML() {
		GestionStructureMusicale Musique = new GestionStructureMusicale();
		String Titre ="";
		String Artiste ="";
		int Duree = 0;
		int ID = 0;
		int Date =0;
		String Genre2 = "";
		String Auteur = "";
		String Langue2 = "";
		String Categorie2 = "";
		XML_INPUT_FILE = "doc/elements.xml";
		NodeList nodes = this.parseXMLFile(XML_INPUT_FILE);
		if (nodes == null) return;

		for (int i = 0; i<nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   {
				Element currentElement = (Element) nodes.item(i);
				if (currentElement.getNodeName().equals("Chanson")) 	{
					try {

						Titre = currentElement.getElementsByTagName("Titre").item(0).getTextContent();
						Artiste = currentElement.getElementsByTagName("Artiste").item(0).getTextContent();
						Duree = Integer.parseInt(currentElement.getElementsByTagName("Duree").item(0).getTextContent());
						ID = Integer.parseInt(currentElement.getElementsByTagName("ID").item(0).getTextContent());
						Genre2 = currentElement.getElementsByTagName("Genre").item(0).getTextContent();



						//verify that I read everything correctly:
					} catch (Exception ex) {
						System.out.println("Something is wrong with the XML albums element");
					}
					if (Genre2.equals("Jazz") ){
						Musique.addSong(Titre, ID, Artiste, Genre.JAZZ);
					}
					else if(Genre2.equals("Pop") ) {
						Musique.addSong(Titre, ID, Artiste, Genre.POP);
					}
					else if (Genre2.equals("Rock") ){
						Musique.addSong(Titre, ID, Artiste, Genre.ROCK);
					}
					else if (Genre2.equals("Hip-Hop")) {
						Musique.addSong(Titre, ID, Artiste, Genre.HIPHOP);
					}
					else if (Genre2.equals("Classique")) {
						Musique.addSong(Titre, ID, Artiste, Genre.CLASSIQUE);
					}
					else if (Genre2.equals("Rap") ){
						Musique.addSong(Titre, ID, Artiste, Genre.RAP);
					}
				}
				if (currentElement.getNodeName().equals("LivreAudio")) 	{
					try {
						Titre = currentElement.getElementsByTagName("Titre").item(0).getTextContent();
						Auteur = currentElement.getElementsByTagName("Auteur").item(0).getTextContent();
						Duree = Integer.parseInt(currentElement.getElementsByTagName("Duree").item(0).getTextContent());
						ID = Integer.parseInt(currentElement.getElementsByTagName("ID").item(0).getTextContent());
						Langue2 = currentElement.getElementsByTagName("Langue").item(0).getTextContent();
						Categorie2 = currentElement.getElementsByTagName("Categorie").item(0).getTextContent();


					} catch (Exception ex) {
						System.out.println("Something is wrong with the XML albums element");
					}
					if (Categorie2.equals("Jeunesse")){
						if (Langue2.equals("Français")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.JEUNESSE);
						}
						else if (Langue2.equals("Anglais")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.JEUNESSE);
						}
						else if (Langue2.equals("Italien")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.JEUNESSE);
						}
						else if (Langue2.equals("Allemand")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.JEUNESSE);
						}
						else if (Langue2.equals("Inconnu")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.JEUNESSE);
						}
						else if (Langue2.equals("Espagnol")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.JEUNESSE);
						}
					}
					if (Categorie2.equals("Roman")){
						if (Langue2.equals("Français")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.ROMAN);
						}
						else if (Langue2.equals("Anglais")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.ROMAN);
						}
						else if (Langue2.equals("Italien")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.ROMAN);
						}
						else if (Langue2.equals("Allemand")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.ROMAN);
						}
						else if (Langue2.equals("Inconnu")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.ROMAN);
						}
						else if (Langue2.equals("Espagnol")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.ROMAN);
						}
					}
					if (Categorie2.equals("Théatre")){
						if (Langue2.equals("Français")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.THEATRE);
						}
						else if (Langue2.equals("Anglais")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.THEATRE);
						}
						else if (Langue2.equals("Italien")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.THEATRE);
						}
						else if (Langue2.equals("Allemand")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.THEATRE);
						}
						else if (Langue2.equals("Inconnu")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.THEATRE);
						}
						else if (Langue2.equals("Espagnol")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.THEATRE);
						}
					}
					if (Categorie2.equals("Discours")){
						if (Langue2.equals("Français")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.DISCOURS);
						}
						else if (Langue2.equals("Anglais")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.DISCOURS);
						}
						else if (Langue2.equals("Italien")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.DISCOURS);
						}
						else if (Langue2.equals("Allemand")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.DISCOURS);
						}
						else if (Langue2.equals("Inconnu")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.DISCOURS);
						}
						else if (Langue2.equals("Espagnol")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.DISCOURS);
						}
					}
					if (Categorie2.equals("Documentaire")){
						if (Langue2.equals("Français")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.DOCUMENTAIRE);
						}
						else if (Langue2.equals("Anglais")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.DOCUMENTAIRE);
						}
						else if (Langue2.equals("Italien")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.DOCUMENTAIRE);
						}
						else if (Langue2.equals("Allemand")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.DOCUMENTAIRE);
						}
						else if (Langue2.equals("Inconnu")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.DOCUMENTAIRE);
						}
						else if (Langue2.equals("Espagnol")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.DOCUMENTAIRE);
						}
					}
					if (Categorie2.equals("Inconnu")){
						if (Langue2.equals("Français")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.INCONNU);
						}
						else if (Langue2.equals("Anglais")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.INCONNU);
						}
						else if (Langue2.equals("Italien")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.INCONNU);
						}
						else if (Langue2.equals("Allemand")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.INCONNU);
						}
						else if (Langue2.equals("Inconnu")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.INCONNU);
						}
						else if (Langue2.equals("Espagnol")) {
							Musique.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.INCONNU);
						}
					}
				}
			}
		}
		String Nom = "";
		String TitreChanson1 = "";	
		String TitreChanson2 = "";	
		String TitreChanson3 = "";	
		String TitreChanson4 = "";	
		String TitreChanson5 = "";	
		String TitreChanson6 = "";	
		String TitreChanson7 = "";	
		String TitreChanson8 = "";	
		String TitreChanson9 = "";	
		String TitreChanson10 = "";
		String TitreChanson11 = "";
		String TitreChanson12 = "";
		String TitreChanson13 = "";
		String TitreChanson14 = "";
		String TitreChanson15 = "";
		String TitreLivreAudio1 = "";	
		String TitreLivreAudio2 = "";	
		String TitreLivreAudio3 = "";	
		String TitreLivreAudio4 = "";	
		String TitreLivreAudio5 = "";	
		nodes = this.parseXMLFile("doc/albums.xml");
		if (nodes == null) return;

		for (int i = 0; i<nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   {
				Element currentElement = (Element) nodes.item(i);
				if (currentElement.getNodeName().equals("Album")) 	{
					try {
						ID = Integer.parseInt(currentElement.getElementsByTagName("ID").item(0).getTextContent());
						Titre = currentElement.getElementsByTagName("Titre").item(0).getTextContent();
						Artiste = currentElement.getElementsByTagName("Artiste").item(0).getTextContent();
						Duree = Integer.parseInt(currentElement.getElementsByTagName("Duree").item(0).getTextContent());
						Date = Integer.parseInt(currentElement.getElementsByTagName("Date").item(0).getTextContent());

					} catch (Exception ex) {
						System.out.println("Something is wrong with the XML albums element");
					}
					Musique.addAlbum(Titre, ID , Artiste, Date);
				}

				else if (currentElement.getNodeName().equals("Chanson")) 	{
					try {
						TitreChanson1 = currentElement.getElementsByTagName("TitreChanson1").item(0).getTextContent();
						TitreChanson2 = currentElement.getElementsByTagName("TitreChanson2").item(0).getTextContent();
						TitreChanson3 = currentElement.getElementsByTagName("TitreChanson3").item(0).getTextContent();
						TitreChanson4 = currentElement.getElementsByTagName("TitreChanson4").item(0).getTextContent();
						TitreChanson5 = currentElement.getElementsByTagName("TitreChanson5").item(0).getTextContent();
						TitreChanson6 = currentElement.getElementsByTagName("TitreChanson6").item(0).getTextContent();
						TitreChanson7 = currentElement.getElementsByTagName("TitreChanson7").item(0).getTextContent();
						TitreChanson8 = currentElement.getElementsByTagName("TitreChanson8").item(0).getTextContent();
						TitreChanson9 = currentElement.getElementsByTagName("TitreChanson9").item(0).getTextContent();
						TitreChanson10 = currentElement.getElementsByTagName("TitreChanson10").item(0).getTextContent();
						TitreChanson11 = currentElement.getElementsByTagName("TitreChanson11").item(0).getTextContent();
						TitreChanson12 = currentElement.getElementsByTagName("TitreChanson12").item(0).getTextContent();
						TitreChanson13 = currentElement.getElementsByTagName("TitreChanson13").item(0).getTextContent();
						TitreChanson14 = currentElement.getElementsByTagName("TitreChanson14").item(0).getTextContent();
						TitreChanson15 = currentElement.getElementsByTagName("TitreChanson15").item(0).getTextContent();


					} catch (Exception ex) {
							System.out.println("Something is wrong with the XML albums element OR THE ALBUM IS NOT FULLED");
					}
					if (TitreChanson1 != "") {
						Musique.addSongToAlbum(TitreChanson1,Titre);
					}
					if (TitreChanson2 != "") {
						Musique.addSongToAlbum(TitreChanson2,Titre);
					}
					if (TitreChanson3 != "") {
						Musique.addSongToAlbum(TitreChanson3,Titre);
					}
					if (TitreChanson4 != "") {
						Musique.addSongToAlbum(TitreChanson4,Titre);
					}
					if (TitreChanson5 != "") {
						Musique.addSongToAlbum(TitreChanson5,Titre);
					}
					if (TitreChanson6 != "") {
						Musique.addSongToAlbum(TitreChanson6,Titre);
					}
					if (TitreChanson7 != "") {
						Musique.addSongToAlbum(TitreChanson7,Titre);
					}
					if (TitreChanson8 != "") {
						Musique.addSongToAlbum(TitreChanson8,Titre);
					}
					if (TitreChanson9 != "") {
						Musique.addSongToAlbum(TitreChanson9,Titre);
					}
					if (TitreChanson10 != "") {
						Musique.addSongToAlbum(TitreChanson10,Titre);
					}
					if (TitreChanson11 != "") {
						Musique.addSongToAlbum(TitreChanson11,Titre);
					}
					if (TitreChanson12 != "") {
						Musique.addSongToAlbum(TitreChanson12,Titre);
					}
					if (TitreChanson13 != "") {
						Musique.addSongToAlbum(TitreChanson13,Titre);
					}
					if (TitreChanson14 != "") {
						Musique.addSongToAlbum(TitreChanson14,Titre);
					}
					if (TitreChanson15 != "") {
						Musique.addSongToAlbum(TitreChanson15,Titre);
					}
					TitreChanson1 = "";	
					TitreChanson2 = "";	
					TitreChanson3 = "";	
					TitreChanson4 = "";	
					TitreChanson5 = "";	
					TitreChanson6 = "";	
					TitreChanson7 = "";	
					TitreChanson8 = "";	
					TitreChanson9 = "";	
					TitreChanson10 = "";
					TitreChanson11 = "";
					TitreChanson12 = "";
					TitreChanson13 = "";
					TitreChanson14 = "";
					TitreChanson15 = "";
				}
			}
		}
		XML_INPUT_FILE = "doc/playlists.xml";
		nodes = this.parseXMLFile(XML_INPUT_FILE);
		if (nodes == null) return;

		for (int i = 0; i<nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   {
				Element currentElement = (Element) nodes.item(i);
				if (currentElement.getNodeName().equals("Playlist")) 	{
					try {
						ID = Integer.parseInt(currentElement.getElementsByTagName("ID").item(0).getTextContent());
						Nom = currentElement.getElementsByTagName("Nom").item(0).getTextContent();

						//verify that I read everything correctly:
					} catch (Exception ex) {
					}
					Musique.addPlaylist(Nom); //ID PAS FAIT
					if (currentElement.getNodeName().equals("Chanson")) 	{
						try {
							TitreChanson1 = currentElement.getElementsByTagName("TitreChanson1").item(0).getTextContent();
							TitreChanson2 = currentElement.getElementsByTagName("TitreChanson2").item(0).getTextContent();
							TitreChanson3 = currentElement.getElementsByTagName("TitreChanson3").item(0).getTextContent();
							TitreChanson4 = currentElement.getElementsByTagName("TitreChanson4").item(0).getTextContent();
							TitreChanson5 = currentElement.getElementsByTagName("TitreChanson5").item(0).getTextContent();
							TitreChanson6 = currentElement.getElementsByTagName("TitreChanson6").item(0).getTextContent();
							TitreChanson7 = currentElement.getElementsByTagName("TitreChanson7").item(0).getTextContent();
							TitreChanson8 = currentElement.getElementsByTagName("TitreChanson8").item(0).getTextContent();
							TitreChanson9 = currentElement.getElementsByTagName("TitreChanson9").item(0).getTextContent();
							TitreChanson10 = currentElement.getElementsByTagName("TitreChanson10").item(0).getTextContent();

							//verify that I read everything correctly:
						} catch (Exception ex) {
							System.out.println("Something is wrong with the XML albums element OR THE PLAYLIST IS NOT FULLED");
						}
						if (TitreChanson1 != "") {
							Musique.addSongToPlaylist(TitreChanson1,Nom);
						}
						if (TitreChanson2 != "") {
							Musique.addSongToPlaylist(TitreChanson2,Nom);
						}
						if (TitreChanson3 != "") {
							Musique.addSongToPlaylist(TitreChanson3,Nom);
						}
						if (TitreChanson4 != "") {
							Musique.addSongToPlaylist(TitreChanson4,Nom);
						}
						if (TitreChanson5 != "") {
							Musique.addSongToPlaylist(TitreChanson5,Nom);
						}
						if (TitreChanson6 != "") {
							Musique.addSongToPlaylist(TitreChanson6,Nom);
						}
						if (TitreChanson7 != "") {
							Musique.addSongToPlaylist(TitreChanson7,Nom);
						}
						if (TitreChanson8 != "") {
							Musique.addSongToPlaylist(TitreChanson8,Nom);
						}
						if (TitreChanson9 != "") {
							Musique.addSongToPlaylist(TitreChanson9,Nom);
						}
						if (TitreChanson10 != "") {
							Musique.addSongToPlaylist(TitreChanson10,Nom);
						}
						TitreChanson1 = "";	
						TitreChanson2 = "";	
						TitreChanson3 = "";	
						TitreChanson4 = "";	
						TitreChanson5 = "";	
						TitreChanson6 = "";	
						TitreChanson7 = "";	
						TitreChanson8 = "";	
						TitreChanson9 = "";	
						TitreChanson10 = "";
						TitreChanson11 = "";
						TitreChanson12 = "";
						TitreChanson13 = "";
						TitreChanson14 = "";
						TitreChanson15 = "";
					}
					if (currentElement.getNodeName().equals("LivreAudio")) 	{
						try {
							TitreLivreAudio1 = currentElement.getElementsByTagName("TitreLivreAudio1").item(0).getTextContent();
							TitreLivreAudio2 = currentElement.getElementsByTagName("TitreLivreAudio2").item(0).getTextContent();
							TitreLivreAudio3 = currentElement.getElementsByTagName("TitreLivreAudio3").item(0).getTextContent();
							TitreLivreAudio4 = currentElement.getElementsByTagName("TitreLivreAudio4").item(0).getTextContent();
							TitreLivreAudio5 = currentElement.getElementsByTagName("TitreLivreAudio5").item(0).getTextContent();

							//verify that I read everything correctly:
						} catch (Exception ex) {
							System.out.println("Something is wrong with the XML albums element OR THE PLAYLIST IS NOT FULLED");
						}
						if (TitreLivreAudio1 != "") {
							Musique.addLivreAudioToPlaylist(TitreLivreAudio1,Nom);
						}
						if (TitreLivreAudio2 != "") {
							Musique.addLivreAudioToPlaylist(TitreLivreAudio2,Nom);
						}
						if (TitreLivreAudio3 != "") {
							Musique.addLivreAudioToPlaylist(TitreLivreAudio3,Nom);
						}
						if (TitreLivreAudio4 != "") {
							Musique.addLivreAudioToPlaylist(TitreLivreAudio4,Nom);
						}
						if (TitreLivreAudio5 != "") {
							Musique.addLivreAudioToPlaylist(TitreLivreAudio5,Nom);
						}
						TitreLivreAudio1 = "";	
						TitreLivreAudio2 = "";	
						TitreLivreAudio3 = "";	
						TitreLivreAudio4 = "";	
						TitreLivreAudio5 = "";	
					}
				}
			}
		}	

	}

	/**
	 * Methode pour démontrer l'écriture d'un fichier XML avec un seul élément
	 */	
	public void writeXML() {
		Document document = this.createXMLDocument();
		if (document == null) return;

		// create root element
		Element root = document.createElement("Chanson");
		document.appendChild(root);

		//save one "albums" element; create a loop to save more elements!!
		Element Chanson = document.createElement("Chanson");
		// albumsID element
		String id = "1";
		Element idElement = document.createElement("ID");
		idElement.appendChild(document.createTextNode(id));
		Chanson.appendChild(idElement);

		// Titre element
		String Titre = "The Album";
		Element firstNameElement = document.createElement("Titre");
		firstNameElement.appendChild(document.createTextNode(Titre));
		Chanson.appendChild(firstNameElement);

		//Artiste element
		String Artiste = "Blackpink";
		Element ArtisteElement = document.createElement("Artiste");
		ArtisteElement.appendChild(document.createTextNode(Artiste));
		Chanson.appendChild(ArtisteElement);

		//Duree element
		String Duree = "1466";
		Element DureeElement = document.createElement("Duree");
		DureeElement.appendChild(document.createTextNode(Duree));
		Chanson.appendChild(DureeElement);

		//Genre element
		String Genre = "Pop";
		Element GenreElement = document.createElement("Genre");
		GenreElement.appendChild(document.createTextNode(Genre));
		Chanson.appendChild(GenreElement);

		root.appendChild(Chanson);

		this.createXMLFile(document, XML_OUTPUT_FILE);
	}

	public static void main (String[] args)
	{
		XMLelement demo = new XMLelement();
		demo.readXML();
		demo.writeXML();
	}

}
