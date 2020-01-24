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
			  out.print("<html>");
			  out.print("<head>");
			  out.print("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			  out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
			  out.print("<title>Insert title here</title>");
			  out.print("</head>");
			  out.print("<body>");
			  out.print("<div class=\"center\">");
			  out.print("<div class=\"login\">");
			  out.print("<form action=Glavna>");
			  out.print("	<p>Username: </p><input type=\"text\" name=\"email\" />");
			  out.print("	<p>Password: </p><input type=\"text\" name=\"pass\" /><br>");
			  out.print("	<br>");
			  out.print("	<input type=\"submit\" value=\"log in\" />");
			  out.print("</form>");
			  out.print("</div>");
			  out.print("</div>");
			  out.print("</body>");
			  out.print("</html>");
			  out.flush();
		  }else{
			  k.setKorisnik(korisnik);
			  if(korisnik.getUloga().equals("user")){
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
				out.println("	<p>Ime: "+korisnik.getIme()+"</p>");
				out.println("	<p>Prezime: "+korisnik.getPrezime()+"</p>");
				out.println("	<p>Organizacija: "+korisnik.getOrganizacija()+"</p>");
				out.println("	<p>Email: "+korisnik.getEmail()+"</p>");
				out.println("	<br>");
				out.println("</div>");
				out.println("<div class=\"linkovi\">");
				out.println("	<a href=IzmenaKor>Izmeni Profil</a>");
				out.println("	<a href=PrikazVMKorisnik>Prikazi VM</a>");
				out.println("	<a href=PrikazDiskKorisnik>Prikazi Disk</a>");
				out.println("	<a href=Logout>Log out</a>");

				out.println("</div>");
				out.println("<div class=\"ostalo\">");
				out.println("<p>KORISNIK</p>");
				out.println("</div>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
				out.flush();
				
			  }else if(korisnik.getUloga().equals("admin")){
				  
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
					out.println("	<p>Ime: "+korisnik.getIme()+"</p>");
					out.println("	<p>Prezime: "+korisnik.getPrezime()+"</p>");
					out.println("	<p>Organizacija: "+korisnik.getOrganizacija()+"</p>");
					out.println("	<p>Email: "+korisnik.getEmail()+"</p>");
					out.println("	<br>");
					out.println("</div>");
					out.println("<div class=\"linkoviA\">");
					out.println("	<a href=PrikazOrgDetaljiAdmin>Prikaz organizacije</a>");
					out.println("	<a href=PrikazKorAdmin>Prikaz korisnika organizacije</a>");
					out.println("	<a href=PrikazVMAdmin>Prikaz VM</a>");
					out.println("	<a href=PrikazDiskAdmin>Prikaz diskova</a>");
					out.println("	<a href=MesecniRacun>Mesecni racun</a>");
					out.println("	<a href=Logout>Log out</a>");
					out.println("</div>");
					out.println("<div class=\"ostalo\">");
					out.println("<p>ADMIN</p>");
					out.println("</div>");
					out.println("</div>");
					out.println("</body>");
					out.println("</html>");
				  out.flush();
			  }else{

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
					out.println("	<p>Ime: "+korisnik.getIme()+"</p>");
					out.println("	<p>Prezime: "+korisnik.getPrezime()+"</p>");
					out.println("	<p>Email: "+korisnik.getEmail()+"</p>");
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
					out.println("<div class=\"ostalo\">");
					out.println("<p>SUPER ADMIN</p>");
					out.println("</div>");
					out.println("</div>");
					out.println("</body>");
					out.println("</html>");
				  out.flush();
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
