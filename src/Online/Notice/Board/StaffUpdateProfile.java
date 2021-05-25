
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


public class StaffUpdateProfile extends HttpServlet {

  Connection con=null;
 PreparedStatement ps=null;
 HttpSession session=null;
 ResultSet rs=null;
 long mobile;
 String post,pass1;
 int year;
 
 String query="select mobileno,post,pass from staff1 where uname=? and pass=?";

 String uname,pass,name;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         session = request.getSession();
         try{
        uname=session.getAttribute("name").toString();
        pass=session.getAttribute("pass").toString();
         }
        catch(NullPointerException npe)
        {
       out.println("<script type=\"text/javascript\">");
		   out.println("alert('please login first');");
		   out.println("location='StaffLogin.html';");
		   out.println("</script>");
        }
       
        
            con=conn.connection.getConnection();
            try{
            ps=con.prepareStatement(query);
             //set query parameter
            ps.setString(1,uname);
            ps.setString(2, pass);
            //execute query
            rs=ps.executeQuery();
            //process resultset
            
        
            if(rs.next())
            {
            mobile = rs.getLong(1);  
             post= rs.getString(2);
              pass1=rs.getString(3);
            }
               }//try
            catch(SQLException se)
            {
            se.printStackTrace();
            }
            
            out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"\n" +
"<script src='js/regformvalidation.js'>\n" +
"\n" +
"\n" +
"</script>\n" +
"<link rel=\"stylesheet\" href=\"css/edit.css\">\n" +
"</head>\n" +
"\n" +
"<div class=\"baccolor\">\n" +
"<h1 align=\"center\"><img src=\"images/logo.jpg\" alt=\"Avatar\" class=\"avatar\"></h1>\n" +

"	<a href=\"StaffProfile\" ><button class=\"tablink\" id=\"defaultOpen\">Profile</button></a>\n" +
"	<a href=\"staffviewnotice.html\" ><button class=\"tablink\" > View Notice</button></a>\n" +
"	<a href=\"AddNotice.html\" ><button class=\"tablink\" > Add Notice</button></a>\n" +
"	<a href=\"StaffUpdateProfile\" ><button class=\"tablink\" >Update profile</button></a>\n" +
"	<a href=\"StaffLogout\" ><button class=\"tablink\" >Logout</button></a>\n" +
"</div>     \n" +
"\n" +
"<section>\n" +
"<form action=\"StaffUpdatePhoto\" style=\"border:1px solid #ccc\" method=\"post\" enctype=\"multipart/form-data\" onsubmit=\" return validation()\">\n" +
"<fieldset>\n" +
"<legend>\n" +
"update profile photo\n" +
"</legend>\n" +
"<label for=\"fileToUpload\"><b>upload here:</b></label>\n" +
"  <input type=\"file\" name=\"fileToUpload\" required>\n" +
"  <button type=\"submit\" class=\"uploadbtn\">upload</button>\n" +
"	</fieldset>\n" +
"</form>\n" +
"\n" +
"	<form action=\"StaffSaveUpdate\" style=\"border:1px solid #ccc\" method=\"post\" onsubmit=\" return validation()\">\n" +
"<fieldset>\n" +
"<legend>personal information</legend>\n" +
"<label for=\"mobile\"><b>Mobile no</b></label>\n" +
" <input type=\"text\" placeholder=\"Enter Mobile No\" name=\"mobile\" value='"+mobile+"'required>\n" +
"\n" +
"<label for=\"course\"><b>course</b></label>\n" +
" <input type=\"text\" placeholder=\"Enter Course Name\" name=\"course\" value='"+post+"' required>\n" +
"\n" +
"<label for=\"psw\"><b>New Password</b></label>\n" +
" <input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" value='"+pass1+"' required>\n" +
" <label for=\"psw-repeat\"><b>Repeat Password</b></label>\n" +
"    <input type=\"password\" placeholder=\"Repeat Password\" name=\"psw-repeat\" required>\n" +
"\n" +
"<div class=\"clearfix\">\n" +
" <button type=\"button\" class=\"cancelbtn\">Cancel</button>\n" +
" <button type=\"submit\" class=\"signupbtn\">update</button>\n" +
" </div>\n" +
"</div>\n" +
" </fieldset>\n" +
"</form>\n" +
"</section>\n" +
"<footer>\n" +
"<div align=\"center\" class=\"fotter-copyright\">&copy;copyright2018</div>\n" +
"<div align=\"center\" class=\"fotter-title\">School of Computational siences SRTMU Nanded</div>\n" +
"</footer>\n" +
"</html> ");
                    
    }

  
}
