
package Online.Notice.Board;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)
public class StudentUpdatePhoto extends HttpServlet {
     Connection con=null;
 PreparedStatement ps=null;
 HttpSession session=null;
 ResultSet rs=null;
 InputStream inputstream=null;
 int count=0;
 String query="update USERS1 set image=? where uname=? and pass=?";
 String uname, pass;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        con=conn.connection.getConnection();
        session=request.getSession();
        uname=session.getAttribute("name").toString();
        pass=session.getAttribute("pass").toString();
        //get html value
             Part filePart = request.getPart("fileToUpload");
                  if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputstream = filePart.getInputStream();
        }
                  try
        {
            ps=con.prepareStatement(query);
                  //set query parameters
                   ps.setBlob(1, inputstream);
                   ps.setString(2,uname);
                   ps.setString(3,pass);
                   //execute query
                   count=ps.executeUpdate();
                   if(count>0)
                   {
                           	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('profile photo update scuessfully');");
		   pw.println("location='StudentUpdateProfile';");
		   pw.println("</script>");
                   }
                   else
                   {
                       	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('photo is not update try again');");
		   pw.println("location='StudentUpdateProfile';");
		   pw.println("</script>");
                   }
                   
        
        
        
        
        }//try
            catch(SQLException se)
                    {
                    se.printStackTrace();
                    }
        
        
    }


}
