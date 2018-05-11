<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
    <%@page import="Connect.Dbconnect" %>
    <%
    Connection conn=null;
    conn=Dbconnect.getconn();
    Statement stmt=null;
    ResultSet rs=null;
    ResultSet rs1=null;
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>Target Company Details</h1>
</center>
<%
String demo="";
stmt=conn.createStatement();
String x=request.getParameter("compname");
String query="select * from tarcomplist where sno="+x;
rs=stmt.executeQuery(query);
while(rs.next()){
demo=rs.getString("name");
}
String query2="select * from tarcompdetails where name='"+demo+"'";
rs1=stmt.executeQuery(query2);
while(rs1.next())
{
	out.println("<font size='4'><b>Company Name</b></font>:&nbsp;"+rs1.getString("name")+"<br/><br/>");
	out.println("<font size='4'><b>Percentage Required</b></font>:&nbsp;"+rs1.getInt("percentage")+"<br/><br/>");
	out.println("<font size='4'><b>No. of Projects</b></font>:&nbsp;"+rs1.getInt("projectno")+"(atleast)<br/><br/>");
	out.println("<font size='4'><b>No. of Backlogs Allowed</b></font>:&nbsp;"+rs1.getInt("backlogs")+"(at max)<br/><br/>");
	out.println("<font size='4'><b>Time to clear the Backlogs</b></font>:&nbsp;"+rs1.getString("backlogcleartime")+"<br/><br/>");
	out.println("<font size='4'><b>No.of Internships</b></font>:&nbsp;"+rs1.getInt("intern")+"(not compulsory)<br/><br/>");
	out.println("<font size='4'><b>Programming Skills Required</b></font>:&nbsp;"+rs1.getString("programminglang")+"<br/><br/>");
	out.println("<font size='4'><b>Interview Pattern</b></font>:&nbsp;"+rs1.getString("interviewprep")+"<br/><br/>");
}
%>
</body>
</html>