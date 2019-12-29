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
 * Servlet implementation class PrikazDiskDetaljiSuperKraj
 */
@WebServlet("/PrikazDiskDetaljiSuperKraj")
public class PrikazDiskDetaljiSuperKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazDiskDetaljiSuperKraj() {
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
		
		Boolean passed = true, kapBul = true, imeBul = true;
		
		if(!request.getParameter("imeStaro").equals(request.getParameter("ime"))){
			if(k.diskovi.containsKey(request.getParameter("ime"))){
				passed = false;
				imeBul = false;
		}}
		
		if(request.getParameter("virtual").equals(""))
			passed = false;
	
		if(!request.getParameter("kap").matches("-?\\d+(\\.\\d+)?")){
			kapBul = false;
			passed = false;
		}
		
		
		if(passed){
			Disk disk = new Disk();
			disk.setIme(request.getParameter("ime"));
			disk.setKapacitet(Integer.parseInt(request.getParameter("kap")));
			disk.setTip(request.getParameter("tip"));
			
			for(Organizacija org : k.organizacije.values()){
				if(org.getResursi().contains(request.getParameter("imeStaro"))){
					org.getResursi().remove(request.getParameter("imeStaro"));
					org.getResursi().add(request.getParameter("ime"));
				}
			}
			if(!(request.getParameter("vcheck")+"").equals("null")){
				disk.setIme_vm("");
				for(Virtualna_masina vm : k.virtualne_masine.values()){
					if(vm.getDiskovi().contains(request.getParameter("imeStaro"))){
						vm.getDiskovi().remove(request.getParameter("imeStaro"));
						
					}
				}
			}else{
				disk.setIme_vm(request.getParameter("virtual"));
				for(Virtualna_masina vm : k.virtualne_masine.values()){
					if(vm.getDiskovi().contains(request.getParameter("imeStaro"))){
						vm.getDiskovi().remove(request.getParameter("imeStaro"));
						
					}
				}
				k.virtualne_masine.get(request.getParameter("virtual")).getDiskovi().add(disk.getIme());
				
			}
			
			
			k.diskovi.remove(request.getParameter("imeStaro"));
			k.diskovi.put(request.getParameter("ime"), disk);
			
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
			out.println("	<form action=PrikazDiskDetaljiSuperKraj>");
			out.println(" 		<input type=\"hidden\" name=\"imeStaro\" value=\""+k.diskovi.get(request.getParameter("ime")).getIme()+"\"");
			out.println("		<p>Ime: </p><input type=\"text\" name=\"ime\" value=\""+k.diskovi.get(request.getParameter("ime")).getIme()+"\"/>");
			
			if(!imeBul)
				out.println("<p>Unesite validno ime</p>");

			out.println("		<p>Kapacitet: </p><input type=\"text\" name=\"kap\" value=\""+k.diskovi.get(request.getParameter("ime")).getKapacitet()+"\"/>");
			if(!kapBul)		
				out.println("<p>Unesite validni kapacitet</p>");
			
			out.println("		<p>Tip :</p><select name=\"tip\">");
			out.println("			<option value=\"SSD\">SSD</option>");
			out.println("			<option value=\"HDD\">HDD</option>");
			out.println("		</select>");
			out.println("		<br>");
			out.println("		<p>Otkaciti se sa VM: </p><input type=\"checkbox\" name=\"vcheck\" value=\"true\"/>");
							for(Virtualna_masina vm : k.virtualne_masine.values()){
								for(Organizacija org : k.organizacije.values()){
									if(org.getResursi().contains(vm.getIme()) && org.getResursi().contains(request.getParameter("ime"))){
										if(vm.getDiskovi().contains(request.getParameter("ime")))
											out.println("<p>"+vm.getIme()+"</p><input type=\"radio\" name=\"virtual\" value=\""+vm.getIme()+"\" checked/>");
										else
											out.println("<p>"+vm.getIme()+"</p><input type=\"radio\" name=\"virtual\" value=\""+vm.getIme()+"\"/>");
									}
								}
							}
			out.println("		<input type=\"submit\" value=\"submit\" />");
			out.println("	</form>");

			out.println("<a href=ObrisiDiskSuper?ime="+request.getParameter("ime")+">Obrisi Disk</a>");
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