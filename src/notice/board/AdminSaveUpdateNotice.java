
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

public class AdminSaveUpdateNotice extends HttpServlet {
  
      Connection con=null;
  PreparedStatement ps=null;
  ResultSet rs=null;
  String id=null;
  String query="update notice1 set title=?,notice=? where nid=?";
  String title,notice,course,post,comman;
 int year,count;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        id=request.getParameter("id");
        int id1=Integer.parseInt(id);
        title=request.getParameter("title");
        notice=request.getParameter("noticebox");
        con=conn.connection.getConnection();
        try
        {
        ps=con.prepareStatement(query);
        ps.setString(1,title);
        ps.setString(2, notice);
        ps.setInt(3,id1);
        count=ps.executeUpdate();
        }
        catch(SQLException se)
        {
        se.printStackTrace();
        }
        
                        if(count>0)
                        {
                           	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('notise update sucssfully..');");
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
