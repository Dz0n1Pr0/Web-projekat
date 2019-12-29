package Klase;

public class Kategorija {
	private String ime;
	private int broj_jezgara,ram,gpu_jezgra;
	public Kategorija(String ime, int broj_jezgara, int ram, int gpu_jezgra) {
		super();
		this.ime = ime;
		this.broj_jezgara = broj_jezgara;
		this.ram = ram;
		this.gpu_jezgra = gpu_jezgra;
	}
	public Kategorija() {
		super();
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
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
	
	
}
