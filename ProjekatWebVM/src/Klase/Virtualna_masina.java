package Klase;

import java.util.ArrayList;

public class Virtualna_masina {
	private String ime,kategorija;
	private int broj_jezgara,ram,gpu_jezgra;
	private ArrayList<String> diskovi = new ArrayList<String>();
	private ArrayList<String> aktivnosti = new ArrayList<String>();
	
	public Virtualna_masina(String ime, String kategorija, int broj_jezgara, int ram, int gpu_jezgra,
			ArrayList<String> diskovi, ArrayList<String> aktivnosti) {
		super();
		this.ime = ime;
		this.kategorija = kategorija;
		this.broj_jezgara = broj_jezgara;
		this.ram = ram;
		this.gpu_jezgra = gpu_jezgra;
		this.diskovi = diskovi;
		this.aktivnosti = aktivnosti;
	}
	
	
	
	public Virtualna_masina() {
		super();
	}



	public ArrayList<String> getAktivnosti() {
		return aktivnosti;
	}



	public void setAktivnosti(ArrayList<String> aktivnosti) {
		this.aktivnosti = aktivnosti;
	}



	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getKategorija() {
		return kategorija;
	}
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
	public int getBroj_jezgara() {
		return broj_jezgara;
	}
	public void setBroj_jezgara(int broj_jezgara) {
		this.broj_jezgara = broj_jezgara;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getGpu_jezgra() {
		return gpu_jezgra;
	}
	public void setGpu_jezgra(int gpu_jezgra) {
		this.gpu_jezgra = gpu_jezgra;
	}
	public ArrayList<String> getDiskovi() {
		return diskovi;
	}
	public void setDiskovi(ArrayList<String> diskovi) {
		this.diskovi = diskovi;
	}

	
}
