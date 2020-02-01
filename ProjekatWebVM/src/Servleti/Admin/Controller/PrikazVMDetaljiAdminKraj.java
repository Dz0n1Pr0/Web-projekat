package Servleti.Admin.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Disk;
import Klase.Kategorija;
import Klase.Organizacija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class PrikazVMDetaljiAdminKraj
 */
@WebServlet("/PrikazVMDetaljiAdminKraj")
public class PrikazVMDetaljiAdminKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazVMDetaljiAdminKraj() {
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
		
		if(k.virtualne_masine.containsKey(request.getParameter("ime")) && !request.getParameter("ime").equals(request.getParameter("imeStaro"))){
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
			
			virt.setAktivnosti(k.virtualne_masine.get(request.getParameter("imeStaro")).getAktivnosti());
			
			
			for(Organizacija org : k.organizacije.values()){
				if(org.getResursi().contains(request.getParameter("imeStaro"))){
					org.getResursi().remove(request.getParameter("imeStaro"));
					org.getResursi().add(virt.getIme());
				}
			}
			
			ArrayList<String> diskDod = new ArrayList();
			for(Disk dk : k.diskovi.values()){
				if(k.virtualne_masine.get(request.getParameter("imeStaro")).getDiskovi().contains(dk.getIme()))
					dk.setIme_vm("");
				
				if(!(request.getParameter(dk.getIme())+"").equals("null")){
					diskDod.add(dk.getIme());
					dk.setIme_vm(virt.getIme());
				}
			}
			
			virt.setDiskovi(diskDod);
			
			k.virtualne_masine.remove(request.getParameter("imeStaro"));
			k.virtualne_masine.put(virt.getIme(), virt);
			
			k.UpisFajl();
			

			response.sendRedirect(k.putanja + "PrikazVMAdmin");
			
		}else{

			request.setAttribute("imeBul", imeBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("PrikazVMDetaljiAdminKrajGreska").forward(request, response);
			
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