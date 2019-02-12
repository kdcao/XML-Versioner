package main.java.TUI;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.main.Datei;
import main.java.main.DateiList;
import main.java.main.Funktion;
import main.java.main.XML_Editor;

public class main {
	final static String prePfad = "C:\\Users\\A92939692\\Desktop\\Projektverzeichnis";
	public static void main(String[] args) throws IOException {
		// Konstruktor aufruf für den Logger
		Logger logger = LoggerFactory.getLogger(main.class);
		// Konstruktor aufruf für den Scanner
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		// Objekt Erzeugnung Datenliste -> für die speicherung der Dateipfade in
		// der Arraylist
		DateiList data = new DateiList();
		// Attribute mit Wahrheitswert zur Wiederholung des Programmes
		boolean weiter = true;

		do {
			
			// Attribut wahl wird mit 6 initialisiert für den Fall das die
			// Eingabe fehlerhaft war
			int wahl = 6;
			// Ausgabe des Menus
			System.out.println("-------------------------------------------");
			System.out.println("               XML-Modifyer");
			System.out.println("-------------------------------------------");
			System.out.println("1. Alle Anzeigen");
			System.out.println("2. Alle Updaten");
			System.out.println("3. Bestimmtes Updaten");
			System.out.println("4. Dateipfad hinzufuegen");
			System.out.println("5. Dateipfad löschen");
			System.out.println("6. Programm beenden");
			System.out.println("-------------------------------------------");
			System.out.print("Auswahl: ");
			// Abrage nach der Auswahl
			try {
				wahl = scan.nextInt();
			} catch (Exception e) {
				// Sollte die Eingabe für die Auswahl fehlerhaft sein wird dies
				// geloggt und es wird eine entsprechende Ausgabe erzeugt
				logger.error("Falsche Eingabe!");
				logger.error("Fehler: ", e);
				logger.info("Ungültige Eingabe!");

			}
			// Laden der Dateipfade aus der Textdatei in die Arraylist
			data.load();
			//In Abhänigkeit von der Wahl des Users wird eine bestimmte aktion ausgeführt
			switch (wahl) {
			case 1:
				//ruft methode showList der Klasse main auf 
				Funktion.showList(data);
				break;
			case 2:
				//Es wird folgende Aktion im logger vermerkt
				Funktion.showList(data);
				logger.info("User moechte alle Versionen anpassen");
				Funktion.updateAll(data,Funktion.contentAbfrage());
				break;
			case 3:
				//Bildschirm Ausgabe  
				System.out.println("-------------------------------------------");
				System.out.println("          Ein Element Updaten");
				Funktion.showList(data);
				System.out.println("-------------------------------------------");
				System.out.print("Auswahl: ");
				int select = scan.nextInt();
				System.out.println("-------------------------------------------");
				//Logger log vom User ausgewählte aktion 
				logger.info("User moechte Element {} updaten", select);
				//Methode update wird aufgerufen um xml  die XML-Datei zu bearbeiten dabei wird die vom Usergewählte datei und eine weitere Methode als paramenter übergeben
				XML_Editor.update(data.getFileList().get(select), Funktion.contentAbfrage());
				// Methode fordert User zur Eingabe eines Wertes auf, welcher in die XML-Datei geschreiben werden soll
				break;
			case 4:
				//Bildschirm Ausgabe
				System.out.println("-------------------------------------------");
				System.out.println("            Neuer Dateipfad ");
				System.out.println("-------------------------------------------");
				System.out.print("Programmname: ");
				//User wird nach den Programmname gefragt dieser wird der Variable rname zugewiesen 
				String rname = scan.next();
				System.out.println("Dateiversionname: ");
				System.out.println("z.B. diwo  von diwo.version");
				//User wird nach den Dateiversionname gefragt dieser wird der Variable name zugewiesen 
				String vname = scan.next();
				String pfad = "";
				System.out.println("Dateipfad auto. generieren ? (Y/N) ");
				System.out.print("Sonderfälle werden nicht beachtet!(z.B ...\\inwo-hilfe\\");
				System.err.println("hilfe-view\\...)");
				//Der User wird gefragt ob der Dateipfad autogeneriert werden soll
				String gen = scan.next();
				// Gibt der User den wert y ein so wird der Pfad autogeneriert 
				if (gen.toUpperCase().equals("Y")) {
					pfad = prePfad +"\\source\\" + rname + "\\" + rname+ "-view\\public_html\\WEB-INF\\web.xml";
				} else {
					//Andernfalls erhält der User die Möglichkeit einen Dateipfad einzugeben/einzufügen 
					System.out.print("Dateipfad: ");
					pfad = scan.next();

				}
				//Methoden aufruf -> zum hinzufügen des neuen Dateipfades 
				Funktion.add(data, new Datei(vname.toLowerCase(), rname.toLowerCase(), pfad));
				//Anschließend sollen alle Dateipfade angezeigt werden
				Funktion.showList(data);
				break;

			case 5:
				//Bildschirm Ausgabe 
				System.out.println("-------------------------------------------");
				System.out.println("           Dateipfad löschen ");
				Funktion.showList(data);//Anzeigen aller Dateipfade
				System.out.println("-------------------------------------------");
				System.out.print("Auswahl: ");
				int zahl = scan.nextInt();//Wahl des zu Löschenden Dateipfades

				Funktion.remove(data, zahl);//Methodenaufruf zum löschen des gewählten Eintrages
				logger.info("User löscht den {}. Dateipfad", zahl);//Logger logt erfolgt Infoformation
				Funktion.showList(data);//Anschließend nochmal alle Dateipfade anzeigen 
				break;
			case 6:
				weiter = false;//Wahrheitswert wird auf false gesetzt um schleife zu beenden 
				logger.info("Programm wird beendet!");//dazugehörige Information wird im Logger vermerkt
				break;

			default:

				logger.info("Unbekannte Eingabe: {}", wahl); // Bei unültigen Eingaben die den Datentyp Int entsprechen wird dem User berichtet das seine Eingabe unglültig ist
				break;
			}
		} while (weiter);

	}

	
}
