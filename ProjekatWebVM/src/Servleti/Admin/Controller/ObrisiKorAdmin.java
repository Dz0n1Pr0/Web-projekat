
package Servleti.Admin.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Korisnik;
import Klase.Organizacija;

/**
 * Servlet implementation class ObrisiKorAdmin
 */
@WebServlet("/ObrisiKorAdmin")
public class ObrisiKorAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrisiKorAdmin() {
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
		
		k.organizacije.get(k.korisnici.get(request.getParameter("email")).getOrganizacija()).getKorisnici().remove(request.getParameter("email"));
				
		k.korisnici.remove(request.getParameter("email"));
		
		k.UpisFajl();
		response.sendRedirect(k.putanja + "PrikazKorAdmin");
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
