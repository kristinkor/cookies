package cookies;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author Kristina Korzhenevskaya
 */

@WebServlet("/repeatVisitor2")
public class Question1_2 extends HttpServlet {
 	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		System.out.println("you were here on page 2");
		boolean newbie = true;
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			
			for (int i = 0; i < cookies.length; i++) {
				
				Cookie c = cookies[i];
				if ((c.getName().equals("repeatVisitor")) && 
						(c.getValue().equalsIgnoreCase("yes"))) {
					
					newbie = false;
					break;
				} 
			}
		} 
		
		String title = null;
		if (newbie) {
			
			Cookie returnVisitorCookie =
					new Cookie("repeatVisitor", "yes");
			returnVisitorCookie.setMaxAge(60*60*24*365); 
			response.addCookie(returnVisitorCookie);
			response.sendRedirect("http://localhost/cookies/repeatVisitor1");
			
		} else {
			
			title = "Welcome back to PAGE 2";
			
		} // end if/else
 	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!DOCTYPE HTML PUBLIC\" -//W3C//DTD HTML 4.0" 
		+ "Transitional//EN\">\n";
        String htmlDoc = docType + "<HTML>\n" + "<HEAD><meta charset=\"UTF-8\"><TITLE>" + title + "</TITLE></HEAD>\n" + "<BODY>\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
        		"<p ALIGN=\"CENTER\"> We missed you so much!</p>\n" +
                "</BODY></HTML>";
        
        out.print(htmlDoc);
 	}
}