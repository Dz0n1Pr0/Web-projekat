package Servleti.Super.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Klase.Kategorija;

/**
 * Servlet implementation class PrikazKatDetaljiKraj
 */
@WebServlet("/PrikazKatDetaljiKraj")
public class PrikazKatDetaljiKraj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrikazKatDetaljiKraj() {
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
		
		if(!k.admin.equals("super") || !k.korisnik.getUloga().equals("super")){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			}else{
		
		if(!request.getParameter("imeStaro").equals(request.getParameter("ime"))){
		if(k.diskovi.containsKey(request.getParameter("ime"))){
			passed = false;
			imeBul = false;
		}}
		
		if(!request.getParameter("brjezgra").matches("-?\\d+(\\.\\d+)?") || request.getParameter("brjezgra").contains("-")){
			passed = false;
			jezgraBul = false;
		}
		
		if(!request.getParameter("ram").matches("-?\\d+(\\.\\d+)?") || request.getParameter("ram").contains("-")){
			passed = false;
			ramBul = false;
		}
		
		if(!request.getParameter("gpu").matches("-?\\d+(\\.\\d+)?") || request.getParameter("gpu").contains("-")){
			passed = false;
			gpuBul = false;
		}
		
		if(passed){
			
			Kategorija kat = new Kategorija();
			kat.setIme(request.getParameter("ime"));
			kat.setGpu_jezgra(Integer.parseInt(request.getParameter("gpu")));
			kat.setBroj_jezgara(Integer.parseInt(request.getParameter("brjezgra")));
			kat.setRam(Integer.parseInt(request.getParameter("ram")));
			
			k.kategorije.remove(request.getParameter("imeStaro"));
			k.kategorije.put(kat.getIme(), kat);
			
			k.UpisFajl();
			
		
				response.sendRedirect(k.putanja + "PrikazKat");
				
		}else{
			
			request.setAttribute("imeBul", imeBul);
			request.setAttribute("jezgraBul", jezgraBul);
			request.setAttribute("ramBul", ramBul);
			request.setAttribute("gpuBul", gpuBul);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.getRequestDispatcher("PrikazKatDetaljiKraj").forward(request, response);
				
		
		
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
