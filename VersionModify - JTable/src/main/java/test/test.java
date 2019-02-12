package main.java.test;

import java.util.ArrayList;

import main.java.main.Datei;
import main.java.main.XML_Editor;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Datei inwoh = new Datei("inwo-hilfe", "diwo",
				"C:\\Users\\A92939692\\Desktop\\inwo\\source\\inwo-hilfe\\hilfe-view\\public_html\\WEB-INF\\web.xml");
		Datei inwo = new Datei("inwo", "inwo",
				"C:\\Users\\A92939692\\Desktop\\inwo\\source\\inwo\\inwo-view\\public_html\\WEB-INF\\web.xml");
		Datei diwo = new Datei("diwo", "diwo",
				"C:\\Users\\A92939692\\Desktop\\inwo\\source\\diwo\\diwo-view\\public_html\\WEB-INF\\web.xml");
		Datei admin = new Datei("admin", "admin",
				"C:\\Users\\A92939692\\Desktop\\inwo\\source\\administration\\admin-view\\public_html\\WEB-INF\\web.xml");
		String wert = "4.4.4.4";
		ArrayList<Datei> dateiliste = new ArrayList<Datei>();
		dateiliste.add(inwoh);
		dateiliste.add(inwo);
		dateiliste.add(diwo);
		dateiliste.add(admin);
		for (int i = 0; i < dateiliste.size(); i++) {
			XML_Editor.update(dateiliste.get(i), wert);
		}

	}

}
