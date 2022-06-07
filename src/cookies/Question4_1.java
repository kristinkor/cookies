package cookies;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * @author Kristina Korzhenevskaya
 */

//Repeat exercise number one, but with session cookies, not persistent cookies. Visit it twice in IE,
//then click on the IE icon on the desktop to start a new browser. Is this considered the same session
//or a new one? Repeat the process with Firefox. Notice that they work differently.

@WebServlet("/repeat_user_1")
public class Question4_1 extends HttpServlet {
 	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "THIS IS PAGE 1";
		String docType = "<!DOCTYPE HTML PUBLIC\" -//W3C//DTD HTML 4.0" 
		+ "Transitional//EN\">\n";
        String theForm = docType + "<HTML>\n" + "<HEAD><meta charset=\"UTF-8\"><TITLE>" + title + "</TITLE></HEAD>\n" + "<BODY>\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
        		"<p ALIGN=\"CENTER\">you are here because you have never visited this page. Welcome!</p>\n" +
                "<FORM ACTION=\"\" METHOD=\"post\">\n" + 
                "<CENTER><INPUT TYPE=\"SUBMIT\" value=\"GO TO SECOND PAGE\"></CENTER>\n" + 
                "</FORM>\n" +
                "</BODY></HTML>";
        
        out.print(theForm);
 	}
 	
	 
	 @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 Cookie c = new Cookie("repeatVisitor", "yes");
		 response.addCookie(c);
		 response.sendRedirect("http://localhost/cookies/repeat_user_2");
	 }
}
