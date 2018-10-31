<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Register
	<form action="register" method="post">
		Name <input type="text" name="f1" /><br /> 
		Email: <input type="text" name="f2" /><br /> 
		Password: <input type="text" name="passwor" /><br /> <input type="submit" value="Register Now" />
	</form>
	<br>
	<form action="login" method="post">
		Email: <input type="text" name="email" /><br /> Password: <input
			type="password" name="password" /><br /> <input type="submit"
			value="Login Now" />
	</form>
</body>
</html>