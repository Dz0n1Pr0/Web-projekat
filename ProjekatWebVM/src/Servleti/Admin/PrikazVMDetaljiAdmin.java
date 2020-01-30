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
 * Servlet implementation class PrikazVMDetaljiAdmin
 */
@WebServlet("/PrikazVMDetaljiAdmin")
public class PrikazVMDetaljiAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazVMDetaljiAdmin() {
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
		out.println("<div class=\"ostalo\">");
		out.println("	<form action=PrikazVMDetaljiAdminKraj>");
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
		out.println(" 		<input type=\"text\" name=\""+ak+"D\" value=\""+k.aktivnosti.get(ak).getDate()+"\" disabled/>");
		out.println(" 		<input type=\"text\" name=\""+ak+"S\" value=\""+k.aktivnosti.get(ak).getStatus()+"\" disabled/>");
		out.println("<br/>");
						}
		out.println("<br/>Diskovi<br/>");
						for(Disk dk : k.diskovi.values()){
								if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(dk.getIme()) && dk.getIme_vm().equals("") &&
										k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(request.getParameter("ime"))){
									
		out.println("<input type=\"checkbox\" name=\""+dk.getIme()+"\" value=\""+dk.getIme()+"\">"+dk.getIme()+"<br>");	
							}else if(dk.getIme_vm().equals(request.getParameter("ime")) && k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(dk.getIme()) && 
									k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(request.getParameter("ime"))){
		out.println("<input type=\"checkbox\" name=\""+dk.getIme()+"\" value=\""+dk.getIme()+"\" checked>"+dk.getIme()+"<br>");	
														
							}}
		out.println("		<input type=\"submit\" value=\"submit\" />");
		out.println("	</form>");

		out.println("<a href=ObrisiVMAdmin?ime="+request.getParameter("ime")+">Obrisi VM</a>");
		if(k.aktivnosti.get(k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().get(k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().size()-1)).getStatus().equals("ugasena")){
			out.println("<a href=PromeniVMAktivnost?ime="+request.getParameter("ime")+">Aktiviraj</a>");				
		}else{
			out.println("<a href=PromeniVMAktivnost?ime="+request.getParameter("ime")+">Deaktiviraj</a>");
		}
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
