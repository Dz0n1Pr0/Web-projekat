package Servleti.Super;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Korisnik;

/**
 * Servlet implementation class PrikazKorDetaljiSuperKraj
 */
@WebServlet("/PrikazKorDetaljiSuperKraj")
public class PrikazKorDetaljiSuperKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazKorDetaljiSuperKraj() {
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

		Boolean passed = true;
		
		if(passed){
			
			Korisnik kor = new Korisnik();
			kor.setEmail(request.getParameter("emailStaro"));
			kor.setIme(request.getParameter("ime"));
			kor.setOrganizacija(k.korisnici.get(request.getParameter("emailStaro")).getOrganizacija());
			kor.setPrezime(request.getParameter("prezime"));
			kor.setUloga(request.getParameter("uloga"));
			kor.setPass(request.getParameter("pass"));
			
			k.korisnici.remove(request.getParameter("emailStaro"));
			k.korisnici.put(kor.getEmail(), kor);
			
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
				out.println("<div class=\"linkoviS\">");
				out.println("	<a href=PrikazOrg>Prikazi organizacije</a>");
				out.println("	<a href=PrikazKorSuper>Prikazi korisnike</a>");
				out.println("	<a href=PrikazVMSuper>Prikazi VM</a>");
				out.println("	<a href=PrikazDiskSuper>Prikazi Diskove</a>");
				out.println("	<a href=PrikazKat>Prikazi Kategorije</a>");

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
									for(Korisnik korisnik : k.korisnici.values()){
										
				out.println("		<tr>");
				out.println("			<td><a href=PrikazKorDetaljiSuper?email=" +korisnik.getEmail()+">" + korisnik.getIme() + "</a></td>");
				out.println("			<td>" + korisnik.getPrezime() +  " </td>");
				out.println("			<td>" + korisnik.getEmail() +  " </td>");
				out.println("			<td>" + korisnik.getOrganizacija() +  " </td>");
				out.println("		</tr>");
									}
				out.println("	</table>");
				
				out.println("	<a href=\"DodajKorSuper\">Dodaj Korisnika</a>");
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
				out.println("<div class=\"linkoviS\">");
				out.println("	<a href=PrikazOrg>Prikazi organizacije</a>");
				out.println("	<a href=PrikazKorSuper>Prikazi korisnike</a>");
				out.println("	<a href=PrikazVMSuper>Prikazi VM</a>");
				out.println("	<a href=PrikazDiskSuper>Prikazi Diskove</a>");
				out.println("	<a href=PrikazKat>Prikazi Kategorije</a>");

				out.println("	<a href=Logout>Log out</a>");
				out.println("</div>");
				out.println("<div class=\"ostalo\">");
				out.println("	<form action=PrikazKorDetaljiSuperKraj>");
				out.println(" 		<input type=\"hidden\" name=\"emailStaro\" value=\""+k.korisnici.get(request.getParameter("emailStaro")).getEmail()+"\"");
				out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" value=\""+k.korisnici.get(request.getParameter("emailStaro")).getIme()+"\"/>");
				out.println("		<p>Prezime: </p><input type=\"text\" name=\"prezime\" value=\""+k.korisnici.get(request.getParameter("emailStaro")).getPrezime()+"\"/>");
				out.println("		<p>Prezime: </p><input type=\"text\" name=\"pass\" value=\""+k.korisnici.get(request.getParameter("emailStaro")).getPass()+"\"/>");
				out.println("<p>Tip korisnika:</p><select name=\"uloga\">");
					out.println("			<option value=\"admin\">Admin</option>");
					out.println("			<option value=\"user\">Korisnik</option>");
					out.println("		</select>");
				out.println("		<br>");
				out.println("		<input type=\"submit\" value=\"submit\" />");
				out.println("	</form>");
				out.println("<a href=ObrisiKorSuper?email="+request.getParameter("emailStaro")+">Obrisi korisnika</a>");
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
