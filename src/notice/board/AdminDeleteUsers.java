
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
import javax.servlet.http.HttpSession;


public class AdminDeleteUsers extends HttpServlet {
     Connection con;
	PreparedStatement ps;
        PrintWriter pw=null;
        String query,name,usertype;
        int id,count=0;
        HttpSession session;
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        pw=response.getWriter();
        response.setContentType("text/html");
        //get element form html form
        name=request.getParameter("id");
                 id = Integer.parseInt(name);
                 
                 //geting vlaue form sesstion
                 HttpSession session= request.getSession();
        usertype=session.getAttribute("name").toString();
         if(usertype.equals("Student"))
                {
                query="delete from users1 where sid=?";
                }
                else
                {
                    query="deleter form staff1 where staff_id=?";
                }
                 
        con=conn.connection.getConnection();
        try
        {
            ps=con.prepareStatement(query);
            //set query parameter
            ps.setInt(1, id);
            count=ps.executeUpdate();
            
            
        }//try
        catch(SQLException se)
        {
        se.printStackTrace();
        }
        if(count>0)
        {
        	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('user is deleted sucessfully...');");
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

   
    

