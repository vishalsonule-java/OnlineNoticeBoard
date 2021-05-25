
package Online.Notice.Board;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StudentProfile extends HttpServlet {

 Connection con=null;
 PreparedStatement ps=null;
 HttpSession session=null;
 ResultSet rs=null;
 String uname,pass,name,course;
 InputStream is=null;
OutputStream os=null;
byte[] buffer=null;
int bytesRead=0;
 String query="(select course,full_name from users1 where uname=? and pass=?)";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        con=conn.connection.getConnection();
        session = request.getSession();
        try{
        uname=session.getAttribute("name").toString();
        pass=session.getAttribute("pass").toString();
        }
        catch(NullPointerException npe)
        {
            out.println("<script type=\"text/javascript\">");
		   out.println("alert('please login first');");
		   out.println("location='index.html';");
		   out.println("</script>");
        }
        if(uname!=null && pass!=null)
        {
        try
        {
           //create prepared statement object
            ps=con.prepareStatement(query);
            //set parametet
            ps.setString(1,uname);
            ps.setString(2,pass);
            //execute query
            rs=ps.executeQuery();
            //fetch records form result set
            while(rs.next())
            {
            name=rs.getString(2);
            course=rs.getString(1);
            }
        }//try
        catch(SQLException se)
        {
        se.printStackTrace();
        }
        
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Profile</title>");   
            out.println("<link rel=\"stylesheet\" href=\"css/profile.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"baccolor\">\n" +
"<h1 align=\"center\"><img src=\"images/logo.jpg\" alt=\"Avatar\" class=\"avatar\"></h1>\n" +
 

"	<a href=\"StudentProfile\" ><button class=\"tablink\" id=\"defaultOpen\">Profile</button></a>\n" +
"	<a href=\"view notice.html\" ><button class=\"tablink\" >View Notice</button></a>\n" +
"	<a href=\"StudentUpdateProfile\" ><button class=\"tablink\" >Update profile</button></a>\n" +
"	<a href=\"StudentLogout\" ><button class=\"tablink\" >Logout</button></a>\n" +
"</div>  ");
            out.println("<section");
            
            
            out.println("<div class='card'>");
            
            out.println(" <img src='Image' alt=\"picture\" style=\"width:100%\" class='user-profile'>");
             out.println("<div class='notice-student'>");
             out.println("<div class='meta-info-user'>");
            out.println("<h1>"+name+"</h1>");
            out.println("<p class=\"title\">"+course+"</p>");
            out.println("<p>SRTM University Nanded</p>");
             out.println("</div>");
            out.println("<div style=\"margin: 24px 0;\">");
            out.println("</div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<footer>\n" +
"<div align=\"center\" class=\"fotter-copyright\">&copy;copyright2018</div>\n" +
"<div align=\"center\" class=\"fotter-title\">School of Computational siences SRTMU Nanded</div>\n" +
"</footer>");
            out.println("</body>");
            out.println("</html>");
        }//if
        else
        {
                    out.println("<script type=\"text/javascript\">");
		   out.println("alert('please login first');");
		   out.println("location='login.html';");
		   out.println("</script>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
