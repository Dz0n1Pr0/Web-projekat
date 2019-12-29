package Servleti.Super;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Kategorija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class ObrisiKat
 */
@WebServlet("/ObrisiKat")
public class ObrisiKat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrisiKat() {
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
		boolean brisi = true;
		for(Virtualna_masina vm : k.virtualne_masine.values()){
			if(vm.getKategorija().equals(request.getParameter("ime")))
				brisi = false;
		}
		
		if(brisi){
			k.kategorije.remove(request.getParameter("ime"));
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
				out.println("			<th>Broj jezgara</th>");
				out.println("			<th>RAM</th>");
				out.println("			<th>GPU jezgra</th>");
				out.println("		</tr>");
									for(Kategorija kat : k.kategorije.values()){
										
				out.println("		<tr>");
				out.println("			<td><a href=PrikazKatDetalji?ime=" +kat.getIme()+">" + kat.getIme() + "</a></td>");
				out.println("			<td>" + kat.getBroj_jezgara() +  " </td>");
				out.println("			<td><img src=\""+kat.getRam()+"\"/></td>");
				out.println("			<td><img src=\""+kat.getGpu_jezgra()+"\"/></td>");
				out.println("		</tr>");
									}
				out.println("	</table>");
				
				out.println("	<a href=\"DodajKat\">Dodaj Kategoriju</a>");
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
			out.println("	<form action=PrikazKatDetaljiKraj>");
			out.println(" 		<input type=\"hidden\" name=\"imeStaro\" value=\""+k.kategorije.get(request.getParameter("ime")).getIme()+"\"");
			out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" value=\""+k.kategorije.get(request.getParameter("ime")).getIme()+"\"/>");
			out.println("		<p>Broj jezgara: </p><input type=\"text\" name=\"brjezgra\" value=\""+k.kategorije.get(request.getParameter("ime")).getBroj_jezgara()+"\"/ disabled>");
			out.println("		<p>RAM: </p><input type=\"text\" name=\"ram\" value=\""+k.kategorije.get(request.getParameter("ime")).getRam()+"\"/ disabled>");
			out.println("		<p>GPU: </p><input type=\"text\" name=\"gpu\" value=\""+k.kategorije.get(request.getParameter("ime")).getGpu_jezgra()+"\"/ disabled>");
			out.println("		<br>");
			out.println("		<input type=\"submit\" value=\"submit\" />");
			out.println("	</form>");

			out.println("<a href=ObrisiKat?ime="+request.getParameter("ime")+">Obrisi Kategoriju</a>");
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