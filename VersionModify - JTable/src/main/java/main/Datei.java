package main.java.main;

import java.io.File;

public class Datei {

	// Attribute
	private String name;
	private String pfad;
	private String rname;

	// Konstruktoren
	public Datei(String name, String rname, String pfad) {

		this.name = name;
		this.pfad = pfad;
		this.rname = rname;
	}

	public Datei() {

	}

	// Getter & Setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String name) {
		this.rname = name;
	}

	public String getPfad() {
		return pfad;
	}

	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	public boolean exists() {

		File f = new File(getPfad());
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}

}
