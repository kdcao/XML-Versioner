package main.java.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DateiList {

	// Attribute
	ArrayList<Datei> fileList = new ArrayList<Datei>();
	final private String filename = "paths.dat";

	// Konstruktoren
	public DateiList(ArrayList<Datei> fileList) {
		super();
		this.fileList = fileList;
	}

	public DateiList() {
		super();
	}

	// Getter & Setter
	public ArrayList<Datei> getFileList() {
		return fileList;
	}

	public void setFileList(ArrayList<Datei> fileList) {
		this.fileList = fileList;
	}

	public String getFilename() {
		return filename;
	}


	
	
	// Methoden


	
	
	// Laden der Inhalte in ArrayList von Textdatei
	public void load() throws IOException {
		this.fileList.clear(); //leeren der ArrayList
		File datei = new File(filename);
		if (!datei.exists()) {// Wenn datei nicht existiert wird eine Neue erzeugt
			
			
			datei.createNewFile(); 

			FileOutputStream oFile = new FileOutputStream(datei, false);
			
			//Schreiben des Kopfes der Textdatei 
			PrintWriter writer = new PrintWriter(filename);
			writer.println("1. Dateiname");
			writer.println("2. Dateiversionname");
			writer.println("3. Dateipfad");
			writer.println("Bespiel");
			writer.println("NaN");
			writer.println("C://Dateipfad//web.xml");
			writer.close();
		}

		BufferedReader in = new BufferedReader(new FileReader(filename));
		//Lesen der ersten 3 Zeilen 
		String path = in.readLine();
		String name = in.readLine();
		String rname = in.readLine();
		//Ausführen des Schleifeninhaltes solange die drei Variablen nicht null sind
		while (path != null && name != null&& rname != null) {
			rname = in.readLine(); //Einlesen des Programmnamens
			name = in.readLine(); //Einlesen des Versionnamens
			path = in.readLine();//Einlesen des Dateipfades
			if (path == null && name == null&& rname == null) {
				break;
			}
	
			//Erzeugung eines Objektes mit den 3 eingelesen Variablen 
			this.fileList.add(new Datei(name, rname, path));//Hinzufügen des Erzeugnten Objektes
		}
	}

	// ArrayList Inhalt in Textdatei speichern
	public void save() throws IOException {
		PrintWriter writer = new PrintWriter(filename);
		//Schreiben der ersten 3 Zeilen
		writer.println("1. Dateiname");
		writer.println("2. Dateiversionname");
		writer.println("3. Dateipfad");
		// Gehe alle elemente durch und schreibe diese in die Datei
		for (int i = 0; i < this.fileList.size(); i++) {
			if (this.fileList.get(i).getName() != null && this.fileList.get(i).getPfad() != null) { 
				// Bei leeren objekten nicht in die datei schreiben
				//Schreiben des Programmnamen in die Datei
				writer.println(this.fileList.get(i).getRname());
				//Schreiben des Versionnamen in die Datei
				writer.println(this.fileList.get(i).getName());
				//Schreiben des Dateipfades in die Datei
				writer.println(this.fileList.get(i).getPfad());
			}
		}
		writer.close();
	}
	
	
	public boolean exists(String pfad){
		for (int i = 0; i < this.fileList.size(); i++) {
			if (pfad.toUpperCase().equals(this.fileList.get(i).getPfad().toUpperCase())) {
				return true;
			}
		}
		return false;
	}

}
