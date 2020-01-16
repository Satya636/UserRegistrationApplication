<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="ISO-8859-1">
<title>User Login</title>
<script type="text/javascript">
	function validateform() {
		var email =  document.loginForm.email.value;
		var pwd = document.loginForm.pwd.value;
		

		if (email==null || email == "") {
			Swal.fire("Email Can't Be Blank !!!");
			return false;
		} 
		else if (pwd==null || pwd == "") {
			Swal.fire("Password Can't Be Blank !!!");
			return false;
		}	
}
</script>
</head>
<body style="background-image: url('images/blue.jpg');">
	<form:form   name="loginForm" action="singIn" method="POST"  modelAttribute="loginInfo" onsubmit="return validateform()">
	
	              
	
		<h2 style="color: white; text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;Login</h2>

                  <center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="white">${loginFailed}</font></center>
	              <center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<font color="white">${accLocked}</font></center>
		<table align="center" style="width: 200px; height: 150px">
			<tr>
				<td style="color: white">Email</td>
				<td><form:input path="email" /></td>
			</tr>

			<tr>
				<td style="color: white">Password</td>
				<td><form:password path="pwd" /></td>
			</tr>

			<tr>
				
				<td  colspan="2" ><center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"  value="Sign In" /></center></td>
			</tr>
			
			<tr>
			<td colspan="2"><a href="showForgotPwdForm" style="color: white">Forgot Password?</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="registerForm" style="color: white">Sign Up</a>
			</td>
			</tr>
		</table>




	</form:form>

</body>
</html>
