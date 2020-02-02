package Servleti.Korisnik;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrikazDiskDetaljiKor
 */
@WebServlet("/PrikazDiskDetaljiKor")
public class PrikazDiskDetaljiKor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazDiskDetaljiKor() {
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
		out.println("<div class=\"ostalo2\">");
		
		out.println("		<p>Ime:"+k.diskovi.get(request.getParameter("ime")).getIme()+"</p>");
		out.println("		<p>Tip:"+k.diskovi.get(request.getParameter("ime")).getTip()+"</p>");
		out.println("		<p>Kapacitet:"+k.diskovi.get(request.getParameter("ime")).getKapacitet()+"</p>");
		out.println("		<p>Virtualna masina:"+k.diskovi.get(request.getParameter("ime")).getIme_vm()+"</p>");
		out.println("		<br>");
		out.println("</div>");
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
