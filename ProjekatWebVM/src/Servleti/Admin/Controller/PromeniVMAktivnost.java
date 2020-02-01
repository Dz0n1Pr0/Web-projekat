package Servleti.Admin.Controller;

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
		
		if(!k.korisnik.getUloga().equals("admin")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendRedirect(k.putanja + "Login.jsp");
			}else{
		
		Aktivnost ak = new Aktivnost();
		ak.setId(k.aktivnosti.size()+1+"");
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ak.setDate(fmt.format(new Date()));
		
		Aktivnost sporedna = k.aktivnosti.get(k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().get(k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().size()-1));
		if(sporedna.getStatus().equals("ugasena"))
			ak.setStatus("aktivna");
		else
			ak.setStatus("ugasena");
		
		k.virtualne_masine.get(request.getParameter("ime")).getAktivnosti().add(ak.getId());
		k.aktivnosti.put(ak.getId(), ak);
		
		k.UpisFajl();
		response.sendRedirect(k.putanja + "PrikazVMAdmin");
		
		
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
