package Servleti.Super.Controller;

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
 * Servlet implementation class PrikazVMDetaljiSuperKraj
 */
@WebServlet("/PrikazVMDetaljiSuperKraj")
public class PrikazVMDetaljiSuperKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazVMDetaljiSuperKraj() {
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
		
		if(!k.admin.equals("super") || !k.korisnik.getUloga().equals("super")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
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
			
			for(String ak : k.virtualne_masine.get(request.getParameter("imeStaro")).getAktivnosti()){
				k.aktivnosti.get(ak).setDate(request.getParameter(ak+"D"));
				k.aktivnosti.get(ak).setStatus(request.getParameter(ak+"S"));
			}
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
			

			response.sendRedirect(k.putanja + "PrikazVMSuper");
			
	}else{
		request.setAttribute("imeBul", imeBul);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		request.getRequestDispatcher("PrikazVMDetaljiSuperKrajGreska").forward(request, response);
		
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
