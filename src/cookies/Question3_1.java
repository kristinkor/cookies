package cookies;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author Kristina Korzhenevskaya
 */

//Make a small page that displays some simple information of your choosing. Make another page
//that lets users choose the foreground and background color that they want the first page to use. For
//example, if users never visit the the color choice page, the main informational page should use
//default colors of your choosing. But if a user visits the color choice page and chooses a yellow
//background and red foreground, all subsequent visits to the main page by that user should result in
//red on yellow. There is no need to vet the colors; you can accept whatever color values the user
//gives you.

@WebServlet("/chooseTheColors")
public class Question3_1 extends HttpServlet {
	 	@Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		Cookie[] cookies = request.getCookies();

		String background = checkCookies(cookies, "background"), foreground = checkCookies(cookies, "foreground");
		if (background==null) {
			background = "f5f5f5";
		}
		if (foreground==null) {
			foreground = "333333";
		}
		
        response.setContentType("text/HTML");
        PrintWriter out = response.getWriter();
        String title = "Choose the Colors";
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
        String theForm = docType + "<HTML>\n" + "<HEAD><meta charset=\"UTF-8\"><TITLE>" + title + "</TITLE></HEAD>\n" + "<BODY style=\"background-color:" + background +";color:" + foreground +";\">\n" + 
        		"<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                "<FORM ACTION=\"\" METHOD=\"post\">\n" + 
                "Backgroung color:" + "<INPUT TYPE=\"TEXT\" NAME=\"background\" VALUE=\"" + background + "\"><BR>\n" +
                "Foreground color:" + "<INPUT TYPE=\"TEXT\" NAME=\"foreground\" VALUE=\"" + foreground + "\"><BR>\n" +
                "<CENTER><INPUT TYPE=\"SUBMIT\" value=\"Submit\"></CENTER>\n" + 
                "</FORM>\n" +
                "</BODY></HTML>";
        out.print(theForm);
	 }
	 
	 private static String checkCookies(Cookie[] cookies, String name) {
		 if(cookies!= null) {
	       for (Cookie c : cookies) {
	    	   if(c.getName().equals(name)) {
	    		   return c.getValue();
	    	   }
	    
	       }
		 }
		return null;
	}
	 
	 @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String background = request.getParameter("background"), foreground = request.getParameter("foreground");
		 if(background!=null) {
			 Cookie c = new Cookie("background", background);
			 c.setMaxAge(60*60*24*365);
			 response.addCookie(c);
		 }
		 if(foreground!=null) {
			 Cookie c = new Cookie("foreground", foreground);
			 c.setMaxAge(60*60*24*365);
			 response.addCookie(c);

		 }
		 
		 response.sendRedirect("http://localhost/cookies/chooseTheColors");
	 }
}
