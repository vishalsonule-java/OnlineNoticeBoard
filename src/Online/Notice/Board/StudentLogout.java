
package Online.Notice.Board;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StudentLogout extends HttpServlet {

  
    

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html");  
            PrintWriter pw=response.getWriter();  
                HttpSession session=request.getSession();  
            session.invalidate();  
              
            pw.println("<script type=\"text/javascript\">");
	   pw.println("alert('your are logout successfully..');");
	   pw.println("location='index.html';");
	   pw.println("</script>");
               
        
    }

    

}
