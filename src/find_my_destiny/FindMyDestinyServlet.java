package find_my_destiny;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name= "FindMyDestinyServlet", urlPatterns = {"/app"})
public class FindMyDestinyServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest Request, HttpServletResponse Response) 
			throws ServletException, IOException
	{
		Response.setContentType("text/html");
		try (PrintWriter Writer = Response.getWriter())
		{
			Writer.write("<html><head><title>Find my Destiny</title></head><body>");
			Writer.write("<h1>This is Find My Destiny. Here, with our help, you can plan the travel of your dreams!</h1>");
			Writer.write("<p>The time now is "+ new Date() + ". Look, it's a great time to plan your travel with Find My Destiny!</p>");
			Writer.write("</body></html>");
			Writer.write("<h1>Hello, " + Request.getParameter("visitor") + "!</h1>");
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest Request, HttpServletResponse Response)
			throws ServletException, IOException
	{
		Response.setContentType("text/html");
		
	}
}
