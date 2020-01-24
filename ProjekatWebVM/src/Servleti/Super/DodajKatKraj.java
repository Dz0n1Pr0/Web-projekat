package Servleti.Super;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Kategorija;

/**
 * Servlet implementation class DodajKatKraj
 */
@WebServlet("/DodajKatKraj")
public class DodajKatKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajKatKraj() {
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
	Boolean passed = true, imeBul = true, jezgraBul = true, ramBul = true, gpuBul = true;
		
		if(k.diskovi.containsKey(request.getParameter("ime"))){
			passed = false;
			imeBul = false;
		}
		
		if(!request.getParameter("brjezgra").matches("-?\\d+(\\.\\d+)?")){
			passed = false;
			jezgraBul = false;
		}
		
		if(!request.getParameter("ram").matches("-?\\d+(\\.\\d+)?")){
			passed = false;
			ramBul = false;
		}
		
		if(!request.getParameter("gpu").matches("-?\\d+(\\.\\d+)?")){
			passed = false;
			gpuBul = false;
		}
		
		if(passed){
			
			Kategorija kat = new Kategorija();
			kat.setIme(request.getParameter("ime"));
			kat.setGpu_jezgra(Integer.parseInt(request.getParameter("gpu")));
			kat.setBroj_jezgara(Integer.parseInt(request.getParameter("brjezgra")));
			kat.setRam(Integer.parseInt(request.getParameter("ram")));
			
			k.kategorije.put(kat.getIme(), kat);
			
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
				out.println("			<th>Broj jezgara</th>");
				out.println("			<th>RAM</th>");
				out.println("			<th>GPU jezgra</th>");
				out.println("		</tr>");
									for(Kategorija kategorija : k.kategorije.values()){
										
				out.println("		<tr>");
				out.println("			<td><a href=PrikazKatDetalji?ime=" +kategorija.getIme()+">" + kategorija.getIme() + "</a></td>");
				out.println("			<td>" + kategorija.getBroj_jezgara() +  " </td>");
				out.println("			<td><img src=\""+kategorija.getRam()+"\"/></td>");
				out.println("			<td><img src=\""+kategorija.getGpu_jezgra()+"\"/></td>");
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
			out.println("<div class=\"ostalo2\">");
			out.println("	<form action=DodajKatKraj>");
			out.println(" 		<input type=\"hidden\" name=\"imeStaro\"/>");
			out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\"/>");
							if(!imeBul){
							out.println("<p>Ime nije validno</p>");
							}
			out.println("		<p>Broj jezgara: </p><input type=\"text\" name=\"brjezgra\" />");
			if(!jezgraBul){
				out.println("<p>Broj jezgara nije validno</p>");
			}
			out.println("		<p>RAM: </p><input type=\"text\" name=\"ram\" />");
			if(!ramBul)
				{
				out.println("<p>RAM nije validno</p>");
				}
			out.println("		<p>GPU: </p><input type=\"text\" name=\"gpu\" />");
			if(!gpuBul)
			{
				out.println("<p>Gpu nije validno</p>");
			}
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
