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
	
		String Titre ="";
		String Artiste ="";
		int Duree = 0;
		int ID = 0;
		int Date =0;
		String Genre2 = "";
		String Auteur = "";
		String Langue2 = "";
		String Categorie2 = "";
		XML_INPUT_FILE = "files/elements.xml";
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
						System.out.println("Incident lors de la lecture du fichier xml");
					}
					if (Genre2.equals("Jazz") ){
						GestionStructureMusicale.addSong(Titre, ID, Artiste, Genre.JAZZ, Duree);
					}
					else if(Genre2.equals("Pop") ) {
						GestionStructureMusicale.addSong(Titre, ID, Artiste, Genre.POP, Duree);
					}
					else if (Genre2.equals("Rock") ){
						GestionStructureMusicale.addSong(Titre, ID, Artiste, Genre.ROCK, Duree);
					}
					else if (Genre2.equals("Hip-Hop")) {
						GestionStructureMusicale.addSong(Titre, ID, Artiste, Genre.HIPHOP, Duree);
					}
					else if (Genre2.equals("Classique")) {
						GestionStructureMusicale.addSong(Titre, ID, Artiste, Genre.CLASSIQUE, Duree);
					}
					else if (Genre2.equals("Rap") ){
						GestionStructureMusicale.addSong(Titre, ID, Artiste, Genre.RAP, Duree);
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
						System.out.println("Incident lors de la lecture du fichier xml");
					}
					if (Categorie2.equals("Jeunesse")){
						if (Langue2.equals("Français")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.JEUNESSE, Duree);
						}
						else if (Langue2.equals("Anglais")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.JEUNESSE, Duree);
						}
						else if (Langue2.equals("Italien")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.JEUNESSE, Duree);
						}
						else if (Langue2.equals("Allemand")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.JEUNESSE, Duree);
						}
						else if (Langue2.equals("Inconnu")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.JEUNESSE, Duree);
						}
						else if (Langue2.equals("Espagnol")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.JEUNESSE, Duree);
						}
					}
					if (Categorie2.equals("Roman")){
						if (Langue2.equals("Français")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.ROMAN, Duree);
						}
						else if (Langue2.equals("Anglais")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.ROMAN, Duree);
						}
						else if (Langue2.equals("Italien")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.ROMAN, Duree);
						}
						else if (Langue2.equals("Allemand")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.ROMAN, Duree);
						}
						else if (Langue2.equals("Inconnu")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.ROMAN, Duree);
						}
						else if (Langue2.equals("Espagnol")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.ROMAN, Duree);
						}
					}
					if (Categorie2.equals("Théatre")){
						if (Langue2.equals("Français")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.THEATRE, Duree);
						}
						else if (Langue2.equals("Anglais")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.THEATRE, Duree);
						}
						else if (Langue2.equals("Italien")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.THEATRE, Duree);
						}
						else if (Langue2.equals("Allemand")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.THEATRE, Duree);
						}
						else if (Langue2.equals("Inconnu")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.THEATRE, Duree);
						}
						else if (Langue2.equals("Espagnol")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.THEATRE, Duree);
						}
					}
					if (Categorie2.equals("Discours")){
						if (Langue2.equals("Français")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.DISCOURS, Duree);
						}
						else if (Langue2.equals("Anglais")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.DISCOURS, Duree);
						}
						else if (Langue2.equals("Italien")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.DISCOURS, Duree);
						}
						else if (Langue2.equals("Allemand")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.DISCOURS, Duree);
						}
						else if (Langue2.equals("Inconnu")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.DISCOURS, Duree);
						}
						else if (Langue2.equals("Espagnol")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.DISCOURS, Duree);
						}
					}
					if (Categorie2.equals("Documentaire")){
						if (Langue2.equals("Français")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.DOCUMENTAIRE, Duree);
						}
						else if (Langue2.equals("Anglais")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.DOCUMENTAIRE, Duree);
						}
						else if (Langue2.equals("Italien")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.DOCUMENTAIRE, Duree);
						}
						else if (Langue2.equals("Allemand")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.DOCUMENTAIRE, Duree);
						}
						else if (Langue2.equals("Inconnu")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.DOCUMENTAIRE, Duree);
						}
						else if (Langue2.equals("Espagnol")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.DOCUMENTAIRE, Duree);
						}
					}
					if (Categorie2.equals("Inconnu")){
						if (Langue2.equals("Français")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.FRANCAIS, Categorie.INCONNU, Duree);
						}
						else if (Langue2.equals("Anglais")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ANGLAIS, Categorie.INCONNU, Duree);
						}
						else if (Langue2.equals("Italien")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ITALIEN, Categorie.INCONNU, Duree);
						}
						else if (Langue2.equals("Allemand")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ALLEMAND, Categorie.INCONNU, Duree);
						}
						else if (Langue2.equals("Inconnu")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.INCONNU, Categorie.INCONNU, Duree);
						}
						else if (Langue2.equals("Espagnol")) {
							GestionStructureMusicale.addLivreAudio(Titre, ID, Auteur, Langue.ESPAGNOL, Categorie.INCONNU, Duree);
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
		nodes = this.parseXMLFile("files/albums.xml");
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
						GestionStructureMusicale.addAlbum(Titre, ID , Artiste, Date, Duree);

					} catch(AlreadyExistException a) { 
					} catch (Exception ex) {
						System.out.println("Incident lors de la lecture du fichier xml");
					}
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
							System.out.println("Incident lors de la lecture du fichier xml OR THE ALBUM IS NOT FULLED");
					}

					try {
						if (TitreChanson1 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson1,Titre);
						}
						if (TitreChanson2 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson2,Titre);
						}
						if (TitreChanson3 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson3,Titre);
						}
						if (TitreChanson4 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson4,Titre);
						}
						if (TitreChanson5 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson5,Titre);
						}
						if (TitreChanson6 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson6,Titre);
						}
						if (TitreChanson7 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson7,Titre);
						}
						if (TitreChanson8 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson8,Titre);
						}
						if (TitreChanson9 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson9,Titre);
						}
						if (TitreChanson10 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson10,Titre);
						}
						if (TitreChanson11 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson11,Titre);
						}
						if (TitreChanson12 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson12,Titre);
						}
						if (TitreChanson13 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson13,Titre);
						}
						if (TitreChanson14 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson14,Titre);
						}
						if (TitreChanson15 != "") {
							GestionStructureMusicale.addSongToAlbum(TitreChanson15,Titre);
						} 

						} catch(NotExistException ex2) {

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
		XML_INPUT_FILE = "files/playlists.xml";
		nodes = this.parseXMLFile(XML_INPUT_FILE);
		if (nodes == null) return;

		for (int i = 0; i<nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   {
				Element currentElement = (Element) nodes.item(i);
				if (currentElement.getNodeName().equals("Playlist")) 	{
					try {
						ID = Integer.parseInt(currentElement.getElementsByTagName("ID").item(0).getTextContent());
						Nom = currentElement.getElementsByTagName("Nom").item(0).getTextContent();
						GestionStructureMusicale.addPlaylist(Nom, ID); 

						//verify that I read everything correctly:
					} catch(AlreadyExistException a) {

				 	} catch (Exception ex) {
					}
				}
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
							

						} catch (Exception ex) {
						}


						try {

							if (TitreChanson1 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson1,Nom);
							}
							if (TitreChanson2 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson2,Nom);
							}
							if (TitreChanson3 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson3,Nom);
							}
							if (TitreChanson4 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson4,Nom);
							}
							if (TitreChanson5 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson5,Nom);
							}
							if (TitreChanson6 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson6,Nom);
							}
							if (TitreChanson7 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson7,Nom);
							}
							if (TitreChanson8 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson8,Nom);
							}
							if (TitreChanson9 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson9,Nom);
							}
							if (TitreChanson10 != "") {
								GestionStructureMusicale.addSongToPlaylist(TitreChanson10,Nom);
							}

							//verify that I read everything correctly:
						} catch(NotExistException e) {
						
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

						 }catch (Exception ex) {
							
						}


						try {
							if (TitreLivreAudio1 != "") {
								GestionStructureMusicale.addLivreAudioToPlaylist(TitreLivreAudio1,Nom);
							}
							if (TitreLivreAudio2 != "") {
								GestionStructureMusicale.addLivreAudioToPlaylist(TitreLivreAudio2,Nom);
							}
							if (TitreLivreAudio3 != "") {
								GestionStructureMusicale.addLivreAudioToPlaylist(TitreLivreAudio3,Nom);
							}
							if (TitreLivreAudio4 != "") {
								GestionStructureMusicale.addLivreAudioToPlaylist(TitreLivreAudio4,Nom);
							}
							if (TitreLivreAudio5 != "") {
								GestionStructureMusicale.addLivreAudioToPlaylist(TitreLivreAudio5,Nom);
							}
							//verify that I read everything correctly:
						} catch(NotExistException e) {
						
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
