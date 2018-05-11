import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import Connect.Dbconnect;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ann extends HttpServlet{

	Connection con=null;
	public void init()
	{
			con=Dbconnect.getconn();
	}
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		String x=req.getParameter("info");
		PrintWriter pw=res.getWriter();
		
		try{
			String x1="insert into announcements(sno,info) values(?,?)";
			String x2="select sno from announcements";
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
				sno=sno+1;
				System.out.println(sno);
				st1.setInt(1,sno);
				st1.setString(2, x);
				st1.executeQuery();
			}
			else
			{
				st1.setInt(1, 1);
				st1.setString(2, x);
				st1.executeQuery();
			}
			pw.println("<h4>the data you entered has been recorded</h4>");
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		RequestDispatcher rd=req.getRequestDispatcher("quicklinks.html");
		rd.include(req, res);
	}
}
