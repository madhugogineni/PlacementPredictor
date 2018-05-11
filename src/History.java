import java.io.IOException;
import Connect.Dbconnect;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class History extends HttpServlet{
	
	Connection con=null;
	public void init()
	{
		con=Dbconnect.getconn();
	}
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String name=req.getParameter("name");
		String timespan=req.getParameter("timespan");
		String studno=req.getParameter("studno");
		String pkage=req.getParameter("package");
		try
		{
			String query="insert into history values(?,?,?,?,?)";
			String check="select sno from history";
			PreparedStatement stmt1=con.prepareStatement(check,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			PreparedStatement stmt2=con.prepareStatement(query);
			ResultSet rs1=stmt1.executeQuery();
			
			if(rs1.next())
			{
				int sno=rs1.getInt(1);
				System.out.println(sno);
				while(rs1.next())
				{
					int m=rs1.getInt("sno");
					System.out.println(m+"hello");
					if(sno<rs1.getInt("sno"))
					{
						sno=rs1.getInt("sno");
					}
				}
				System.out.println(sno);
				sno++;
				System.out.println(sno);
				stmt2.setInt(1, sno);
				stmt2.setString(2, name);
				stmt2.setString(3, timespan);
				stmt2.setInt(4, Integer.parseInt(studno));
				stmt2.setString(5,pkage);
				stmt2.executeQuery();
			}
			else
			{
				stmt2.setInt(1,1);
				System.out.println("1");
				stmt2.setString(2, name);
				stmt2.setString(3, timespan);
				stmt2.setInt(4, Integer.parseInt(studno));
				stmt2.setString(5,pkage);
				stmt2.executeQuery();
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		pw.println("your input has been recorded");
		RequestDispatcher rd=req.getRequestDispatcher("quicklinks.html");
		rd.include(req, res);
	}

}
