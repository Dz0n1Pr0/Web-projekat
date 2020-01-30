package Servleti.Super;

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

		if(request.getParameter("ime").equals("")){
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

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			
		    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		    out.println("<html>");
		  	out.println("<head>");
		  	out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
			out.println("<title>Insert title here</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"center\">");
			out.println("<div class=\"glava\">");
			out.println("	<p>Ime: "+k.korisnik.getIme()+"</p>");
			out.println("	<p>Prezime: "+k.korisnik.getPrezime()+"</p>");
			out.println("	<p>Email: "+k.korisnik.getEmail()+"</p>");
			out.println("	<br>");
			out.println("</div>");
			out.println("<div class=\"linkoviA\">");
			out.println("	<a href=PrikazOrg>Prikazi organizacije</a>");
			out.println("	<a href=PrikazKorSuper>Prikazi korisnike</a>");
			out.println("	<a href=PrikazVMSuper>Prikazi VM</a>");
			out.println("	<a href=PrikazDiskSuper>Prikazi Diskove</a>");
			out.println("	<a href=PrikazKat>Prikazi Kategorije</a>");

			out.println("	<a href=Logout>Log out</a>");
			out.println("</div>");
			out.println("<div class=\"ostalo2\">");
			out.println("	<form action=DodajKorSuperKraj>");
			out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" />");
			if(!imeBul){
				out.println("<p>Unesite validno ime</p>");
								}
			out.println("		<p>Prezime: </p><input type=\"text\" name=\"prezime\" />");
			if(!prezimeBul){
				out.println("<p>Unesite validno prezime</p>");
								}
			out.println("		<p>Email: </p><input type=\"text\" name=\"email\" />");
							if(!emailBul){
			out.println("<p>Unesite validan email</p>");
							}
			out.println("		<p>Pass: </p><input type=\"password\" name=\"pass\" />");
			if(!passBul){
				out.println("<p>Unesite validan password</p>");
								}
			out.println("		<p>	Organizacija:</p><select name=\"org\">");
							for(Organizacija org : k.organizacije.values()){
								if(!k.korisnik.getOrganizacija().equals(org.getIme())){
			out.println("			<option value=\""+org.getIme()+"\">"+org.getIme()+"</option>");
							}}
			out.println("		</select>");
			out.println("<p>Tip korisnika:</p><select name=\"uloga\">");
				out.println("			<option value=\"admin\">Admin</option>");
				out.println("			<option value=\"user\">Korisnik</option>");
				out.println("		</select>");
			out.println("		<br>");
			out.println("		<input type=\"submit\" value=\"submit\" />");
			out.println("	</form>");
			out.println("</div></div>");
			out.println("</body>");
			out.println("</html>");
			out.flush();
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
