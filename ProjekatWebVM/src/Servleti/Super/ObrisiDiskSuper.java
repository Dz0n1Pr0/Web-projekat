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
 * Servlet implementation class ObrisiDiskSuper
 */
@WebServlet("/ObrisiDiskSuper")
public class ObrisiDiskSuper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrisiDiskSuper() {
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
		
		k.diskovi.remove(request.getParameter("ime"));
		
		for(Organizacija org : k.organizacije.values()){
			if(org.getResursi().contains(request.getParameter("ime")))
				org.getResursi().remove(request.getParameter("ime"));
		}
		
		for(Virtualna_masina virt : k.virtualne_masine.values()){
			if(virt.getDiskovi().contains(request.getParameter("ime")))
				virt.getDiskovi().remove(request.getParameter("ime"));
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
		out.println("	<p>Email: "+k.korisnik.getEmail()+"</p>");
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
		
		out.println("	<table>");
		
		out.println("		<tr>");
		out.println("			<th>Ime</th>");
		out.println("			<th>Kapacitet</th>");
		out.println("			<th>Tip</th>");
		out.println("		</tr>");
							for(Disk dk : k.diskovi.values()){
								
		out.println("		<tr>");
		out.println("			<td><a href=PrikazDiskDetaljiSuper?ime=" +dk.getIme()+">" + dk.getIme() + "</a></td>");
		out.println("			<td>" + dk.getKapacitet() +  " </td>");
		out.println("			<td>" + dk.getTip() +  "</td>");
		out.println("		</tr>");
							}
		out.println("	</table>");

		out.println("</div>");
		out.println("<div class=\"ostalo2\">");
		out.println("	<form action=PrikazDiskSuper>");
		out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" />");
		out.println("		<p>Kapacitet: </p><input type=\"text\" name=\"kapacitetOd\" /> - <input type=\"text\" name=\"kapacitetDo\" />");
		out.println("		<p>Po cemu filtrirati:</p><select name=\"tip\">");
		out.println("			<option value=\"SSD\">SSD</option>");
		out.println("			<option value=\"HDD\">HDD</option>");
		out.println("		</select>");
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
