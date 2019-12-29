package Servleti.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Aktivnost;
import Klase.Organizacija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class PromeniVMAktivnost
 */
@WebServlet("/PromeniVMAktivnost")
public class PromeniVMAktivnost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromeniVMAktivnost() {
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
		
		Aktivnost ak = new Aktivnost();
		ak.setId(k.aktivnosti.size()+1+"");
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ak.setDate(fmt.format(new Date()));
		
		Aktivnost sporedna = k.aktivnosti.get(k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().get(k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().size()-1));
		if(sporedna.getStatus().equals("ugasena"))
			ak.setStatus("upaljena");
		else
			ak.setStatus("ugasena");
		
		k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().add(ak.getId());
		k.aktivnosti.put(ak.getId(), ak);
		

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
		
		out.println("	<table>");
		
		out.println("		<tr>");
		out.println("			<th>Ime</th>");
		out.println("			<th>Broj jezgara</th>");
		out.println("			<th>RAM</th>");
		out.println("			<th>GPU jezgra</th>");
		out.println("		</tr>");

					for(Virtualna_masina vm : k.virtualne_masine.values()){
						for(Organizacija org : k.organizacije.values()){
							if(org.getResursi().contains(vm.getIme()) && org.getIme().equals(k.korisnik.getOrganizacija())){
		out.println("		<tr>");
		out.println("			<td><a href=PrikazVMDetaljiAdmin?ime=" +vm.getIme()+">" + vm.getIme() + "</a></td>");
		out.println("			<td>" + vm.getBroj_jezgara() +  " </td>");
		out.println("			<td>" + vm.getRam() +  "</td>");
		out.println("			<td>" + vm.getGpu_jezgra() +  "</td>");
		out.println("		</tr>");
					}}}
		out.println("	</table>");
							
		out.println("	<a href=\"DodajVMAdmin\">Dodaj virtualnu masinu</a>");

		out.println("</div>");
		out.println("<div class=\"ostalo2\">");
		out.println("	<form action=PrikazVMAdmin>");
		out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" />");
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
