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
		var uname = document.registerForm.userName.value;
		var email = document.registerForm.email.value;
		var phno = document.registerForm.phNo.value;

		if (uname == null || uname == "") {
			Swal.fire("Name Can't Be Blank !!!");
			return false;
		} else if (email == null || email == "") {
			Swal.fire("Email Can't Be Blank !!!");
			return false;
		} else if (phno == null || phno == "") {
			Swal.fire("PhoneNo Can't Be Blank !!!");
			return false;
		} else {
			showMessage();
		}
	}
</script>
<script type="text/javascript">
	function showMessage() {
		var uname = document.registerForm.userName.value.alert("Hello " + uname
				+ " !!! Are You Sure, Want To Register ???");
	}
</script>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(event) {
		$("#email").blur(function(e) {

			var email = $("#email").val();
			$.ajax({
				type : "GET",
				url : 'validateEmail?email=' + email,
				success : function(result) {
					if (result == 'Duplicate') {
						$("#Email").html("Email Already Exists..!!");
						$("#email").focus();
						$("#RegisterBtn").prop("disabled", true);
					} else {
						$("#Email").html("");
						$("#RegisterBtn").prop("disabled", false);
					}
				}
			});
		});
	});
</script>

</head>
<body background="images/user_bg.jpg">
	<h2 style="color: white; text-align: center;">User Registration</h2>

	<form:form name="registerForm" action="register" method="POST"
		modelAttribute="userInfo" onsubmit="return validateform()">
		<center>
			<font color=#ccffff>${sucessMsg}</font> 
		</center>
		<center>
			<font color=#ccffff>${failureMsg}</font>
		</center>
	

		<center>
			<table style= "width: 200px; height: 150px">
				<tr>
					<td style="color: white;">Name</td>
					<td><form:input path="userName" /></td>
				<tr>
				<tr>
					<td style="color: white;">Email</td>
					<td><form:input path="email" /></td>
					<div id="Email" style="color: white;"></div> 
					
				</tr>

				<tr>
					<td style="color: white;">Phone No</td>
					<td><form:input path="phNo" /></td>
				</tr>
			</table>
			<tr>
				<td></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
					value="Register" id="RegisterBtn" /></td>
			</tr>

		</center>
	</form:form>








</body>
</html>