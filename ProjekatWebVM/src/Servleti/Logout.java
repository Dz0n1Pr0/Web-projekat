package Servleti;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
		
		k.setKorisnik(null);
		 out.print("<html>");
		  out.print("<head>");
		  out.print("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		  out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		  out.print("<title>Insert title here</title>");
		  out.print("</head>");
		  out.print("<body>");
		  out.print("<div class=\"center\">");
		  out.print("<div class=\"login\">");
		  out.print("<form action=Glavna>");
		  out.print("	<p>Username: </p><input type=\"text\" name=\"email\" />");
		  out.print("	<p>Password: </p><input type=\"text\" name=\"pass\" /><br>");
		  out.print("	<br>");
		  out.print("	<input type=\"submit\" value=\"log in\" />");
		  out.print("</form>");
		  out.print("</div>");
		  out.print("</div>");
		  out.print("</body>");
		  out.print("</html>");
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
