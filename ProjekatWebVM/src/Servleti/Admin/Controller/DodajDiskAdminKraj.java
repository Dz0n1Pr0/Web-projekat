package Servleti.Admin.Controller;

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
 * Servlet implementation class DodajDiskAdminKraj
 */
@WebServlet("/DodajDiskAdminKraj")
public class DodajDiskAdminKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajDiskAdminKraj() {
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
		
		
		Boolean passed = true, kapBul = true, imeBul = true, virtBul = true;
		
		if(k.diskovi.containsKey(request.getParameter("ime")) || request.getParameter("ime").equals("")){
			passed = false;
			imeBul = false;
		}
	
		if(("" + request.getParameter("virtual")).equals("null")){
			virtBul = false;
			passed = false;
		}
			
		if(!request.getParameter("kap").matches("-?\\d+(\\.\\d+)?")){
			kapBul = false;
			passed = false;
		}
		
		if(passed){
			Disk disk = new Disk();
			disk.setIme(request.getParameter("ime"));
			disk.setKapacitet(Integer.parseInt(request.getParameter("kap")));
			disk.setTip(request.getParameter("tip"));
			
			k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().add(request.getParameter("ime"));
			
			disk.setIme_vm(request.getParameter("virtual"));
			k.virtualne_masine.get(request.getParameter("virtual")).getDiskovi().add(disk.getIme());
				
			
			k.diskovi.remove(request.getParameter("imeStaro"));
			k.diskovi.put(request.getParameter("ime"), disk);
			
			k.UpisFajl();
			

		response.sendRedirect(k.putanja + "PrikazDiskAdmin");
			
			
		}else{
			request.setAttribute("imeBul", imeBul);
			request.setAttribute("kapBul", kapBul);
			request.setAttribute("virtBul", virtBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("DodajDiskAdminKrajGreska").forward(request, response);

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
