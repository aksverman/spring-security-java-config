<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>index</title>
</head>
<body>
	<div class="container">
	    <h1>This is not secured!</h1>
	    <p> Hello User</p>
	    <b><c:out value="${pageContext.request.remoteUser}"/></b>
	    <hr color="red"/>
	    <table cellspacing='12px'> 
	    	<tr>
		    	<th rowspan="2"><a href="/security-java/user/login">  Login  </a></th>
		    	<th><a href="/security-java/user/register">  Create user  </a> </th>
		    	<th><a href="/security-java/user/show">  show secured page  </a></th>
		    	<th><a href="/security-java/user/listAll"> List Users</a> </th></tr>
	    	<tr></tr><tr>
		    	<th><a href="/security-java/public/secured"> @Secured </a> </th>
		    	<th><a href="/security-java/public/pre"> @Pre </a> </th>
		    	<th><a href="/security-java/public/prewithparam/admin"> @PreWithParam </a> </th>
		    	<th><a href="/security-java/public/post"> @Post </a> </th>
		    	<th><a href="/security-java/public/precustom/aks"> @PreCustomUtil </a> </th>
		    	<th><a href="/security-java/public/rolesallwoed"> @RolesAllowed </a></th>
		    </tr>
	    </table> 
	    <form  action="/security-java/logout" method="post">
	      <input type="submit" value="Log out" />
	      <!--	HTTP ERROR 403
				Problem accessing /security-java/logout. Reason:
				Could not verify the provided CSRF token because your session was not found. -->
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
  	</div>
</body>
</html>