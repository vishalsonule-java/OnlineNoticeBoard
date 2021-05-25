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


public class AdminSearchNotice extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String id,date;
	String query ;
        String usertype,name,title;
        int rsult;
        
      
       
	public void init()
	{
		con=conn.connection.getConnection();
		
	}
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
              int temp =0,check=1;
              String AllNotice;
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
        title=request.getParameter("title");
        AllNotice=request.getParameter("check");
        if(AllNotice!=null)
        check=AllNotice.compareTo("all");
        if(check==0)
        {
            query ="select nid,title,n_date from notice1";
        }
        else
        {
            query ="select nid,title,n_date from notice1 where title=?";
        }
           
                try
                {
                    if(check==0)
                    {
                ps=con.prepareStatement(query);
                
                    }
                    else
                    {
                    ps=con.prepareStatement(query);
                    ps.setString(1, title);
                    }
               
                rs=ps.executeQuery();
                while(rs.next())
                {
                    if(check==0)
                    {
                        temp++;
                pw.println("<html><body><form action='AdminUpdateNotice'method='POST' target='iframe2'>");
						pw.println("<table >");
						pw.println("<tr>");
						pw.println("<td>"+rs.getString(2)+"</td>");
                                                pw.println("<td>"+rs.getDate(3)+"</td>");
                                                pw.println("<input type='hidden' name='id' value='"+rs.getInt(1)+"'></td>");
                                                pw.println("<td><input type='submit' value='update'></td>");
                                                 pw.println("<td><input type='submit' value='delete' formaction='AdminDeleteNotice' formmethod='Post' formtarget='iframe2'></td>");
						pw.println("</tr>");
						pw.println("</table>");
                                               
						pw.println("</form></body></html>");
                    }
                    else
                    {
                      if(title!=null)
                       if(rs.getString(2)!=null)
                     rsult=title.compareTo(rs.getString(2));
                    
                     if(rsult==0)
                     { 
                         temp++;
                    pw.println("<html><body><form action='AdminUpdateNotice'method='POST' target='iframe2'>");
						pw.println("<table >");
						pw.println("<tr>");
						pw.println("<td>"+rs.getString(2)+"</td>");
                                                pw.println("<td>"+rs.getDate(3)+"</td>");
                                                pw.println("<input type='hidden' name='id' value='"+rs.getInt(1)+"'></td>");
                                                pw.println("<td><input type='submit' value='update'></td>");
                                                 pw.println("<td><input type='submit' value='delete' formaction='AdminDeleteNotice' formmethod='Post' formtarget='iframe2'></td>");
						pw.println("</tr>");
						pw.println("</table>");
                                               
						pw.println("</form></body></html>");
                     }
                    }
                                                
                }
                pw.println("total notice found:"+temp);
                                              
                }
                
                catch(SQLException se)
                {
                se.printStackTrace();
                }
        }
}