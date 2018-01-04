<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<script
	src='<s:url value="/resources/dist/js/jquery-3.2.1.min.js"></s:url>'></script>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin İşlemleri</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<jsp:include page="${request.contextPath}/css"></jsp:include>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="${request.contextPath}/header"></jsp:include>
		<jsp:include page="${request.contextPath}/menu"></jsp:include>
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Admin Ekle</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-list"></i> Admin Islemleri</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="register-box">


					<div class="register-box-body">
						<h3>
							<p class="login-box-msg">Yeni Bir Admin Ekle</p>
						</h3>
						<form action='<s:url value="/adminEkle"></s:url>' method="post">
							<div class="form-group has-feedback">
								<input required type="text" name="name" class="form-control"
									placeholder="İsim"> <span
									class="glyphicon glyphicon-user form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="text" name="surname" class="form-control"
									placeholder="Soyisim"> <span
									class="glyphicon glyphicon-user form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="email" name="email" class="form-control"
									placeholder="Email" id="email"> <span
									class="glyphicon glyphicon-envelope form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input type="text" class="form-control" name="job"
									placeholder="Meslek"> <span
									class="glyphicon glyphicon-book form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="password" name="password"
									class="form-control" placeholder="Password" id="password"
									pattern=".{6,}" title="Six or more characters"> <span
									class="glyphicon glyphicon-lock form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="password" class="form-control"
									placeholder="Retype password" id="confirm_password"
									pattern=".{6,}" title="Six or more characters"> <span
									class="glyphicon glyphicon-log-in form-control-feedback"></span>
							</div>
							<div class="row">
								<div class="col-xs-8">
									<div class="checkbox icheck"></div>
								</div>
								<!-- /.col -->
								<div class="col-xs-4">
									<button type="submit"
										class="btn btn-primary btn-block btn-flat">Register</button>
								</div>
								<!-- /.col -->
							</div>

							<c:if test="${ not empty hata }">
								<script>
									document.getElementById("email").select();
								</script>
								<div class="form-group has-feedback">
									<div id="alertDiv" class="alert alert-danger">
										<strong>Hatalar</strong>
										<c:out value="${hata }"></c:out>
									</div>

								</div>

							</c:if>
							<c:if test="${ not empty success }">
								<hr>
								<div class="form-group has-feedback">
									<div id="alertDiv" class="alert alert-success">
										<c:out value="${success }"></c:out>
									</div>

								</div>

							</c:if>
						</form>


					</div>
					<!-- /.form-box -->
				</div>
				<!-- /.register-box -->
			</section>
		</div>
		<jsp:include page="${request.contextPath}/footer"></jsp:include>

	</div>
	<!-- ./wrapper -->
	<jsp:include page="${request.contextPath}/js"></jsp:include>

</body>
<script>
	setTimeout(function() {
		$('#alertDiv').delay(3000).hide('slow');
	});
</script>
<script type="text/javascript">
	var password = document.getElementById("password"), confirm_password = document
			.getElementById("confirm_password");

	function validatePassword() {
		if (password.value != confirm_password.value) {
			confirm_password.setCustomValidity("Passwords Don't Match");
		} else {
			confirm_password.setCustomValidity('');
		}
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;
</script>


</html>
