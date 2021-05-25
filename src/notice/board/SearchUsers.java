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
import javax.servlet.http.HttpSession;

public class SearchUsers extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String id;
	String query =null;
        String usertype,name;
	public void init()
	{
		con=conn.connection.getConnection();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();  
		         
		      
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String temp;
                usertype=request.getParameter("UserType");
                name=request.getParameter("textbox").toUpperCase();
                
                if(usertype.equals("Student"))
                {
                    session.setAttribute("name",usertype);
                query="select sid,full_name, course,year from users1 where full_name=?";
                }
                else
                {
                    session.setAttribute("name",usertype);
                    query="select staff_id,fname,gender,post from staff1 where fname=?";
                
                }
		
			try
			{
                            if(con!=null)
			ps=con.prepareStatement(query);
                            //set query parameters
                            ps.setString(1,name);
				
				if(ps!=null)
				rs=ps.executeQuery();
				if(rs!=null)
				{
					while(rs.next())
					{
						pw.println("<html><body><form action='AdminUpdateUsers'method='POST' target='iframe2'>");
						pw.println("<table >");
						pw.println("<tr>");
						pw.println("<td>"+rs.getInt(1)+"</td>");
						pw.println("<td>"+rs.getString(2)+"</td>");
                                                pw.println("<td>"+rs.getString(3)+"</td>");
                                                pw.println("<td>"+rs.getString(4)+"</td>");
                                                pw.println("<input type='hidden' name='id' value='"+rs.getInt(1)+"'></td>");
                                                pw.println("<td><input type='submit' value='update'></td>");
                                                 pw.println("<td><input type='submit' value='delete' formaction='AdminDeleteUsers' formmethod='Post' formtarget='iframe2'></td>");
						pw.println("</tr>");
						pw.println("</table>");
						pw.println("</form></body></html>");
					}
					
				}//if
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


