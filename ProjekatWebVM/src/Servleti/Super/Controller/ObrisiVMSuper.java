package Servleti.Super.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Disk;
import Klase.Organizacija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class ObrisiVMSuper
 */
@WebServlet("/ObrisiVMSuper")
public class ObrisiVMSuper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrisiVMSuper() {
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
		
		if(!k.korisnik.getUloga().equals("super")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendRedirect(k.putanja + "Login.jsp");
			}else{
		
				
		for(Virtualna_masina vm : k.virtualne_masine.values()){
			if(vm.getIme().equals(request.getParameter("ime")))
			for(String akt : vm.getAktivnosti()){
				k.aktivnosti.remove(akt);
			}
		}
		
		k.virtualne_masine.remove(request.getParameter("ime"));
		
		for(Organizacija org : k.organizacije.values()){
			if(org.getResursi().contains(request.getParameter("ime")))
				org.getResursi().remove(request.getParameter("ime"));
		}
		
		for(Disk disk : k.diskovi.values()){
			if(disk.getIme_vm().equals(request.getParameter("ime")))
				disk.setIme_vm("");
		}
		
		k.UpisFajl();

		response.sendRedirect(k.putanja + "PrikazVMSuper");
			
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
