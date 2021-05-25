
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

public class AdminUpdateNotice extends HttpServlet {

  Connection con=null;
  PreparedStatement ps=null;
  ResultSet rs=null;
  String id=null;
  String query="select nid,title,notice from notice1 where nid=?";
  String title,notice,course,post,comman;
 int year,nid;
 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        response.setContentType("text/html");
        id=request.getParameter("id");
        int id1=Integer.parseInt(id);
        con=conn.connection.getConnection();
        try
        {
        ps=con.prepareStatement(query);
        ps.setInt(1,id1);
        rs=ps.executeQuery();
        if(rs.next())
        {
            nid=rs.getInt(1);
            title=rs.getString(2);
             notice=rs.getString(3);
        pw.println("<html>\n" +
"  <head>\n" +
"  <title>\n" +
"  </title>\n" +
" <script src=\"javascript/adminaddnotice.js\">\n" +
"\n" +
"</script>\n" +
"<link rel=\"stylesheet\" href=\"css/adminaddnotice.css\">\n" +
"  </head>\n" +
"  <body>\n" +
"  <div class=\"container\">\n" +
"  <form action=\"AdminSaveUpdateNotice\" method=\"Post\" >\n" +
"  \n" +
"  <label for=\"title\" class=\"lable5\"><b>give a Title to notice:</b></label><br>\n" +
"  <input type=\"text\" name=\"title\" id=\"title\" class=\"title\"value='"+title+"'><br><br>\n" +
"  \n" +
"  <label for=\"noticebox\"><b>Type notice here</b></label><br>\n" +
"  <textarea rows=\"4\" cols=\"50\" name=\"noticebox\" id=\"notice\" class=\"noticebox1\">\n" +
"\n" +
        notice +
"</textarea>\n" +
"  <br>\n" +
        "<input type=hidden name='id' value='"+nid+"'>"+
"   \n" +
"  \n" +
"  <input type=\"submit\" value=\"save\" class=\"btn\">\n" +
"  </form>\n" +
"  </div>\n" +
"  </body>\n" +
"  </html>");
        }
        }
        catch(SQLException se)
        {
        se.printStackTrace();
        }
        
       
    }

}
