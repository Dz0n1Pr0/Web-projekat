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
 * Servlet implementation class PrikazOrgDetaljiAdminKraj
 */
@WebServlet("/PrikazOrgDetaljiAdminKraj")
public class PrikazOrgDetaljiAdminKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazOrgDetaljiAdminKraj() {
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
		
		Boolean passed = true, imeBul = true;
		if(!request.getParameter("imeStaro").equals(request.getParameter("ime"))){
		if(k.organizacije.containsKey(request.getParameter("ime"))){
			passed = false;
			imeBul = false;
		}}
		
		if(passed){
			Organizacija org = new Organizacija();
			org.setIme(request.getParameter("ime"));
			org.setOpis(request.getParameter("opis"));
			if(request.getParameter("logo").equals(""))
				org.setLogo("logo.jpg");
			else
				org.setLogo(request.getParameter("logo"));
			org.setKorisnici(k.organizacije.get(request.getParameter("imeStaro")).getKorisnici());
			org.setResursi(k.organizacije.get(request.getParameter("imeStaro")).getResursi());
			
			k.organizacije.remove(request.getParameter("imeStaro"));
			k.organizacije.put(org.getIme(), org);
			
			for(Korisnik kor : k.getKorisnici().values()){
				
				if(kor.getOrganizacija().equals(request.getParameter("imeStaro"))){
					kor.setOrganizacija(org.getIme());
				}
			}
			
			
			k.UpisFajl();
			
			response.sendRedirect(k.putanja + "PrikazOrgDetaljiAdmin");
			
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
							
			out.println("	<form action=PrikazOrgDetaljiAdminKraj>");
			out.println(" 		<input type=\"hidden\" name=\"imeStaro\" value=\""+k.organizacije.get(k.korisnik.getOrganizacija()).getIme()+"\"");
			out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" value=\""+k.organizacije.get(k.korisnik.getOrganizacija()).getIme()+"\"/>");
								if(!imeBul){
									out.println("<p>Unesite validno ime</p>");
								}
			out.println("		<p>Opis: </p><input type=\"text\" name=\"opis\" value=\""+k.organizacije.get(k.korisnik.getOrganizacija()).getOpis()+"\"/>");
			out.println("		<p>Logo: </p><input type=\"file\" name=\"logo\" value=\""+k.organizacije.get(k.korisnik.getOrganizacija()).getLogo()+"\"/>");
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

}
