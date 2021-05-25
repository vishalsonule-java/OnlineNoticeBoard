
package notice.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class AdminDeleteNotice extends HttpServlet {
      Connection con=null;
  PreparedStatement ps=null;
  ResultSet rs=null;
  int id1,count=0;
  String id;
  String query="delete from notice1 where nid=?";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        id=request.getParameter("id");
        int id1=Integer.parseInt(id);
        con=conn.connection.getConnection();
        try
        {
            ps=con.prepareStatement(query);
            //set query parameter
            ps.setInt(1, id1);
            count=ps.executeUpdate();
            
            
        }//try
        catch(SQLException se)
        {
        se.printStackTrace();
        }
        if(count>0)
        {
        	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('notice is deleted sucessfully...');");
		   pw.println("</script>");
        }
        else
        {
            	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('please try agian');");
		   pw.println("</script>");
        
        }
        
    }

   
}
