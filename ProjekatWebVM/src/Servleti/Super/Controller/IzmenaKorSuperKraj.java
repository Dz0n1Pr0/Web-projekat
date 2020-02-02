package Servleti.Super.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IzmenaKorSuperKraj
 */
@WebServlet("/IzmenaKorSuperKraj")
public class IzmenaKorSuperKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmenaKorSuperKraj() {
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
		Boolean passed = true, emailBul = true, passBul = true;
		
		if(!request.getParameter("email").equals(request.getParameter("emailStaro")))
			if(k.korisnici.containsKey(request.getParameter("email")))
				passed = false;
		
		if(!isValid(request.getParameter("email"))){
			passed = false;
			emailBul = false;
		}
		
		if(!request.getParameter("pass").equals(request.getParameter("passPonovo"))){
			passed = false;
			passBul = false;
		}
		
		if(passed){
			
			String stari = k.korisnik.getEmail();
			k.korisnik.setIme(request.getParameter("ime"));
			k.korisnik.setPrezime(request.getParameter("prezime"));
			k.korisnik.setEmail(request.getParameter("email"));
			k.korisnik.setPass(request.getParameter("pass"));
			
			k.korisnici.put(stari, k.korisnik);
			
			k.UpisFajl();
			
			response.sendRedirect(k.putanja + "PrikazKorSuper");
			
		
		}else{
			request.setAttribute("emailBul", emailBul);
			request.setAttribute("passBul", passBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("IzmenaKorSuperKrajGreska").forward(request, response);
			
			
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
