
package Online.Notice.Board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vishal
 */
public class StudentLogin extends HttpServlet {

    	Connection con= null;
	PreparedStatement ps=null;
	ResultSet rs=null;
        HttpSession session;
	public static final String query="SELECT * FROM USERS1 WHERE UNAME=? AND PASS=?";
	public void init()
	{
		con=conn.connection.getConnection();
		try
		{
			
			if(con!=null)
				System.out.println("Connection");
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
	}
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname,psw;
		int count=0;
		Cookie cookie;
		//set the general settings
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		//get values from session
                    session=request.getSession();
                    uname=session.getAttribute("name").toString();
                     psw=session.getAttribute("pass").toString();
               
		//set cookies
		cookie=new Cookie("uname",uname);
		//add cookies
		response.addCookie(cookie);
		try {
			if(ps!=null)
			{
		//set query parameter
		ps.setString(1,uname);
		ps.setString(2, psw);
		}
		//execute the sql query
		rs=ps.executeQuery();
		//process the result set
		if(rs.next())
		{
			 HttpSession session=request.getSession();  
		        session.setAttribute("name",uname); 
		        session.setAttribute("pass",psw); 
			response.sendRedirect("StudentProfile");
			
		}
		else
		{
			pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('User or password incorrect');");
		   pw.println("location='index.html';");
		   pw.println("</script>");
		}
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

  

}
