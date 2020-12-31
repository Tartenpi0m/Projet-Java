import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;

import java.util.Scanner;

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
		System.out.println("Choisissez le fichier à afficher: albums.xml, playlists.xml, elements.xml.");
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

	Scanner scan = new Scanner(System.in);
/**
* Le nom du fichier XML qu'on lit
*/
	String fichier = scan.nextLine();
	
	//private static String XML_INPUT_FILE = "elements.xml";
	
	
	private  String XML_INPUT_FILE = fichier;
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
		NodeList nodes = this.parseXMLFile(XML_INPUT_FILE);
		if (nodes == null) return;
		
		for (int i = 0; i<nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE)   {
				Element currentElement = (Element) nodes.item(i);
				if (currentElement.getNodeName().equals("Chanson")) 	{
					try {
						String Titre = currentElement.getElementsByTagName("Titre").item(0).getTextContent();
						String Artiste = currentElement.getElementsByTagName("Artiste").item(0).getTextContent();
						String Duree = currentElement.getElementsByTagName("Duree").item(0).getTextContent();
					        String ID = currentElement.getElementsByTagName("ID").item(0).getTextContent();
						String Genre = currentElement.getElementsByTagName("Genre").item(0).getTextContent();
						
						//verify that I read everything correctly:
						System.out.println("\n" + "Chanson: " + "\n"+  "Titre: " +Titre + "\n" +"Artiste :"+ Artiste + "\n"+ "Duree: " +  Duree + " secondes" + "\n"+ "Genre :" + Genre + "\n" + "IDUNique: " +ID.toString());
					} catch (Exception ex) {
						System.out.println("Something is wrong with the XML albums element");
					}
				}
				else if (currentElement.getNodeName().equals("LivreAudio")) 	{
					try {
						String Titre = currentElement.getElementsByTagName("Titre").item(0).getTextContent();
						String Auteur = currentElement.getElementsByTagName("Auteur").item(0).getTextContent();
						String Duree = currentElement.getElementsByTagName("Duree").item(0).getTextContent();
						String ID = currentElement.getElementsByTagName("ID").item(0).getTextContent();
						String Langue = currentElement.getElementsByTagName("Langue").item(0).getTextContent();
						String Categorie = currentElement.getElementsByTagName("Categorie").item(0).getTextContent();
						
						//verify that I read everything correctly:
						System.out.println("\n" + "LivreAudio: " +"\n"  + " Titre: " + Titre + "\n"+ "Auteur: "+ Auteur + "\n" + "Duree: " + Duree + " secondes" + "\n"+ "Langues: " + Langue + "\n"+"Categorie: " + Categorie + "\n" + "IDUnique: "+ ID.toString());
					} catch (Exception ex) {
						System.out.println("Something is wrong with the XML albums element");
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
