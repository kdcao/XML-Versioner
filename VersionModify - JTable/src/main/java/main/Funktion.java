package main.java.main;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Funktion {
	
	private static Scanner scan = new Scanner(System.in);
	private static Logger logger = LoggerFactory.getLogger(Funktion.class);
	private Funktion(){
	}
	
	
	// methode zum hinzufügen neuer Einträge und zum speichern dieser in der
		// Textdatei
		public static boolean add(DateiList liste, Datei data) {
			
			if (!liste.exists(data.getPfad())) {
				try {
				//Laden der Dateipfade in die Arraylist
					liste.load();
					liste.getFileList().add(data);//Hinzufügen des Neuen Dateipfades 
					liste.save();// Speicherung in die Textdatei
					logger.info("Programm {} hinzugefügt", data.getRname()); //Logger übergibt entsprechende Meldung
					return true;
				} catch (Exception e) {
					logger.error("Fehler!", e);// Bei Fehlern wird dies ebenfalls im Logger vermerkt 
					return false;
				}
			}else{
				
				logger.info("Dateipfad existiert bereits!");
				return false;
			}
		}

		// methode zum löschen eines Eintrages aus der Arrayliste und der Textdatei
		public static boolean remove(DateiList liste, int zahl) {
			
			try {
				//Laden der Dateipfade in die Arraylist
				liste.load();
				//Entfernen des zuentfernenden Inhaltes 
				liste.getFileList().remove(zahl);
				logger.info("Element {} wurde entfernt", zahl);
				//Speicherung der Arraylist in einer Textdatei
				liste.save();
				return true;
			} catch (IOException e) {
				
				e.printStackTrace();
				logger.error("Fehler:",e);
				return false;
			}
			
		}

		// Ausgabe der Arraylist -> Programmname, Dateipfad und version
		public static void showList(DateiList liste) {
			
			//Ausgabe der Dateipfade mit ihren Zugehörigen Programmnamen sowie Versionen 
			System.out.println("-------------------------------------------");
			System.out.println("NR - Programmname  - Version - Dateipfad");
			System.out.println("-------------------------------------------");
			for (int j = 0; j < liste.getFileList().size(); j++) {
				String format = "%s%-15s%s%s%n"; // Format für die Ausgabe
				//Ausgabe des Index - Programmnamen - Version - Dateipfad
				System.out.printf(format, (j), "  - " + liste.getFileList().get(j).getRname(),
						"  - " + XML_Editor.showContent(liste.getFileList().get(j)), " - " + liste.getFileList().get(j).getPfad());
				
				/*
				System.out.println((j)+ "  - " + liste.getFileList().get(j).getRname()+
						"  - " + xml.showContent(liste.getFileList().get(j))+ " - " + liste.getFileList().get(j).getPfad());
						
						*/
				// System.out.println((j) + " - " +
				// liste.getFileList().get(j).getRname() + " - "
				// +xml.showContent(liste.getFileList().get(j)) + " - " +
				// liste.getFileList().get(j).getPfad());
			}
		}

		// Methode zur A
		public static String contentAbfrage() {
			// Eingabe des zu schreibenden Inhaltes
			System.out.print("Enter Content: ");
			String content = scan.next();
			return content;

		}

		public static boolean updateAll(DateiList data,String content){
		try {
			for (int i = 0; i < data.getFileList().size(); i++) { // Schleifeninhalt wird solange ausgeführt bis zähler die maximal länge des Arraylist erreicht hat
				XML_Editor.update(data.getFileList().get(i), content); //führt Methode update des Objektes xml aus
			}
			return true;
		} catch (Exception e) {
			logger.error("Fehler",e);
			return false;
		}
			
		}	
	
}
