package Servleti.Admin.View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DodajKorAdminKrajGreska
 */
@WebServlet("/DodajKorAdminKrajGreska")
public class DodajKorAdminKrajGreska extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajKorAdminKrajGreska() {
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
		
				Boolean emailBul = (Boolean) request.getAttribute("emailBul");
				Boolean imeBul = (Boolean) request.getAttribute("imeBul");
				Boolean prezimeBul = (Boolean) request.getAttribute("prezimeBul");
				Boolean passBul = (Boolean) request.getAttribute("passBul");
				
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
				out.println("	<form action=DodajKorAdminKraj>");
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
				out.println("		<p>Tip korisnika:</p><select name=\"uloga\">");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
