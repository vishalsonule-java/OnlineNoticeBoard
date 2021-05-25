
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


public class ViewNotice extends HttpServlet {

    String tid;
    int id;
    String query="select notice from notice1 where nid=?";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        
        tid=request.getParameter("id");
        id=Integer.parseInt(tid);
        con=conn.connection.getConnection();
        try
        {
        ps=con.prepareStatement(query);
        ps.setInt(1, id);
        rs=ps.executeQuery();
        if(rs.next())
        {
        pw.println("<html><head><link rel=\"stylesheet\" href=\"css/noticebox.css\"></head><body><form>");
						
						
           pw.println("<textarea rows=\"4\" cols=\"50\" name=\"noticebox\" id=\"notice\" class=\"noticebox1\">");
           pw.println(rs.getString(1));

                pw.println("</textarea>");
                pw.println("</form></body></html>");
        }
        }
        catch(SQLException se)
        {
        se.printStackTrace();
        }
        
    }

  

}
