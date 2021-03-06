package Servleti.Admin.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Korisnik;

/**
 * Servlet implementation class PrikazKorDetaljiAdminKraj
 */
@WebServlet("/PrikazKorDetaljiAdminKraj")
public class PrikazKorDetaljiAdminKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazKorDetaljiAdminKraj() {
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
		

		Boolean imeBul = true, prezBul = true, passBul = true, passed = true;
		
		if(request.getParameter("ime").equals("")){
			imeBul = false;
			passed = false;
		}
		
		if(request.getParameter("prezime").equals("")){
			prezBul = false;
			passed = false;
		}
		
		if(request.getParameter("pass").equals("")){
			passBul = false;
			passed = false;
		}
		
		
		
		if(passed){
			
			Korisnik kor = new Korisnik();
			kor.setEmail(request.getParameter("emailStaro"));
			kor.setIme(request.getParameter("ime"));
			kor.setOrganizacija(k.korisnici.get(request.getParameter("emailStaro")).getOrganizacija());
			kor.setPrezime(request.getParameter("prezime"));
			kor.setUloga(request.getParameter("uloga"));
			kor.setPass(request.getParameter("pass"));
			
			k.korisnici.remove(request.getParameter("emailStaro"));
			k.korisnici.put(kor.getEmail(), kor);
			
			k.UpisFajl();
			
			response.sendRedirect(k.putanja + "PrikazKorAdmin");
			
		}else{

			request.setAttribute("imeBul", imeBul);
			request.setAttribute("prezBul", prezBul);
			request.setAttribute("passBul", passBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("PrikazKorDetaljiAdminKrajGreska").forward(request, response);
			
			
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
