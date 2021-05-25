
package Online.Notice.Board;

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
import javax.servlet.http.HttpSession;
public class StaffSearchNotice extends HttpServlet {
    Connection con;
	PreparedStatement ps,ps1;
	ResultSet rs,rs1;
        String usertype,uname,pass,course,tyear,sdate;
        String query = "select post from staff1 where uname=? and pass=?";
        String query1="select nid,title,n_date from notice1 where post=? or comman='ALL' or comman='STAFF'";
        int year,rsult;
	public void init()
	{
		con=conn.connection.getConnection();
		
	}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();  
		         
		    int temp=0,check=1;  
                    String AllNotice;
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
               
                 uname=session.getAttribute("name").toString(); 
		 pass= session.getAttribute("pass").toString();
              
                 sdate=request.getParameter("date1");
                 AllNotice=request.getParameter("all");
                  if(AllNotice!=null)
                      check=AllNotice.compareTo("all");
                 try
                 {
                 ps=con.prepareStatement(query);
                 //set query parameters
                 ps.setString(1,uname);
                 ps.setString(2,pass);
                 //execute query
                 rs=ps.executeQuery();
                 //proccess the result set
                 if(rs.next())
                 {
                 course=rs.getString(1);
                 }
                 ps1=con.prepareStatement(query1);
                 ps1.setString(1,course);
                rs1= ps1.executeQuery();
                 while(rs1.next())
                 {
                         if(check==0)
                     {
                              temp++;
                 pw.println("<html><body><form action='ViewNotice'method='POST' target='framesection2'>");
						pw.println("<table >");
						pw.println("<tr>");
						pw.println("<td>"+rs1.getDate(3)+"</td>");
						pw.println("<td>"+rs1.getString(2)+"</td>");
                                                pw.println("<input type='hidden' name='id' value='"+rs1.getInt(1)+"'></td>");
                                               pw.println("<td><input type='submit' value='view'></td>");  
						pw.println("</tr>");
						pw.println("</table>");
						pw.println("</form></body></html>");
                     }
                         else
                         {
                   if(sdate!=null)
                        if(rs1.getString(2)!=null)
                     rsult=sdate.compareTo(rs1.getString(2));
                    
                     if(rsult==0)
                     { 
                         temp++;
                 pw.println("<html><body><form action='ViewNotice'method='POST' target='framesection2'>");
						pw.println("<table >");
						pw.println("<tr>");
						pw.println("<td>"+rs1.getDate(3)+"</td>");
						pw.println("<td>"+rs1.getString(2)+"</td>");
                                                pw.println("<input type='hidden' name='id' value='"+rs1.getInt(1)+"'></td>");
                                                pw.println("<td><input type='submit' value='view'></td>");  
						pw.println("</tr>");
						pw.println("</table>");
						pw.println("</form></body></html>");
                     }
                 }
                 }
                  pw.println("total notice found:"+temp);
                 
                 }//try
                 catch(SQLException se)
                 {
                 se.printStackTrace();
                 }
    
        
    }

    
}
