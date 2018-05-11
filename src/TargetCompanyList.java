import java.io.IOException;
import java.io.PrintWriter;

import Connect.Dbconnect;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class TargetCompanyList extends HttpServlet{
	
	Connection con=null;
	public void init()
	{
		con=Dbconnect.getconn();
	}
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String x=req.getParameter("name");
		System.out.println(x);
		try
		{
			String x1="insert into tarcomplist(sno,name) values(?,?)";
			String x2="select sno from tarcomplist";
			
			
			PreparedStatement st1=con.prepareStatement(x1);
			PreparedStatement st2=con.prepareStatement(x2,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs2=st2.executeQuery();
			if(rs2.next())
			{
				int sno=rs2.getInt(1);
				System.out.println(sno);
				while(rs2.next())
				{
					int m=rs2.getInt("sno");
					System.out.println(m+"hello");
					if(sno<rs2.getInt("sno"))
					{
						sno=rs2.getInt("sno");
					}
				}
				System.out.println(sno);
				sno++;
				System.out.println(sno);
				st1.setInt(1,sno );
				st1.setString(2, x);
				st1.executeQuery();
			}
			else
			{
				st1.setInt(1, 1);
				st1.setString(2, x);
				st1.executeQuery();
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		pw.println("your input has been recorded");
		pw.println("");
		RequestDispatcher rd=req.getRequestDispatcher("quicklinks.html");
		rd.include(req, res);
		
	}

}
