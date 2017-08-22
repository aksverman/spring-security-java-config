<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<h3>Custom Login with Username and Password</h3>
	<form name="f" action="/security-java/login" method="POST">
		<table>
			<tbody>
				<tr><td>User:</td><td><input type="text" name="username" value=""></td></tr>
				<tr><td>Password:</td><td><input type="password" name="password"></td></tr>
				<tr><td colspan="2"><input name="submit" type="submit" value="Login"></td></tr>
				<tr><td colspan="2"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td></tr>
		</tbody></table>
</form>
</body>
</html>