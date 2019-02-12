package main.java.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class fileWriter {
	
	private fileWriter() {
		// TODO Auto-generated constructor stub
	}
	
	public static String load() throws IOException {
	 
		File datei = new File("Einstellung.config");
		if (!datei.exists()) {// Wenn datei nicht existiert wird eine Neue erzeugt
			
			
			datei.createNewFile(); 
	
			FileOutputStream oFile = new FileOutputStream(datei, false);
			
			//Schreiben des Kopfes der Textdatei 
			PrintWriter writer = new PrintWriter("Einstellung.config");
			writer.println("C:\\Users\\A92939692\\Desktop\\Projektverzeichnis");
			writer.close();
		}

		BufferedReader in = new BufferedReader(new FileReader("Einstellung.config"));
	
		String path = in.readLine();
		return path;
	}

	// ArrayList Inhalt in Textdatei speichern
	public static void save(String pfad) throws IOException {
		PrintWriter writer = new PrintWriter("Einstellung.config");
	
		writer.println(pfad);
	
		
		writer.close();
	}
}
