package main.java.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML_Editor {
	//Java Konstanten 
	final private static String TAGNAME = "context-param";
	final private static String UNTERTAG1 = "param-name";
	final private static String UNTERTAG2 = "param-value";

	// Setzen eines neuen Tag-inhaltes
	public static boolean update(Datei datei, String content) {
		String tmp = "";
		boolean gefunden = false;
		String vname = datei.getName() + ".version";
		Logger logger = LoggerFactory.getLogger(XML_Editor.class);
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(datei.getPfad());
			int lenght = document.getElementsByTagName(TAGNAME).getLength();

			
			//Gehe alle Tages mit dem tagnamen durch 
			for (int i = 0; i < lenght; i++) {

				Node tag = document.getElementsByTagName(TAGNAME).item(i);
				NodeList nodes = tag.getChildNodes();
				//Gehe alle Untertags durch
				for (int j = 0; j < nodes.getLength(); j++) {

					Node element = nodes.item(j);

					if (element != null) {
						//Suche nach dem Untertag der der Variable untertag1 entspricht
						if (UNTERTAG1.equals(element.getNodeName())) {
							//Wenn der Inhalt des Untertags dem vnamen entricht setze attribut gefunden auf wahr
							if (vname.equals(nodes.item(j).getTextContent())) {
								logger.info("Element[{}] entspricht {}", j, vname);

								gefunden = true;

							}

						}
						//Wemm untertag2 dem akutellen tagnamen entspricht und gefunden wahr ist passe wert an 
						if (UNTERTAG2.equals(element.getNodeName()) && gefunden) {
							tmp = element.getTextContent();
							element.setTextContent(content);
							logger.info("{} in {} wird angepasst ! ", vname, datei.getPfad());
							logger.info(" alter Wert:" + tmp + " neuer Wert: {}", element.getTextContent());
							gefunden = false;
						}
					} else {

						logger.info("Ein Fehler liegt vor XML-Tag existiert nicht!");
						logger.error("XML-Tag existiert nicht im Dateipfad {} !", datei.getPfad());
						return false;

					}
				}

			}
			// speichern / schreiben auf die Datei

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer;
			try {
				transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);

				StreamResult streamResult = new StreamResult(new File(datei.getPfad()));
				transformer.transform(domSource, streamResult);
				logger.info("Done");
				return true;
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				logger.error("Fehler!", e);
				logger.info("Fehler!");
				e.printStackTrace();
			} catch (TransformerException e) {
				logger.error("Fehler!", e);
				logger.info("Fehler!");
				e.printStackTrace();
			}
		} catch (SAXException e) {
			logger.error("Fehler!", e);
			logger.info("Fehler!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Fehler!", e);
			logger.info("Fehler!");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			logger.error("Fehler!", e);
			logger.info("Fehler!");
			e.printStackTrace();
		}
		return false;
	}

	// Laden des Taginhaltes
	public static String showContent(Datei datei) {
		boolean gefunden = false;
		String vname = datei.getName() + ".version";
		Logger logger = LoggerFactory.getLogger(XML_Editor.class);
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(datei.getPfad());
			int lenght = document.getElementsByTagName(TAGNAME).getLength();
			// Gehe alle Tags durch die den Tagnamen entsprechen
			for (int i = 0; i < lenght; i++) {

				Node tag = document.getElementsByTagName(TAGNAME).item(i);
				NodeList nodes = tag.getChildNodes();
				//Gehe alle Untertags durch 
				for (int j = 0; j < nodes.getLength(); j++) {
					
					Node element = nodes.item(j);

					if (element != null) {
						//Entspricht der Untertagname dem Untertag1 logge dies und setze das attribut gefunden auf wahr
						if (UNTERTAG1.equals(element.getNodeName())) {

							if (vname.equals(nodes.item(j).getTextContent())) {
								logger.debug("Element[{}] entspricht {}", j, vname);

								gefunden = true;

							}

						}
						//Entspricht der Untertagname dem Untertag2 so logge dies und geben den inhalt dieses Tags zurück
						if (UNTERTAG2.equals(element.getNodeName()) && gefunden) {

							logger.debug("{} gefunden! ", vname);
							gefunden = false;
							return element.getTextContent();
						}
					} else {
						//Logge wenn Datei nicht existiert 
						logger.info("Ein Fehler liegt vor XML-Tag existiert nicht!");
						logger.error("XML-Tag existiert nicht im Dateipfad {} !", datei.getPfad());

					}
				}

			}
			// speichern / schreiben auf die Datei

		} catch (SAXException e) {
			logger.error("Fehler!", e);
			logger.info("Fehler!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Fehler!", e);
			logger.info("Fehler!");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			logger.error("Fehler!", e);
			logger.info("Fehler!");
			e.printStackTrace();
		}
		return "Kein Eintrag";
	}

	private XML_Editor(){}

}
