package Servleti.Super;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Aktivnost;
import Klase.Disk;
import Klase.Kategorija;
import Klase.Organizacija;

/**
 * Servlet implementation class PrikazVMDetaljiSuper
 */
@WebServlet("/PrikazVMDetaljiSuper")
public class PrikazVMDetaljiSuper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazVMDetaljiSuper() {
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
		out.println("<div class=\"linkoviS\">");
		out.println("	<a href=PrikazOrg>Prikazi organizacije</a>");
		out.println("	<a href=PrikazKorSuper>Prikazi korisnike</a>");
		out.println("	<a href=PrikazVMSuper>Prikazi VM</a>");
		out.println("	<a href=PrikazDiskSuper>Prikazi Diskove</a>");
		out.println("	<a href=PrikazKat>Prikazi Kategorije</a>");

		out.println("	<a href=Logout>Log out</a>");
		out.println("</div>");
		out.println("<div class=\"ostalo2\">");
		out.println("	<form action=PrikazVMDetaljiSuperKraj>");
		out.println(" 		<input type=\"hidden\" name=\"imeStaro\" value=\""+k.virtualne_masine.get(request.getParameter("ime")).getIme()+"\"");
		out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" value=\""+k.virtualne_masine.get(request.getParameter("ime")).getIme()+"\"/>");
		out.println("		<p>Broj jezgara: </p><input type=\"text\" value=\""+k.virtualne_masine.get(request.getParameter("ime")).getBroj_jezgara()+"\"/ disabled>");
		out.println("		<p>RAM: </p><input type=\"text\" value=\""+k.virtualne_masine.get(request.getParameter("ime")).getRam()+"\"/ disabled>");
		out.println("		<p>GPU: </p><input type=\"text\" value=\""+k.virtualne_masine.get(request.getParameter("ime")).getGpu_jezgra()+"\"/ disabled>");
		out.println("		<p>Kategorija :</p><select name=\"kat\">");
						for(Kategorija kat : k.kategorije.values()){
		out.println("			<option value=\""+kat.getIme()+"\">"+kat.getIme()+"</option>");
						}
		out.println("		</select>");
		out.println("		<br>");
		out.println(" 		<p>Aktivnosti</p>");
		out.println("		<br>");
						for(String ak : k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti()){
		out.println(" 		<input type=\"text\" name=\""+ak+"D\" value=\""+k.aktivnosti.get(ak).getDate()+"\"/>");
		out.println(" 		<input type=\"text\" name=\""+ak+"S\" value=\""+k.aktivnosti.get(ak).getStatus()+"\"/>");
						}
						for(Disk dk : k.diskovi.values()){
							for(Organizacija org : k.organizacije.values()){
								if(org.getResursi().contains(dk.getIme()) && dk.getIme_vm().equals("") && org.getResursi().contains(request.getParameter("ime"))){
									
		out.println("<p>Disk iz organizacije "+org.getIme()+" </p><input type=\"checkbox\" name=\""+dk.getIme()+"\" value=\""+dk.getIme()+"\">"+dk.getIme()+"<br>");	
							}else if(dk.getIme_vm().equals(request.getParameter("ime")) && org.getResursi().contains(dk.getIme()) && 
									org.getResursi().contains(request.getParameter("ime"))){
		out.println("<p>Disk iz organizacije "+org.getIme()+" </p><input type=\"checkbox\" name=\""+dk.getIme()+"\" value=\""+dk.getIme()+"\" checked>"+dk.getIme()+"<br>");	
														
							}}
						}
		out.println("		<input type=\"submit\" value=\"submit\" />");
		out.println("	</form>");

		out.println("<a href=ObrisiVMSuper?ime="+request.getParameter("ime")+">Obrisi VM</a>");
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
