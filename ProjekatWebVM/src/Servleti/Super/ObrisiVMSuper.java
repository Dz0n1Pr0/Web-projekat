package Servleti.Super;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Disk;
import Klase.Organizacija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class ObrisiVMSuper
 */
@WebServlet("/ObrisiVMSuper")
public class ObrisiVMSuper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrisiVMSuper() {
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
		
		k.virtualne_masine.remove(request.getParameter("ime"));
		
		for(Organizacija org : k.organizacije.values()){
			if(org.getResursi().contains(request.getParameter("ime")))
				org.getResursi().remove(request.getParameter("ime"));
		}
		
		for(Disk disk : k.diskovi.values()){
			if(disk.getIme_vm().equals(request.getParameter("ime")))
				disk.setIme_vm("");
		}

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
							for(Virtualna_masina vm : k.virtualne_masine.values()){
		out.println("		<tr>");
		out.println("			<td><a href=PrikazVMDetaljiSuper?ime=" +vm.getIme()+">" + vm.getIme() + "</a></td>");
		out.println("			<td>" + vm.getBroj_jezgara() +  " </td>");
		out.println("			<td>" + vm.getRam() +  "</td>");
		out.println("			<td>" + vm.getGpu_jezgra() +  "</td>");
		out.println("		</tr>");
							}
		out.println("	</table>");
							

		out.println("	<a href=\"DodajVMSuper\">Dodaj virtualnu masinu</a>");

		out.println("</div>");
		out.println("<div class=\"ostalo2\">");
		out.println("	<form action=PrikazVMSuper>");
		out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" />");
		out.println("		<p>Organizacija: </p><input type=\"text\" name=\"org\" />");
		out.println("		<p>Broj jezgara: </p><input type=\"text\" name=\"jezgraOd\" /> - <input type=\"text\" name=\"jezgraDo\" />");
		out.println("		<p>RAM: </p><input type=\"text\" name=\"ramOd\" /> - <input type=\"text\" name=\"ramDo\" />");
		out.println("		<p>GPU: </p><input type=\"text\" name=\"gpuOd\" /> - <input type=\"text\" name=\"gpuDo\" />");
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
