package cookies;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author Kristina Korzhenevskaya
 */

//Write a servlet that displays the values of the firstName, lastName, and emailAddress request
//parameters. But, remember what users told you in the past, and use the old values if the current values are missing. 
//So, if a parameter is missing and the client is a first-time visitor, have the servlet
//list “Unknown” for the missing values. If a parameter is missing and the client is a repeat visitor,
//have the servlet use previously-entered values for the missing values.

@WebServlet("/rememberUserInfo")
public class Question2 extends HttpServlet {
 	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();

		String firstName = checkCookies(cookies, "firstName"), lastName = checkCookies(cookies, "lastName"), emailAddress = checkCookies(cookies, "emailAddress");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Enter your credentials";
		String docType = "<!DOCTYPE HTML PUBLIC\" -//W3C//DTD HTML 4.0" 
		+ "Transitional//EN\">\n";
        String theForm = docType + "<HTML>\n" + "<HEAD><meta charset=\"UTF-8\"><TITLE>" + title + "</TITLE></HEAD>\n" + "<BODY>\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                "<FORM ACTION=\"\" METHOD=\"post\">\n" + 
                "First Name:" + "<INPUT TYPE=\"TEXT\" NAME=\"firstName\" value =\"" + firstName + "\"><BR>\n" +
                "Last name:" + "<INPUT TYPE=\"TEXT\" NAME=\"lastName\" value =\"" + lastName + "\"><BR>\n" +
                "Email Address:" + "<INPUT TYPE=\"TEXT\" NAME=\"emailAddress\" value =\"" + emailAddress + "\"><BR>\n" +
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
		return "Unknown";
	}
	 
	 @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String firstName = request.getParameter("firstName"), lastName = request.getParameter("lastName"), emailAddress = request.getParameter("emailAddress");
		 if(firstName!=null&&!firstName.equals("")) {
			 Cookie c = new Cookie("firstName", firstName);
			 c.setMaxAge(9999999);
			 response.addCookie(c);
		 }
		 if(lastName!=null&&!lastName.equals("")) {
			 Cookie c = new Cookie("lastName", lastName);
			 c.setMaxAge(9999999);
			 response.addCookie(c);

		 }
		 if(emailAddress!=null&&!emailAddress.equals("")) {
			 Cookie c = new Cookie("emailAddress", emailAddress);
			 c.setMaxAge(9999999);
			 response.addCookie(c);

		 }
		 
		 response.sendRedirect("http://localhost/cookies/rememberUserInfo");
	 }
}
