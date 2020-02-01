package Servleti.Super.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Korisnik;
import Klase.Organizacija;

/**
 * Servlet implementation class DodajKorSuperKraj
 */
@WebServlet("/DodajKorSuperKraj")
public class DodajKorSuperKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajKorSuperKraj() {
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
		
		Boolean passed = true, emailBul = true, imeBul = true, prezimeBul = true, passBul = true;

		if(request.getParameter("ime").equals("") || request.getParameter("ime").equals("")){
			imeBul = false;
			passed = false;
		}
		
		if(request.getParameter("prezime").equals("")){
			passed = false;
			prezimeBul = false;
		}
		

		if(request.getParameter("pass").equals("")){
			passed = false;
			passBul = false;
		}
		
		if(k.korisnici.containsKey(request.getParameter("email")) || !isValid(request.getParameter("email"))){
			passed = false;
			emailBul = false;
			
		}
		
		
		if(passed){
			Korisnik kor = new Korisnik();
			kor.setEmail(request.getParameter("email"));
			kor.setIme(request.getParameter("ime"));
			kor.setOrganizacija(request.getParameter("org"));
			kor.setPrezime(request.getParameter("prezime"));
			kor.setUloga(request.getParameter("uloga"));
			kor.setPass(request.getParameter("pass"));
			
			k.korisnici.put(kor.getEmail(), kor);
			k.organizacije.get(request.getParameter("org")).getKorisnici().add(kor.getEmail());
			k.UpisFajl();
		
			response.sendRedirect(k.putanja + "PrikazKorSuper");
			
		}else{
			request.setAttribute("imeBul", imeBul);
			request.setAttribute("emailBul", emailBul);
			request.setAttribute("prezimeBul", prezimeBul);
			request.setAttribute("passBul", passBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("DodajKorSuperKrajGreska").forward(request, response);
			
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

	public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 

	
}
