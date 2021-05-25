
package Online.Notice.Board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StaffImage extends HttpServlet
{
     
 Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
        HttpSession session;
        String uname,pass;
        String query="select image from staff1 where uname=? and pass=?";
        ServletOutputStream sos=null;
        byte[] img=null;
 @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
         response.setContentType("text/html");
                   session = request.getSession();
        uname=session.getAttribute("name").toString();
        pass=session.getAttribute("pass").toString();
        con=conn.connection.getConnection();
        try
        {
		//create prepare statement
		ps=con.prepareStatement(query);

		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        try
        {
            //set parametet
            ps.setString(1,uname);
            ps.setString(2,pass);
            //execute query
            rs=ps.executeQuery();
            //fetch records form result set
            while(rs.next())
            {
           img=rs.getBytes(1);
            }
           sos=response.getOutputStream();
           sos.write(img);
            
        }//try
        catch(SQLException se)
        {
        se.printStackTrace();
        }
       
    }
   
}
