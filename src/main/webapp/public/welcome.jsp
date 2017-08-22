<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>index</title>
</head>
<body>
  <h2>welcome <c:out value="${username}"/> !!!</h2>
  <b><c:out value="${pageContext.request.remoteUser}"/></b>
  <a href="/security-java/index.jsp"> Home </a>
  <br/><br/>
  <form  action="/security-java/logout" method="post">
	      <input type="submit" value="Log out" />
	      <!--	HTTP ERROR 403
				Problem accessing /security-java/logout. Reason:
				Could not verify the provided CSRF token because your session was not found. -->
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
</body>
</html>