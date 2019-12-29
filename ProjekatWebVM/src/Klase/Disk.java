package Klase;

public class Disk {
	private String ime,tip,ime_vm;
	private int kapacitet;
	public Disk(String ime, String tip, String ime_vm, int kapacitet) {
		super();
		this.ime = ime;
		this.tip = tip;
		this.ime_vm = ime_vm;
		this.kapacitet = kapacitet;
	}
	public Disk() {
		super();
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getIme_vm() {
		return ime_vm;
	}
	public void setIme_vm(String ime_vm) {
		this.ime_vm = ime_vm;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	
	
}
