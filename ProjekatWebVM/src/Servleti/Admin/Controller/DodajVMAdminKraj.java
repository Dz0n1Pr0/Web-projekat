package Servleti.Admin.Controller;

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
		
		if(!k.admin.equals("admin") || !k.korisnik.getUloga().equals("admin")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
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
			request.setAttribute("imeBul", imeBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("DodajVMAdminKrajGreska").forward(request, response);

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
