<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<meta charset="ISO-8859-1">
<title>User Registration Management System</title>
<script type="text/javascript">
	function validateform() {
		var tpwd = document.unlockForm.tempPwd.value;
		var pwd1 = document.unlockForm.newPwd.value;
		var pwd2 = document.unlockForm.cnfPwd.value;

		if (tpwd == null || tpwd == "" || pwd1 == null || pwd2 == null || pwd1 == "" || pwd1 == "") {
			Swal.fire("Password Can't Be Blank !!!");
			return false;
		} else if (pwd1.length < 6 || pwd2.length < 6) {
			Swal.fire("Password must be at least 6 characters long !!!");
			return false;
		} else if (pwd1 != pwd2) {
			Swal.fire("New Password And Confirm Passwords Do not match !!!");
			return false;
		}

	}
</script>

</head>
<body background="images/gr.jpg">
	<h2 style="color: white; text-align: center;">Unlock Account</h2>

	<center>
		<font color="white">${success} </font>
	</center>
	<br>
	<center>
		<font color="white"> ${failure} </font>
	</center>
	<form:form name="unlockForm" onsubmit="return validateform()"
		action="unlockUser" method="POST" modelAttribute="userUnlockInfo">

		<center>
			<font color=#ccffff>${sucessMsg}</font>
		</center>
		<center>
			<font color=#ccffff>${failureMsg}</font>
		</center>

		<center>
			<table style="width: 200px; height: 150px">
				<tr>
					<td style="color: white;">Email</td>
					<td><form:input path="email" readonly="true" /></td>
				<tr>
				<tr>
					<td style="color: white;">Temporary Password</td>
					<td><form:input path="tempPwd" /></td>
				</tr>
				<tr>
					<td style="color: white;">New Password</td>
					<td><form:password path="newPwd" /></td>
				</tr>
				<tr>
					<td style="color: white;">Confirm Password</td>
					<td><form:password path="cnfPwd" /></td>
				</tr>
			</table>
			<tr>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
					value="Unlock"></td>
			</tr>

		</center>
	</form:form>
</body>
</html>