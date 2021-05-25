
package notice.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AdminSaveUpdate extends HttpServlet {
    Connection con;
    PreparedStatement ps;
        PrintWriter pw=null;
        String query,name,usertype;
        int id,count=0;
        HttpSession session;
        String fname,gender,dob,course,tyear,tmobile,uname,pass,post;
        int year;
        long mobileno,ms;
        SimpleDateFormat sdf;
        java.util.Date udob;
        java.sql.Date sdob;
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        con=conn.connection.getConnection();
        pw=response.getWriter();
        response.setContentType("text/html");
        
                 //geting vlaue form sesstion
                 HttpSession session= request.getSession();
        usertype=session.getAttribute("name").toString();
        
        //geting vlaue form html form
         fname=request.getParameter("Fname").toUpperCase();
                 gender=request.getParameter("gender").toUpperCase();
                dob=request.getParameter("dob");
                sdf=new SimpleDateFormat("dd-MM-yyyy");
                course=request.getParameter("course").toUpperCase();
                post=request.getParameter("course").toUpperCase();
		tyear=request.getParameter("course_year");
                if(usertype.equals("Student"))
		year=Integer.parseInt(tyear);
                tmobile=request.getParameter("mobile");
		mobileno=Long.parseLong(tmobile);
		uname=request.getParameter("uname");
		pass=request.getParameter("password");
                name=request.getParameter("id");
                 id = Integer.parseInt(name);
         if(usertype.equals("Student"))
                {
                      
                query="update users1 set full_name=?,gender=?,dob=?,course=?,year=?,mobileno=?,uname=? ,pass=? where sid=?";
               
                }
                else
                {
                    query="update staff1 set fname=?,gender=?,dob=?,post=?,mobileno=?,uname=? ,pass=? where staff_id=?";
                    
                    
                }
          
         try
         {
                  if(sdf!=null)
                    udob=sdf.parse(dob);
                    if(udob!=null)
                       ms=udob.getTime();
                        sdob= new java.sql.Date(ms);
                        ps=con.prepareStatement(query);
                        //set query paramenters
                        if(usertype.equals("Student"))
                        {
                             ps.setString(1, fname);
                             ps.setString(2,gender);
                             ps.setDate(3,sdob);
                             ps.setString(4,course);
                             ps.setInt(5,year);
                             ps.setLong(6,mobileno);
                             ps.setString(7,uname);
                             ps.setString(8,pass);
                             ps.setInt(9,id);
                             pw.println(id);
                            
                        }
                        else
                        {
                             ps.setString(1, fname);
                             ps.setString(2,gender);
                             ps.setDate(3,sdob);
                             ps.setString(4,post);
                             ps.setLong(5,mobileno);
                             ps.setString(6,uname);
                             ps.setString(7,pass);
                             ps.setInt(8,id);
                              
                        }
                        //excecute query
                        if(ps!=null)
                        count=ps.executeUpdate();
                         }
         catch(SQLException se)
         {
         se.printStackTrace();
         }
         catch(Exception e)
         {
         e.printStackTrace();
         }
          
         
                        if(count>0)
                        {
                           	pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('user profile is update');");
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
