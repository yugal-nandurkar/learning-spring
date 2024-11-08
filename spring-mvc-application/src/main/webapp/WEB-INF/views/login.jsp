<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
My First JSP is ${name}
<%
    // Capture the 'name' parameter from the request and store it in a variable
    String name = request.getParameter("name");

    // Log the name parameter to the server console using pageContext.getServletContext().log
    pageContext.getServletContext().log("Name parameter: " + name);

    // Create a Date object to show the current date
    Date date = new Date();
%>

<!-- Display the captured 'name' parameter: http://localhost:8081/?name=yugal and current date -->
<div> Captured name: <%= name %> </div>
<div> Current date is <%= date %> </div>
<form action="/login" method="post">
    Enter your name: <input type="text" name="name"/> password: <input type="password" name="password"/> <input type="submit" value="Submit" />
    <p><font color = "red">${error}</font></p>
</form>
</body>
</html>
