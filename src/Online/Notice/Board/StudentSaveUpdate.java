
package Online.Notice.Board;

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

public class StudentSaveUpdate extends HttpServlet {
     Connection con=null;
 PreparedStatement ps=null;
 HttpSession session=null;
int count=0;
 String query="update users1 set mobileno=?,course=?,pass=?,year=? where uname=? and pass=?";
 String uname,pass,name,course,tmobile,repass,tyear,pass1;
 int year;
 long mobileno;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter pw=response.getWriter();
          response.setContentType("text/html"); 
        con=conn.connection.getConnection();
        session=request.getSession();
        uname=session.getAttribute("name").toString();
        pass=session.getAttribute("pass").toString();
        //geting input fomr html file
        course=request.getParameter("course").toUpperCase();
        if(course.equals("MSC.CS"))
        {
        course="MSC(CS)";
        }
        else if(course.equals("MSC.CA"))
        {
        course="MSC(CA)";
        }
        else if(course.equals("MSC.CS"))
        {
        course="MSC(CS)";
        }
		tyear=request.getParameter("course_year");
		year=Integer.parseInt(tyear);
                tmobile=request.getParameter("mobile");
		mobileno=Long.parseLong(tmobile);
		pass1=request.getParameter("psw");
                repass=request.getParameter("psw-repeat");
            con=conn.connection.getConnection();
            try{
            ps=con.prepareStatement(query);
             //set query parameter
            
            ps.setLong(1, mobileno);
            ps.setString(2,course);
            ps.setString(3,pass1);
            ps.setInt(4, year);
            ps.setString(5,uname);
            ps.setString(6, pass);
            //execute query
            if(pass1.equals(repass))
            {
            count=ps.executeUpdate();
            }
            else
            {
                        	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('repassword does not match');");
		   pw.println("location='StudentUpdateProfile';");
		   pw.println("</script>");
            }
            if(count>0)
            {
                          	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('profile update scuessfully');");
		   pw.println("location='StudentUpdateProfile';");
		   pw.println("</script>");
            }
            else
            {
                 	pw.println("<script type=\"text/javascript\">");
		  pw.println("alert('profile is not update try again');");
		  pw.println("location='StudentUpdateProfile';");
		  pw.println("</script>");
            }
            }//try
            catch(SQLException se)
            {
                se.printStackTrace();
            }
    }

    

}
