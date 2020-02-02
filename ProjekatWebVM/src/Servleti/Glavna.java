package Servleti;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Glavna
 */
@WebServlet("/Glavna")
public class Glavna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Glavna() {
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
		Klase.Korisnik korisnik = null;
		try{
			
			boolean nasao=false;
			  if(k.korisnici.containsKey(request.getParameter("email")) && k.korisnici.get(request.getParameter("email")).getPass().equals(request.getParameter("pass"))){
				  korisnik = k.korisnici.get(request.getParameter("email"));
				  nasao=true;
			  }
			  
		  if(!nasao){
			 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			  response.sendRedirect(k.putanja + "Login.jsp");
			 
		  }else{
			  k.setKorisnik(korisnik);
			  if(korisnik.getUloga().equals("user")){
				  k.setAdmin("user");
			response.sendRedirect(k.putanja + "PrikazVMKorisnik");
				
			  }else if(korisnik.getUloga().equals("admin")){
				  k.setAdmin("admin");

					response.sendRedirect(k.putanja + "PrikazVMAdmin");
			  }else{
				  k.setAdmin("super");
				  	
					response.sendRedirect(k.putanja + "PrikazVMSuper");
			  }
		  }
			
			
		}catch (Exception e) {
			// TODO: handle exception
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
