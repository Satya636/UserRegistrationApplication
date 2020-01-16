<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<script type="text/javascript">
	function validateform() {
		var email = document.forgotpwdForm.email.value;

		if (email == null || email == "") {
			Swal.fire("Email Can't Be Blank !!!");
			return false;
		}
	}
	
</script>

</head>
<body style="background-image: url('images/forgot_bg.jpg');">

	<form:form name="forgotpwdForm" action="forgotPwd" method="POST"
		modelAttribute="userPwd" onsubmit="return validateform()">

		<h2 style="color: white; text-align: center;">&nbsp;&nbsp;&nbsp;Forgot
			Password</h2>

		<center>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="white">${NA}</font><font
				color="white">${LOCKED}</font> </font> <font color="white">${EMAIL_SENT}</font>
		</center>


		<table align="center">
			<tr>
				<td style="color: white;">Email</td>
				<td><form:input path="email" size="25" /></td>
			</tr>

			<tr>
				<td colspan="2"><center>
						<br>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit"
							value="Submit">
					</center></td>
			</tr>











		</table>


	</form:form>
</body>
</html>