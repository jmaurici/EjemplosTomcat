package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Click;
import modelo.Contador;

/**
 * Servlet implementation class ListaClicksServlet
 */
@WebServlet("/Clicks")
public class ListaClicksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaClicksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("clicks") == null) {
			sesion.setAttribute("contador", new Contador());
			sesion.setAttribute("clicks", new ArrayList<Click>());
		}

		ArrayList<Click> clicks = (ArrayList<Click>) sesion.getAttribute("clicks");
		Contador contador = (Contador) sesion.getAttribute("contador");
		
		contador.setContador(contador.getContador()+1);

		Click click = new Click();
		click.setContador(new Contador(contador.getContador()));
		click.setFechaHora(new Date());
		clicks.add(click);

		PrintWriter salida = response.getWriter();
		salida.write("<html><body><p> id Sesion = " + sesion.getId());
		for (Click click1 : clicks) {
			salida.write("<p> contador = " + click1.getContador() + "( " + click1.getFechaHora() + " )");
		}
		salida.write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
