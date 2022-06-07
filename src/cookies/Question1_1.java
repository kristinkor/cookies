package cookies;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author Kristina Korzhenevskaya
 */


//In a previous problem, you had two pages. You used the Referer header to force users to get to page
//2 by following a link from page 1. Now, suppose that it is not necessary to always go to page 1
//before page 2; you just want to make sure that users have been to page 1 at least once before they
//are allowed to visit page 2. For example, page 1 might be a page that introduces the site and gives
//some legal disclaimers, and users are required to visit that page at least once before they can see the
//second page.
//Create two servlets. If a user visits page 2 before having ever visited page 1, they should get redirected to page 1. Note: some people find this problem easier if they manually delete cookies
//between tests. To do this in IE 5/6, start at the Tools menu, then select Internet Options, General,
//and Delete Cookies. With IE 7, start at the Tools menu, then select Internet Options, General, and
//click “Delete” under “Browsing history”. With Firefox, click on Tools, then Options, then Privacy,
//then Cookies—you’ll have various options from there.



@WebServlet("/repeatVisitor1")
public class Question1_1 extends HttpServlet {
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
		 Cookie c = new Cookie("repeatVisiotor", "yes");
		 c.setMaxAge(60*60*24*365);
		 response.addCookie(c);
		 response.sendRedirect("http://localhost/cookies/repeatVisitor2");
	 }
}
