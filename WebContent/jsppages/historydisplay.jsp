<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="Connect.Dbconnect" %>
 <%
            Connection  conn=null;  
            conn =Dbconnect.getconn();
            Statement stmt=null;
            ResultSet rs=null;
            %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>History</h1>
<%
		stmt= conn.createStatement();
		String s = "SELECT * FROM history order by sno";
		 rs= stmt.executeQuery(s);
		if(rs.next())
		{
			
			out.println("<table border='3'>");
			out.println("<tr>");
			out.println("<th>Sno</th>");
			out.println("<th>Name</th>");
			out.println("<th>Timespan</th>");
			out.println("<th>Recruited No</th>");
			out.println("<th>Package</th>");
			out.println("</tr>");
			do
			{
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getInt(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				out.println("</tr>");
			}while(rs.next());
			out.println("</table>");
		}
		else
		{
			out.println("<h3>No announcements as of now. Come back later</h3>");
		}

%>
</center>
</body>
</html>