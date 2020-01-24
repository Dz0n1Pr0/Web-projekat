package Klase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Podaci {
	public HashMap<String,Korisnik> korisnici = new HashMap<String,Korisnik>();
	public HashMap<String,Disk> diskovi = new HashMap<String,Disk>();
	public HashMap<String,Kategorija> kategorije = new HashMap<String,Kategorija>();
	public HashMap<String,Organizacija> organizacije = new HashMap<String,Organizacija>();
	public HashMap<String,Virtualna_masina> virtualne_masine = new HashMap<String,Virtualna_masina>();
	public HashMap<String,Aktivnost> aktivnosti = new HashMap<String,Aktivnost>();
	public Korisnik korisnik = new Korisnik();
	public String admin="";
	public Podaci(HashMap<String, Korisnik> korisnici, HashMap<String, Disk> diskovi,
			HashMap<String, Kategorija> kategorije, HashMap<String, Organizacija> organizacije,
			HashMap<String, Virtualna_masina> virtualne_masine, Korisnik korisnik, String admin) {
		super();
		this.korisnici = korisnici;
		this.diskovi = diskovi;
		this.kategorije = kategorije;
		this.organizacije = organizacije;
		this.virtualne_masine = virtualne_masine;
		this.korisnik = korisnik;
		this.admin = admin;
	}
	public Podaci() {
		try {
			UcitavanjeFajlova();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashMap<String, Korisnik> getKorisnici() {
		return korisnici;
	}
	public void setKorisnici(HashMap<String, Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	public HashMap<String, Disk> getDiskovi() {
		return diskovi;
	}
	public void setDiskovi(HashMap<String, Disk> diskovi) {
		this.diskovi = diskovi;
	}
	public HashMap<String, Kategorija> getKategorije() {
		return kategorije;
	}
	public void setKategorije(HashMap<String, Kategorija> kategorije) {
		this.kategorije = kategorije;
	}
	public HashMap<String, Organizacija> getOrganizacije() {
		return organizacije;
	}
	public void setOrganizacije(HashMap<String, Organizacija> organizacije) {
		this.organizacije = organizacije;
	}
	public HashMap<String, Virtualna_masina> getVirtualne_masine() {
		return virtualne_masine;
	}
	public void setVirtualne_masine(HashMap<String, Virtualna_masina> virtualne_masine) {
		this.virtualne_masine = virtualne_masine;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public void UcitavanjeFajlova() throws IOException{
		
		File file = new File("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/users.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st;
		while ((st = br.readLine()) != null){
			String[] pod = st.split(";");
			//String email, String pass, String ime, String prezime, String organizacija,
			//String uloga
			korisnici.put(pod[0], new Korisnik(pod[0],pod[1],pod[2],pod[3],pod[4],pod[5]));
						
		}
		br.close();
		
		file = new File("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/disk.txt"); 
		  
		br = new BufferedReader(new FileReader(file)); 
		
		//String ime, String tip, String ime_vm, int kapacitet
		while ((st = br.readLine()) != null){
		  	String[] pod = st.split(";");
		  	diskovi.put(pod[0], new Disk(pod[0],pod[1],pod[2],Integer.parseInt(pod[3])));
		}
		  
		br.close();
		
		
		file = new File("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/kategorija.txt"); 
		  
		br = new BufferedReader(new FileReader(file)); 
		
		//String ime, int broj_jezgara, int ram, int gpu_jezgra
		while ((st = br.readLine()) != null){
		  	String[] pod = st.split(";");
		  	kategorije.put(pod[0], new Kategorija(pod[0],Integer.parseInt(pod[1]),Integer.parseInt(pod[2]),Integer.parseInt(pod[3])));
		}
		  
		br.close();
		
		
		file = new File("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/organizacija.txt"); 
		  
		br = new BufferedReader(new FileReader(file)); 
		
		//String ime, String opis, String logo, ArrayList<String> korisnici,
		//ArrayList<String> resursi
		while ((st = br.readLine()) != null){
		  	String[] pod = st.split(";");
		  	
		  	if(pod.length==3){
		  		
			  	ArrayList<String> temp = new ArrayList<String>();
			  	
			  	ArrayList<String> temp2 = new ArrayList<String>();
			  	
			  	
			  	organizacije.put(pod[0], new Organizacija(pod[0],pod[1],pod[2],temp,temp2));
			
		  	}else if(pod.length==4){
		  		if(pod[4].contains("@")){
		  			String[] koris = pod[3].split("-");
				  	
				  	ArrayList<String> temp = new ArrayList<String>();
				  	for(int i =0;i<koris.length;i++){
				  		temp.add(koris[i]);
				  	}
				  	
				  	ArrayList<String> temp2 = new ArrayList<String>();
				  	
				  	
				  	
				  	organizacije.put(pod[0], new Organizacija(pod[0],pod[1],pod[2],temp,temp2));
			
		  		}else{
		  		  	
				  	ArrayList<String> temp = new ArrayList<String>();
				  	
				  	String[] resurs = pod[3].split("-");
				  	
				  	ArrayList<String> temp2 = new ArrayList<String>();
				  	for(int i =0;i<resurs.length;i++){
				  		temp2.add(resurs[i]);
				  	}
				  	
				  	
				  	organizacije.put(pod[0], new Organizacija(pod[0],pod[1],pod[2],temp,temp2));
			
		  		}
		  	}else{
		  		String[] koris = pod[3].split("-");
			  	
			  	ArrayList<String> temp = new ArrayList<String>();
			  	for(int i =0;i<koris.length;i++){
			  		temp.add(koris[i]);
			  	}
			  	
			  	String[] resurs = pod[4].split("-");
			  	
			  	ArrayList<String> temp2 = new ArrayList<String>();
			  	for(int i =0;i<resurs.length;i++){
			  		temp2.add(resurs[i]);
			  	}
			  	
			  	
			  	organizacije.put(pod[0], new Organizacija(pod[0],pod[1],pod[2],temp,temp2));
			
		  	}
	  	}
		  
		br.close();
		
		file = new File("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/virtualnamasina.txt"); 
		  
		br = new BufferedReader(new FileReader(file)); 
		
		//String ime, String kategorija, int broj_jezgara, int ram, int gpu_jezgra,
		//ArrayList<String> diskovi
		while ((st = br.readLine()) != null){
		  	String[] pod = st.split(";");
		  	
		  	String[] koris = pod[5].split("-");
		  	
		  	ArrayList<String> temp = new ArrayList<String>();
		  	for(int i =0;i<koris.length;i++){
		  		temp.add(koris[i]);
		  	}
		  	
		  	String[] akti = pod[6].split("-");
		  	
		  	ArrayList<String> temp2 = new ArrayList<String>();
		  	for(int i =0;i<akti.length;i++){
		  		temp2.add(akti[i]);
		  	}
		  	
		  	
		  	virtualne_masine.put(pod[0], new Virtualna_masina(pod[0],pod[1],Integer.parseInt(pod[2]),Integer.parseInt(pod[3]),Integer.parseInt(pod[4]),temp, temp2));
		}
		  
		br.close();
		
		
		file = new File("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/aktivnost.txt"); 
		  
		br = new BufferedReader(new FileReader(file)); 
		
		//String id,string date,string status
		while ((st = br.readLine()) != null){
		  	String[] pod = st.split(";");
		  	aktivnosti.put(pod[0], new Aktivnost(pod[0],pod[1],pod[2]));
		}
		  
		br.close();
		
	}
	
	
	public void UpisFajl() throws IOException{
		
		PrintWriter pw = new PrintWriter(new FileWriter("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/users.txt"));	
		//String user, String pass, String email, String ime, String prezime, String organizacija,
		//String uloga
		
		for(Korisnik kr : korisnici.values()){
			pw.write(kr.getEmail()+";"+kr.getPass()+";"+kr.getIme()+";"+kr.getPrezime()+";"+kr.getOrganizacija()+";"+kr.getUloga());
			pw.write("\n");
		}
		
		pw.close();
		
		
		pw = new PrintWriter(new FileWriter("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/disk.txt"));	
		//String ime, String tip, String ime_vm, int kapacitet
		
		for(Disk dk : diskovi.values()){
			pw.write(dk.getIme()+";"+dk.getTip()+";"+dk.getIme_vm()+";"+dk.getKapacitet());
			pw.write("\n");
		}
		
		pw.close();
		
		
		
		
		pw = new PrintWriter(new FileWriter("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/kategorija.txt"));	
		//String ime, int broj_jezgara, int ram, int gpu_jezgra
		
		for(Kategorija kat : kategorije.values()){
			pw.write(kat.getIme()+";"+kat.getBroj_jezgara()+";"+kat.getRam()+";"+kat.getGpu_jezgra());
			pw.write("\n");
		}
		
		pw.close();
		
		
		
		
		pw = new PrintWriter(new FileWriter("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/organizacija.txt"));	
		//String ime, String opis, String logo, ArrayList<String> korisnici,
				//ArrayList<String> resursi
		for(Organizacija or : organizacije.values()){
			String temp = "";
			for(int i=0;i<or.getKorisnici().size();i++){
				if(i<or.getKorisnici().size()-1)
					temp+=or.getKorisnici().get(i)+"-";
				else
					temp+=or.getKorisnici().get(i);
			}
			
			String temp2 = "";
			for(int i=0;i<or.getResursi().size();i++){
				if(i<or.getResursi().size()-1)
					temp2+=or.getResursi().get(i)+"-";
				else
					temp2+=or.getResursi().get(i);
			}
			
			pw.write(or.getIme()+";"+or.getOpis()+";"+or.getLogo()+";"+temp+";"+temp2);
			pw.write("\n");
		}
		
		pw.close();
		
		
		
		
		
		pw = new PrintWriter(new FileWriter("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/virtualnamasina.txt"));

		//String ime, String kategorija, int broj_jezgara, int ram, int gpu_jezgra,
		//ArrayList<String> diskovi
		
		for(Virtualna_masina vm : virtualne_masine.values()){
			
			String temp = "";
			for(int i=0;i<vm.getDiskovi().size();i++){
				if(i<vm.getDiskovi().size()-1)
					temp+=vm.getDiskovi().get(i)+"-";
				else
					temp+=vm.getDiskovi().get(i);
			}
			
			String temp2 = "";
			for(int i=0;i<vm.getAktivnosti().size();i++){
				if(i<vm.getAktivnosti().size()-1)
					temp2+=vm.getAktivnosti().get(i)+"-";
				else
					temp2+=vm.getAktivnosti().get(i);
			}
			
			pw.write(vm.getIme()+";"+vm.getKategorija()+";"+vm.getBroj_jezgara()+";"+vm.getRam()+";"+vm.getGpu_jezgra()+";"+temp+";"+temp2);
			pw.write("\n");
		}
		
		pw.close();
		
		

		pw = new PrintWriter(new FileWriter("C:/Users/Dz0n1Pr0/Desktop/Web-projekat/ProjekatWebVM/aktivnost.txt"));	
		//String ime, String tip, String ime_vm, int kapacitet
		
		for(Aktivnost ak : aktivnosti.values()){
			pw.write(ak.getId()+";"+ak.getDate()+";"+ak.getStatus());
			pw.write("\n");
		}
		
		pw.close();
		
		
	}
}
