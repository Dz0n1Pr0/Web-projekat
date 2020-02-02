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
 * Servlet implementation class PrikazDiskDetaljiSuperKraj
 */
@WebServlet("/PrikazDiskDetaljiSuperKraj")
public class PrikazDiskDetaljiSuperKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazDiskDetaljiSuperKraj() {
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
		
		Boolean passed = true, kapBul = true, imeBul = true;
		
		if(!request.getParameter("imeStaro").equals(request.getParameter("ime"))){
			if(k.diskovi.containsKey(request.getParameter("ime"))){
				passed = false;
				imeBul = false;
		}}
		
		if(request.getParameter("virtual").equals(""))
			passed = false;
	
		if(!request.getParameter("kap").matches("-?\\d+(\\.\\d+)?")){
			kapBul = false;
			passed = false;
		}
		
		
		if(passed){
			Disk disk = new Disk();
			disk.setIme(request.getParameter("ime"));
			disk.setKapacitet(Integer.parseInt(request.getParameter("kap")));
			disk.setTip(request.getParameter("tip"));
			
			for(Organizacija org : k.organizacije.values()){
				if(org.getResursi().contains(request.getParameter("imeStaro"))){
					org.getResursi().remove(request.getParameter("imeStaro"));
					org.getResursi().add(request.getParameter("ime"));
				}
			}
			if(!(request.getParameter("vcheck")+"").equals("null")){
				disk.setIme_vm("");
				for(Virtualna_masina vm : k.virtualne_masine.values()){
					if(vm.getDiskovi().contains(request.getParameter("imeStaro"))){
						vm.getDiskovi().remove(request.getParameter("imeStaro"));
						
					}
				}
			}else{
				disk.setIme_vm(request.getParameter("virtual"));
				for(Virtualna_masina vm : k.virtualne_masine.values()){
					if(vm.getDiskovi().contains(request.getParameter("imeStaro"))){
						vm.getDiskovi().remove(request.getParameter("imeStaro"));
						
					}
				}
				k.virtualne_masine.get(request.getParameter("virtual")).getDiskovi().add(disk.getIme());
				
			}
			
			
			k.diskovi.remove(request.getParameter("imeStaro"));
			k.diskovi.put(request.getParameter("ime"), disk);
			
			k.UpisFajl();
			

			response.sendRedirect(k.putanja + "PrikazDiskSuper");
			
			
		}else{

			request.setAttribute("imeBul", imeBul);
			request.setAttribute("kapBul", kapBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("DodajKatKrajGreska").forward(request, response);
			
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
