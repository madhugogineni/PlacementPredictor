<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*" %>
<%@page import="Connect.Dbconnect" %>
<%
Connection conn=null;
conn=Dbconnect.getconn();
Statement stmt=null;
ResultSet rs=null;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function check()
{
	
}
</script>
</head>
<body>
<center>
<form action="targetcompdetailsdisplay.jsp" method="post">
<%
stmt= conn.createStatement();
String s = "SELECT * FROM tarcomplist order by sno";
rs= stmt.executeQuery(s);
if(rs.next())
{
	out.println("Company:");
	out.println("<select name='compname'>");
	out.println("<option selected disabled>choose</option>");
	
	do
	{
		out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
	
	}while(rs.next());
	out.println("</select>");
	out.println("<br/><br/>");
	out.println("<input type='submit' value='Submit'/>");
}
else
{
	out.println("Placement details now ready yet. Come back later");
}
%>

</form>
</center>
</body>
</html>