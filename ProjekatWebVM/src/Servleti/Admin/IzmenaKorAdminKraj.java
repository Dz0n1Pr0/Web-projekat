package Servleti.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IzmenaKorAdminKraj
 */
@WebServlet("/IzmenaKorAdminKraj")
public class IzmenaKorAdminKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmenaKorAdminKraj() {
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
		
		Boolean passed = true, emailBul = true, passBul = true;
		
		if(!request.getParameter("email").equals(request.getParameter("emailStaro")))
			if(k.korisnici.containsKey(request.getParameter("email")))
				passed = false;
		
		if(request.getParameter("email").contains("@")){
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
				out.println("	<p>Telefon: "+k.korisnik.getOrganizacija()+"</p>");
				out.println("	<p>Email: "+k.korisnik.getEmail()+"</p>");
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
				out.println("<p>KORISNIK</p>");
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
				out.println("	<p>Ime: "+k.korisnik.getIme()+"</p>");
				out.println("	<p>Prezime: "+k.korisnik.getPrezime()+"</p>");
				out.println("	<p>Telefon: "+k.korisnik.getOrganizacija()+"</p>");
				out.println("	<p>Email: "+k.korisnik.getEmail()+"</p>");
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
				out.println("	<form action=IzmenaKorAdminKraj>");
				out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" value=\""+k.korisnik.getIme()+"\"/>");
				out.println("		<p>Prezime: </p><input type=\"text\" name=\"prezime\" value=\""+k.korisnik.getPrezime()+"\"/>");
				out.println("		<p>Email: </p><input type=\"text\" name=\"email\" value=\""+k.korisnik.getEmail()+"\"/>");
									if(!emailBul)
				out.println("<p>Unesite email ponovo</p>");
				out.println("		<input type=\"hidden\" name=\"emailStaro\" value=\""+k.korisnik.getEmail()+"\"/>");
				out.println("		<p>Password: </p><input type=\"text\" name=\"pass\" value=\""+k.korisnik.getPass()+"\"/>");
				out.println("		<p>Password again: </p><input type=\"text\" name=\"passPonovo\" value=\""+k.korisnik.getPass()+"\"/>");
									if(!passBul)
				out.println("<p>Unesite pass ponovo</p>");
				out.println("		<br>");
				out.println("		<input type=\"submit\" value=\"submit\" />");
				out.println("	</form>");
				out.println("</div></div>");
				out.println("</body>");
				out.println("</html>");
				out.flush();
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
