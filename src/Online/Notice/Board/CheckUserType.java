
package Online.Notice.Board;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class CheckUserType extends HttpServlet {
    String uname,psw,userType;
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //set the general settings
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
        //geting input from html file
        try
        {
                uname=request.getParameter("uname");
		psw=request.getParameter("psw");
        }
         catch(NullPointerException npe)
         {
         pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('please login first');");
		   pw.println("location='index.html';");
		   pw.println("</script>");
         }
                userType=request.getParameter("usertype").toUpperCase();
        //setting session value
                        HttpSession session=request.getSession();  
		        session.setAttribute("name",uname); 
		        session.setAttribute("pass",psw); 
        //check user type
                        if(userType.equals("STUDENT"))
                        {
                        response.sendRedirect("StudentLogin");
                        }
                        else if(userType.equals("STAFF"))
                        {
                         response.sendRedirect("StaffLogin");   
                        }
                
  
   
}
}
