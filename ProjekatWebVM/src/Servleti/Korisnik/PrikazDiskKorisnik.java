package Servleti.Korisnik;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Disk;

/**
 * Servlet implementation class PrikazDiskKorisnik
 */
@WebServlet("/PrikazDiskKorisnik")
public class PrikazDiskKorisnik extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazDiskKorisnik() {
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
		
		if(!k.admin.equals("user") || !k.korisnik.getUloga().equals("user")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			}else{
		
		if(!(""+ request.getParameter("ime")).equals("null")){
			
			ArrayList<Disk> temp = new ArrayList<Disk>();
			for(Disk dk : k.diskovi.values()){
			boolean dodaj = true;	
			
			if(!request.getParameter("kapacitetOd").equals("") && !request.getParameter("kapacitetDo").equals("")){
				
				if(dk.getKapacitet()<Integer.parseInt(request.getParameter("kapacitetOd")) || dk.getKapacitet()>Integer.parseInt(request.getParameter("kapacitetDo"))){
					dodaj = false;
				}
			}
			
			if(request.getParameter("tip").equals("SSD") && !dk.getTip().equals("SSD")){
				dodaj = false;
			}else if(request.getParameter("tip").equals("HDD") && !dk.getTip().equals("HDD")){
				dodaj = false;
			}
			
			if(!dk.getIme().toLowerCase().contains(request.getParameter("ime").toLowerCase()))
				dodaj= false;
			
			if(dodaj)
				temp.add(dk);

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
				out.println("	<p>Organizacija: "+k.korisnik.getOrganizacija()+"</p>");
				out.println("	<p>Email: "+k.korisnik.getEmail()+"</p>");
				out.println("	<br>");
				out.println("</div>");
				out.println("<div class=\"linkovi\">");
				out.println("	<a href=IzmenaKor>Izmeni Profil</a>");
				out.println("	<a href=PrikazVMKorisnik>Prikazi VM</a>");
				out.println("	<a href=PrikazDiskKorisnik>Prikazi Disk</a>");
				out.println("	<a href=Logout>Log out</a>");

				out.println("</div>");
				out.println("<div class=\"ostalo\">");
				
				out.println("	<table>");
				
				out.println("		<tr>");
				out.println("			<th>Ime</th>");
				out.println("			<th>Kapacitet</th>");
				out.println("			<th>Tip</th>");
				out.println("		</tr>");
									for(Disk dk : temp){
										if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(dk.getIme())){
				out.println("		<tr>");
				out.println("			<td><a href=PrikazDiskDetaljiKor?ime=" +dk.getIme()+">" + dk.getIme() + "</a></td>");
				out.println("			<td>" + dk.getKapacitet() +  " </td>");
				out.println("			<td>" + dk.getTip() +  "</td>");
				out.println("		</tr>");
									}}
				out.println("	</table>");

				out.println("</div>");
				out.println("<div class=\"ostalo2\">");
				out.println("	<form action=PrikazDiskKorisnik>");
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
			out.println("<div class=\"linkovi\">");
			out.println("	<a href=IzmenaKor>Izmeni Profil</a>");
			out.println("	<a href=PrikazVMKorisnik>Prikazi VM</a>");
			out.println("	<a href=PrikazDiskKorisnik>Prikazi Disk</a>");
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
									if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(dk.getIme())){
			out.println("		<tr>");
			out.println("			<td><a href=PrikazDiskDetaljiKor?ime=" +dk.getIme()+">" + dk.getIme() + "</a></td>");
			out.println("			<td>" + dk.getKapacitet() +  " </td>");
			out.println("			<td>" + dk.getTip() +  "</td>");
			out.println("		</tr>");
								}}
			out.println("	</table>");

			out.println("</div>");
			out.println("<div class=\"ostalo2\">");
			out.println("	<form action=PrikazDiskKorisnik>");
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
