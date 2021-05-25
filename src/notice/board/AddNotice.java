
package notice.board;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddNotice extends HttpServlet {
    Connection con;
    PreparedStatement ps=null,ps1=null;
    int id,count,year,rand_int1;
    String stringid,filepath,newname,oldname;
    String query="insert into notice1 values(notice_id.nextval,?,?,?,?)";
    String title,notice,coures,tyear;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        
        con=conn.connection.getConnection();
        //geting value form html file
        title=request.getParameter("title");
        notice=request.getParameter("noticebox");
        coures=request.getParameter("course");
        tyear=request.getParameter("year");
       pw.println(title);
         pw.println(notice);
         pw.println(coures);
         pw.println(tyear);
        
        try
        {
            
      ps=con.prepareStatement(query);
      //set query parameters
      ps.setString(1, title);
      ps.setString(2, notice);
      ps.setString(3, coures);
      ps.setString(4, tyear);
      //execute query
      
      count=ps.executeUpdate();
      pw.println(count);
       }
        catch(SQLException se)
        {
        se.printStackTrace();
        }
      if(count>0)
      {
          pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('notice is added sucessfully...');");
		   pw.println("location='AddNotice.html';");
		   pw.println("</script>");
      }
      else
      {
      pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('notice is not added please try again.');");
		   pw.println("location='AddNotice.html';");
		   pw.println("</script>");
      }
      
     
       
        
    }

  
    

}
