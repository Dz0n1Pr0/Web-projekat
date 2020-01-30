package Servleti.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Organizacija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class DodajDiskAdmin
 */
@WebServlet("/DodajDiskAdmin")
public class DodajDiskAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajDiskAdmin() {
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
		
		if(!k.korisnik.getUloga().equals("admin")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendRedirect(k.putanja + "Login.jsp");
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
		out.println("<div class=\"ostalo2\">");
		out.println("	<form action=DodajDiskAdminKraj>");
		out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" />");
		out.println("		<p>Kapacitet: </p><input type=\"text\" name=\"kap\" />");
		out.println("		<p>Tip :</p><select name=\"tip\">");
		out.println("			<option value=\"SSD\">SSD</option>");
		out.println("			<option value=\"HDD\">HDD</option>");
		out.println("		</select>");
		out.println("		<br/>");
						for(Virtualna_masina vm : k.virtualne_masine.values()){
								if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(vm.getIme())){
								
									out.println("<p>"+vm.getIme()+"</p><input type=\"radio\" name=\"virtual\" value=\""+vm.getIme()+"\"/>");
								}
							
						}
		out.println("	<br/><input type=\"submit\" value=\"submit\" />");
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
