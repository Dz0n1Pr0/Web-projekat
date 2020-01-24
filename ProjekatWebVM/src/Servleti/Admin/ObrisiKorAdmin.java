package Servleti.Admin;

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

		k.organizacije.get(k.korisnici.get(request.getParameter("email")).getOrganizacija()).getKorisnici().remove(request.getParameter("email"));
				
		k.korisnici.remove(request.getParameter("email"));
		
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
			out.println("	<p>Organizacija: "+k.korisnik.getOrganizacija()+"</p>");
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
			
			out.println("	<table>");
			
			out.println("		<tr>");
			out.println("			<th>Ime</th>");
			out.println("			<th>Prezime</th>");
			out.println("			<th>Email</th>");
			out.println("			<th>Organizacija</th>");
			out.println("		</tr>");
								for(Korisnik kor : k.korisnici.values()){
									if(kor.getOrganizacija().equals(k.korisnik.getOrganizacija())){
			out.println("		<tr>");
			out.println("			<td><a href=PrikazKorDetaljiSuper?email=" +kor.getEmail()+">" + kor.getIme() + "</a></td>");
			out.println("			<td>" + kor.getPrezime() +  " </td>");
			out.println("			<td>" + kor.getEmail() +  " </td>");
			out.println("			<td>" + kor.getOrganizacija() +  " </td>");
			out.println("		</tr>");
								}}
			out.println("	</table>");
			
			out.println("	<a href=\"DodajKorSuper\">Dodaj Korisnika</a>");
			out.println("</body>");
			out.println("</html>");
			out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
