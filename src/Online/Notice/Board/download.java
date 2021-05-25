/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Online.Notice.Board;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class download extends HttpServlet
{
     
 Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
        HttpSession session;
        String uname,pass,tid,FileName,mimtype;
        int id;
        String query="select file1, file_name from notice1 where Nid=?";
        ServletOutputStream sos=null;
        byte[] img=null;
        
 @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
        tid=request.getParameter("id");
        id=Integer.parseInt(tid);
        con=conn.connection.getConnection();
        try
        {
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
        try
        {
            //set parametet
            ps.setInt(1,id);
            //execute query
            rs=ps.executeQuery();
            //fetch records form result set
            while(rs.next())
            {
           img=rs.getBytes(1);
           FileName=rs.getString(2);
          
            }
            if(img==null){
                PrintWriter out=response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('File is not avilable');");
           
            out.println("</script>");
            }
            else
            {
                try{
           response.setContentType("APPLICATION/OCTET-STREM");
          response.setHeader("Content-Disposition", "attachment;filename=" +FileName );
        
           sos=response.getOutputStream();
           sos.write(img);
                }
                catch(NullPointerException npe)
                {
                    PrintWriter out=response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('File is not avilable');");
            out.println("location='view notice.html';");
            out.println("</script>");
                }
            }
            
        }//try
        catch(SQLException se)
        {
        se.printStackTrace();
        }
       
    }
   
}

