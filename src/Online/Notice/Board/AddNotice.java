
package Online.Notice.Board;

import java.io.IOException;
import java.io.InputStream;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig(maxFileSize = 16177215) 
public class AddNotice extends HttpServlet {
    Connection con;
    PreparedStatement ps=null,ps1=null;
    int id,count,year,rand_int1;
    String query,filename,mimtype;
    String title,notice,coures,tyear;
    InputStream inputstream;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        
        con=conn.connection.getConnection();
        //geting value form html file
        title=request.getParameter("title");
        notice=request.getParameter("noticebox");
        coures=request.getParameter("course").toUpperCase();
        tyear=request.getParameter("year");
          Part filePart = request.getPart("fileupload");
                  if (filePart != null) {
            // prints out some information for debugging
            filename=filePart.getName();
           filename= filePart.getSubmittedFileName();
            System.out.println(filePart.getSize());
            mimtype=(filePart.getContentType());
            System.out.println(filePart.getContentType());
             System.out.println(filename);
           
            // obtains input stream of the upload file
           inputstream = filePart.getInputStream();
        }
        if(coures.equals("STUDENT"))
        {
                query="insert into notice1 (nid,title,notice,comman,n_date,file1,file_name) values(notice_id.nextval,?,?,?,sysdate,?,?)";
     
        }
        else
        {
                    year=Integer.parseInt(tyear);

            query="insert into notice1 (nid,title,notice,course,year,n_date,file1,file_name) values(notice_id.nextval,?,?,?,?,sysdate,?,?)";
        }
        
        try
        {
            
      ps=con.prepareStatement(query);
      //set query parameters
      if(coures.equals("STUDENT"))
        {
            ps.setString(1, title);
      ps.setString(2, notice);
      ps.setString(3, coures);
      ps.setBlob(4, inputstream);
      ps.setString(5, filename);
      
        }
        else
      {
      ps.setString(1, title);
      ps.setString(2, notice);
      ps.setString(3, coures);
      ps.setInt(4, year);
      ps.setBlob(5, inputstream);
      ps.setString(6, filename);
     
      }
      //execute query
      
      count=ps.executeUpdate();
      
       }
        catch(SQLException se)
        {
        se.printStackTrace();
        }
      if(count>0)
      {
          pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('notice is added sucessfully...');");
		   pw.println("location='AddNotice.html';");
		   pw.println("</script>");
      }
      else
      {
      pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('notice is not added please try again.');");
		   pw.println("location='AddNotice.html';");
		   pw.println("</script>");
      } 
        
    }
}
