package Servleti.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Disk;
import Klase.Kategorija;
import Klase.Organizacija;

/**
 * Servlet implementation class DodajVMAdmin
 */
@WebServlet("/DodajVMAdmin")
public class DodajVMAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajVMAdmin() {
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
		out.println("	<form action=DodajVMAdminKraj>");
		out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\"/>");
		
		out.println("		<p>Kategorija :</p><select name=\"kat\">");
						for(Kategorija kat : k.kategorije.values()){
		out.println("			<option value=\""+kat.getIme()+"\">"+kat.getIme()+"</option>");
						}
		out.println("		</select>");
		for(Disk dk : k.diskovi.values()){
				if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(dk.getIme()) && dk.getIme_vm().equals("") &&
						k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(request.getParameter("ime"))){
					out.println("<input type=\"checkbox\" name=\""+dk.getIme()+"\" value=\""+dk.getIme()+"\">"+dk.getIme()+"<br>");	
			}
		}
		out.println("		<br>");
		out.println("		<input type=\"submit\" value=\"submit\" />");
		out.println("	</form>");
		out.println("</div></div>");
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
