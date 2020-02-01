package Servleti.Super.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Kategorija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class ObrisiKat
 */
@WebServlet("/ObrisiKat")
public class ObrisiKat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrisiKat() {
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
		
		boolean brisi = true;
		for(Virtualna_masina vm : k.virtualne_masine.values()){
			if(vm.getKategorija().equals(request.getParameter("ime")))
				brisi = false;
		}
		
		if(brisi){
			k.kategorije.remove(request.getParameter("ime"));
			k.UpisFajl();
			
			response.sendRedirect(k.putanja + "PrikazKat");
			
			
		}else{

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("PrikazKatDetalji").forward(request, response);
			
			
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
