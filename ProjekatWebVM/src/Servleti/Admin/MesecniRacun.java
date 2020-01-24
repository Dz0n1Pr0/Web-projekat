package Servleti.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import Klase.Aktivnost;
import Klase.Disk;
import Klase.Organizacija;
import Klase.Virtualna_masina;

/**
 * Servlet implementation class MesecniRacun
 */
@WebServlet("/MesecniRacun")
public class MesecniRacun extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesecniRacun() {
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
		
		if(!(""+ request.getParameter("datumOd")).equals("null") && !(""+request.getParameter("datumDo")).equals("null")){
			
			ArrayList<Virtualna_masina> temp = new ArrayList<Virtualna_masina>();
			ArrayList<Disk> temp2 = new ArrayList<Disk>();
			ArrayList<Double> tempCena = new ArrayList<Double>();
			ArrayList<Double> temp2Cena = new ArrayList<Double>();
			Double konacno = 0.0;
			
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			try {
				
				Date datumOd = fmt.parse(request.getParameter("datumOd"));
				System.out.println(datumOd + " -- " + request.getParameter("datumOd"));
				Date datumDo = fmt.parse(request.getParameter("datumDo"));
				System.out.println(datumDo + " -- " + request.getParameter("datumDo"));
			
			fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			for(Virtualna_masina vm : k.virtualne_masine.values()){

			if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(vm.getIme())){
				
				for(int i = 1; i<vm.getAktivnosti().size();i+=2){
					
				Date akt = fmt.parse(k.aktivnosti.get(vm.getAktivnosti().get(i)).getDate());
				Date akt2 = datumDo;
				if(i+1<vm.getAktivnosti().size()){
				akt2 = fmt.parse(k.aktivnosti.get(vm.getAktivnosti().get(i+1)).getDate());
				}
				
				if(akt.after(datumOd) && akt.before(datumDo)){
					long diff;
					if(akt2.before(datumDo)){
						diff = (akt2.getTime()-akt.getTime())/(60*60 * 1000);
						
					}else{

						diff = (datumDo.getTime()-akt.getTime())/(60*60 * 1000);
						
					}
					
					temp.add(vm);
					double VM = diff*(25*vm.getBroj_jezgara() + 15*vm.getRam() + 1*vm.getGpu_jezgra());
					tempCena.add(VM);
					konacno += VM;
					
				}
				
				}
				
			}
			}
			
			for(Disk dk : k.diskovi.values()){
				if(k.organizacije.get(k.korisnik.getOrganizacija()).getResursi().contains(dk.getIme())){
					
				long diff = (datumDo.getTime()-datumOd.getTime())/(60*60 * 1000);
				double disk;
				if(dk.getTip().equals("SSD"))
					disk = diff*(0.3*dk.getKapacitet());
			
				else
					disk = diff*(0.1*dk.getKapacitet());
			
				temp2.add(dk);
				temp2Cena.add(disk);
				konacno += disk;
			}
				}
			
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				out.println("			<th>Cena</th>");
				out.println("		</tr>");
				
									for(int i =0; i<temp.size();i++){
				out.println("		<tr>");
				out.println("			<td>"+temp.get(i).getIme()+"</td>");
				out.println("			<td>" + tempCena.get(i) +  " </td>");;
				out.println("		</tr>");
									}
			

									for(int i =0; i<temp2.size();i++){
				out.println("		<tr>");
				out.println("			<td>"+temp2.get(i).getIme()+"</td>");
				out.println("			<td>" + temp2Cena.get(i) +  " </td>");;
				out.println("		</tr>");
									}
									
				out.println("		<tr>");
				out.println("			<td>Konacna cena</td>");
				out.println("			<td>"+konacno+"</td>");
				out.println("		</tr>");
				out.println("	</table>");

				out.println("</div>");
				out.println("<div class=\"ostalo2\">");
				out.println("	<form action=MesecniRacun>");
				out.println("		<p>Datum od: </p><input type=\"text\" name=\"datumOd\" />");
				out.println("		<p>Datum do: </p><input type=\"text\" name=\"datumDo\" />");
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
			out.println("<div class=\"linkoviA\">");
			out.println("	<a href=PrikazOrgDetaljiAdmin>Prikaz organizacije</a>");
			out.println("	<a href=PrikazKorAdmin>Prikaz korisnika organizacije</a>");
			out.println("	<a href=PrikazVMAdmin>Prikaz VM</a>");
			out.println("	<a href=PrikazDiskAdmin>Prikaz diskova</a>");
			out.println("	<a href=MesecniRacun>Mesecni racun</a>");
			out.println("	<a href=Logout>Log out</a>");
			out.println("</div>");
			out.println("<div class=\"ostalo2\">");
			
			out.println("	<form action=MesecniRacun>");
			out.println("		<p>Format datuma je YYYY-MM-DD (2020-01-20)</p>");
			out.println("		<p>Datum od: </p><input type=\"text\" name=\"datumOd\" />");
			out.println("		<p>Datum do: </p><input type=\"text\" name=\"datumDo\" />");
			out.println("		<br>");
			out.println("		<input type=\"submit\" value=\"submit\" />");
			out.println("	</form>");
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
