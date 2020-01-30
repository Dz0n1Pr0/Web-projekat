package Servleti.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Aktivnost;
import Klase.Disk;
import Klase.Kategorija;
import Klase.Organizacija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class DodajVMAdminKraj
 */
@WebServlet("/DodajVMAdminKraj")
public class DodajVMAdminKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajVMAdminKraj() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding(response.getCharacterEncoding());
		PrintWriter out = response.getWriter();
		Klase.Podaci k = (Klase.Podaci)getServletContext().getAttribute("podaci");
		
		if(!k.korisnik.getUloga().equals("admin")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendRedirect(k.putanja + "Login.jsp");
			}else{
		
		
		Boolean pass = true, imeBul = true;
		
		
		if(k.virtualne_masine.containsKey(request.getParameter("ime")) || request.getParameter("ime").equals("")){
			imeBul = false;
			pass = false;
		}
		
		if(pass){
	
			Virtualna_masina virt = new Virtualna_masina();
			virt.setIme(request.getParameter("ime"));
			
			virt.setBroj_jezgara(k.kategorije.get(request.getParameter("kat")).getBroj_jezgara());
			virt.setRam(k.kategorije.get(request.getParameter("kat")).getRam());
			virt.setGpu_jezgra(k.kategorije.get(request.getParameter("kat")).getGpu_jezgra());
			
			virt.setKategorija(request.getParameter("kat"));
			
			Aktivnost ak = new Aktivnost();
			ak.setId(k.aktivnosti.size()+1+"");
			DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			ak.setDate(fmt.format(new Date()));
			ak.setStatus("ugasena");
			
			k.aktivnosti.put(ak.getId(), ak);
			virt.getAktivnosti().add(ak.getId());
			
			
			for(Organizacija org : k.organizacije.values()){
				if(org.getIme().equals(k.korisnik.getOrganizacija())){
					org.getResursi().add(virt.getIme());
				}
			}
			
			ArrayList<String> diskDod = new ArrayList();
			for(Disk dk : k.diskovi.values()){
				
				if(!(request.getParameter(dk.getIme())+"").equals("null")){
					diskDod.add(dk.getIme());
					dk.setIme_vm(virt.getIme());
				}
			}
			virt.setDiskovi(diskDod);

			k.virtualne_masine.put(virt.getIme(), virt);
			k.UpisFajl();
		

			response.sendRedirect(k.putanja + "PrikazVMAdmin");
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
			out.println("<title>Insert title here</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"center\">");
			out.println("<div class=\"glava\">");
			out.println("	<p>Ime: "+k.korisnik.getIme()+"</p>");
			out.println("	<p>Prezime: "+k.korisnik.getPrezime()+"</p>");
			out.println("	<p>Organizacija: "+k.korisnik.getOrganizacija()+"</p>");
			out.println("	<p>Email: "+k.korisnik.getEmail()+"</p>");
			out.println("	<br>");
			out.println("</div>");
			out.println("<div class=\"linkoviA\">");
			out.println("	<a href=PrikazOrgDetaljiAdmin>Prikaz organizacije</a>");
			out.println("	<a href=PrikazKorAdmin>Prikaz korisnika organizacije</a>");
			out.println("	<a href=PrikazVMAdmin>Prikaz VM</a>");
			out.println("	<a href=PrikazDiskAdmin>Prikaz diskova</a>");
			out.println("	<a href=MesecniRacun>Mesecni racun</a>");
			out.println("	<a href=Logout>Log out</a>");
			out.println("</div>");
			out.println("<div class=\"ostalo2\">");
			out.println("	<form action=DodajVMAdminKraj>");
			out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\"/>");
							if(!imeBul){
				out.println("<p>Unesite validno ime</p>");
							}
			out.println("		<p>Kategorija :</p><select name=\"kat\">");
							for(Kategorija kat : k.kategorije.values()){
			out.println("			<option value=\""+kat.getIme()+"\">"+kat.getIme()+"</option>");
							}
			out.println("		</select>");
			for(Disk dk : k.diskovi.values()){
					if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(dk.getIme()) && dk.getIme_vm().equals("") &&
							k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(request.getParameter("ime"))){
						out.println("<input type=\"checkbox\" name=\""+dk.getIme()+"\" value=\""+dk.getIme()+"\">"+dk.getIme()+"<br>");	
				}
			}
			out.println("		<br>");
			out.println("		<input type=\"submit\" value=\"submit\" />");
			out.println("	</form>");
			out.println("</div></div>");
			out.println("</body>");
			out.println("</html>");
			out.flush();
		}
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
