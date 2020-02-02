package Servleti.Super.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Organizacija;

/**
 * Servlet implementation class DodajOrgKraj
 */
@WebServlet("/DodajOrgKraj")
public class DodajOrgKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajOrgKraj() {
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
		
		Boolean passed = true, imeBul = true;
		if(k.organizacije.containsKey(request.getParameter("ime")) || request.getParameter("ime").equals("")){
			passed = false;
			imeBul = false;
		}
		
		if(passed){
		
			Organizacija org = new Organizacija();
			org.setIme(request.getParameter("ime"));
			org.setOpis(request.getParameter("opis"));
			if(request.getParameter("logo").equals(""))
				org.setLogo("logo.jpg");
			else
				org.setLogo(request.getParameter("logo"));
		
			k.organizacije.put(org.getIme(), org);
			
			k.UpisFajl();
	
			response.sendRedirect(k.putanja + "PrikazOrg");
			
		}else{
			request.setAttribute("imeBul", imeBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("DodajOrgKrajGreska").forward(request, response);
			
			
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
