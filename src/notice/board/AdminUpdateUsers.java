
package notice.board;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminUpdateUsers extends HttpServlet {
    Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String query =null;
        String usertype,name;
        int id;
       String fname,gender,post,course,tyear,mobileno,username,pass,repass,sdob;
        java.sql.Date sqdob=null;
        java.util.Date udob=null;
        long mobile;
        int year,tid;
        SimpleDateFormat sdf=null;
	public void init()
	{
		con=conn.connection.getConnection();
		
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sdf=new SimpleDateFormat("mm/dd/yyyy");
       HttpSession session= request.getSession();
        usertype=session.getAttribute("name").toString();
        response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String temp;
                usertype=session.getAttribute("name").toString();
                
                name=request.getParameter("id");
                 id = Integer.parseInt(name);
                 
                if(usertype.equals("Student"))
                {
                query="select*from users1 where sid=?";
                }
                else
                {
                    query="select *from staff1 where staff_id=?";
                }
		
			try
			{
                            
                            if(con!=null)
			ps=con.prepareStatement(query);
                            //set query parameters
                            ps.setInt(1,id);
                                 rs=ps.executeQuery();
                                 if(usertype.equals("Student"))
                                    {
                                        if(rs!=null)
                                    while(rs.next())
					{
                                            tid=rs.getInt(1);
						fname=rs.getString(2).toUpperCase();
                                                gender=rs.getString(3).toUpperCase();
                                                sqdob=rs.getDate(4);
                                                udob=(java.util.Date)sqdob;
                                                sdob=sdf.format(udob);
                                                course=rs.getString(5).toUpperCase();
                                                year=rs.getInt(6);
                                                mobile=rs.getLong(7);
                                                username=rs.getString(8);
                                                pass=rs.getString(9);
					}
                                    pw.println("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<script src=\"javascript/reg.js\">\n" +
"\n" +
"</script>\n" +
"<link rel=\"stylesheet\" href=\"css/reg.css\">\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<form action=\"AdminSaveUpdate\" style=\"border:1px  solid #ccc\" class=\"form1\" onsubmit=\" return validation()\" method=\"POST\">\n" +
"  <div class=\"container\">\n" +
"    <h1>Update</h1>\n" +
"    <p>update user profile.</p>\n" +
"    <hr>\n" +
"	<label for=\"Fname\"><b>Full Name</b></label>\n" +
"    <input type=\"text\" placeholder=\"Enter Full Name\" id=\"name\" name=\"Fname\"value='"+fname+"' ><br>\n" +
"	<span id=\"msg1\" style=\"color:red\"></span>\n" +
"	\n" +
"	<label for=\"gender\"><b>gender</b></label>\n" +
"	<select name=gender class=\"gender1\" value='"+gender+"'>\n" +
"  <option value=\"male\">Male</option>\n" +
"  <option value=\"female\">Female</option>\n" +
" </select>\n" +
"	\n" +
"	<label for=\"dob\"><b>Date of Birth</b></label>\n" +
"    <input type=\"date\"  name=\"dob\" required class=\"DOB\" value='"+udob+"'><br>\n" +
"	\n" +
"	<label for=\"course\" id=\"lab1\"><b>course</b></label>\n" +
"	<select name=\"course\" class=\"course1\" id=\"course2\" value='"+course+"' required>\n" +
"  <option value=\"MCA\">MCA</option>\n" +
"  <option value=\"Msc(CA)\">Msc(CA)</option>\n" +
"  <option value=\"Msc(CN)\">Msc(CN)</option>\n" +
"  <option value=\"Msc(CS)\">Msc(CS)</option>\n" +
"</select>\n" +
"	\n" +
"	<label for=\"course_year\" id=\"lab2\"><b>course year</b></label>\n" +
"    <input type=\"number\" placeholder=\"Enter Course year\" id=\"year\" name=\"course_year\" value='"+year+"'>\n" +
"	<span id=\"msg2\" style=\"color:red\"></span>\n" +
"    \n" +
"	<label for=\"mobile\"><b>Mobile no</b></label>\n" +
"    <input type=\"text\" placeholder=\"enter mobile no\" name=\"mobile\" id=\"mbi\"value='"+mobile+"' >\n" +
"	<span id=\"msg3\" style=\"color:red\"></span>\n" +
"    \n" +
"	<label for=\"uname\"><b>Email id</b></label>\n" +
"    <input type=\"text\" placeholder=\"Enter Email id\" name=\"uname\" id=\"u_name\" value='"+username+"'>\n" +
"	<span id=\"msg4\" style=\"color:red\"></span>\n" +
"    \n" +
"	<label for=\"password\" ><b>Password</b></label>\n" +
"    <input type=\"text\" placeholder=\"Enter Password\" name=\"password\" id=\"pass\" value='"+pass+"' >\n" +
"	<span id=\"msg5\" style=\"color:red\"></span>\n" +
"	\n" +
"     \n" +
"\n" +
"    <div class=\"clearfix\">\n" +
"      <button type=\"button\" class=\"cancelbtn\">Cancel</button>\n" +
"      <button type=\"submit\" class=\"signupbtn\">Save</button>\n" +
"    </div>\n" +
" \n" +
        "<input type='hidden' name='id' value='"+tid+"'>"+
"</form>\n" +
"     </div>\n" +
"</body>\n" +
"</html>");
                                    }//if
                                    if(usertype.equals("Staff"))
                                    {
                                        if(rs!=null)
                                             while(rs.next())
					{
                                                tid=rs.getInt(1);
						fname=rs.getString(2).toUpperCase();
                                                gender=rs.getString(3).toUpperCase();
                                                sqdob=rs.getDate(4);
                                                udob=(java.util.Date)sqdob;
                                                post=rs.getString(5).toUpperCase();
                                                mobile=rs.getLong(6);
                                                username=rs.getString(7);
                                                pass=rs.getString(8);
					}
                                             pw.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        <title>TODO supply a title</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <script src=\"javascript/reg.js\">\n" +
"\n" +
"</script>\n" +
"<link rel=\"stylesheet\" href=\"css/reg.css\">\n" +
"    </head>\n" +
"    <body>\n" +
"        \n" +
"<form action=\"AdminSaveUpdate\" style=\"border:1px  solid #ccc\" class=\"form1\" onsubmit=\" return validation()\" method=\"POST\">\n" +
"  <div class=\"container\">\n" +
"    <h1>Update</h1>\n" +
"    <p>Update satff profile</p>\n" +
"    <hr>\n" +
"	<label for=\"Fname\"><b>Full Name</b></label>\n" +
"    <input type=\"text\" placeholder=\"Enter Full Name\" id=\"name\" name=\"Fname\"value='"+fname+"'><br>\n" +
"	<span id=\"msg1\" style=\"color:red\"></span>\n" +
"	\n" +
"	<label for=\"gender\"><b>gender</b></label>\n" +
"	<select name=gender class=\"gender1\" value='"+gender+"'>\n" +
"  <option value=\"male\">Male</option>\n" +
"  <option value=\"female\">Female</option>\n" +
" </select>\n" +
"	\n" +
"	<label for=\"dob\"><b>Date of Birth</b></label>\n" +
"    <input type=\"date\"  name=\"dob\" required class=\"DOB\" value='"+udob+"'><br>\n" +
"	\n" +
"	<label for=\"course\" id=\"lab1\"><b>Post</b></label>\n" +
"	<select name=course class=\"course1\" id=\"course2\"value='"+course+"'>\n" +
"  <option value=\"Director\">Director</option>\n" +
"  <option value=\"Lecturer\">Lecturer</option>\n" +
"  <option value=\"HOD\">HOD</option>\n" +
"  <option value=\"Other\">Other</option>\n" +
"</select>\n" +
"    \n" +
"	<label for=\"mobile\"><b>Mobile no</b></label>\n" +
"    <input type=\"text\" placeholder=\"enter mobile no\" name=\"mobile\" id=\"mbi\" value='"+mobile+"'>\n" +
"	<span id=\"msg3\" style=\"color:red\"></span>\n" +
"    \n" +
"	<label for=\"uname\"><b>Email id</b></label>\n" +
"    <input type=\"text\" placeholder=\"Enter Email id\" name=\"uname\" id=\"u_name\" value='"+username+"'>\n" +
"	<span id=\"msg4\" style=\"color:red\"></span>\n" +
"    \n" +
"	<label for=\"password\" ><b>Password</b></label>\n" +
"    <input type=\"text\" placeholder=\"Enter Password\" name=\"password\" id=\"pass\" value='"+pass+"' >\n" +
"	<span id=\"msg5\" style=\"color:red\"></span>\n" +
"	\n" +
"\n" +
"    <div class=\"clearfix\">\n" +
"      <button type=\"button\" class=\"cancelbtn\">Cancel</button>\n" +
"      <button type=\"submit\" class=\"signupbtn\">Save</button>\n" +
"    </div>\n" +
" \n" +
        "<input type='hidden' name='id' value='"+tid+"'>"+
"</form>\n" +
"     </div>\n" +
"    </body>\n" +
"</html>\n" +
"");
                                    
                                    }
                                
                                
                                }
                        catch (SQLException e) {
				e.printStackTrace();
			}
                        
   }
			
       
    }

    
   
