<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin Yönetim Paneli</title>
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

		<!-- Left side column. contains the logo and sidebar -->


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>User Profile</h1>
				<ol class="breadcrumb">
					<li class="active">User profile</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<div class="row">
					<div class="col-md-3">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body box-profile">
								<img
									src='http://localhost:80/resim/server/php/files/${ pictureId }/${pictureName}'
									class="profile-user-img img-responsive img-circle"
									alt="User profile picture" width="160" height="160">
								<h3 class="profile-username text-center">
									<c:out value="${ admin.getName() } ${ admin.getSurname() }"></c:out>
								</h3>

								<p class="text-muted text-center">
									<c:out value="${ admin.getJob() }"></c:out>
								</p>


							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

						<!-- About Me Box -->
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">About Me</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">

								<strong><i class="fa fa-file-text-o margin-r-5"></i>
									Notes</strong>
								<p class="text-muted">
									<c:out value="${ admin.getDescription() }"></c:out>
								</p>

							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
					<div class="col-md-9">

						<div>
							<h4>Profil Resmi Ayarları</h4>

							<iframe
								src='http://localhost:80/resim/index.php?resim_id=<c:out value="${ kulId }"></c:out>'
								style="width: 100%; height: 500px;" frameborder="0"></iframe>
							<h4>Genel Bilgiler</h4>
						</div>
						<form action='<s:url value="/adminGuncelle"></s:url>'
							method="post">

							<input id="id" name="id" type="hidden"
								value='<c:out value="${ admin.getId() }"></c:out>'>

							<div class="form-group has-feedback">
								<input required type="text" name="name" class="form-control"
									placeholder="İsim"
									value='<c:out value="${ admin.getName() }"></c:out>'> <span
									class="glyphicon glyphicon-user form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="text" name="surname" class="form-control"
									placeholder="Soyisim"
									value='<c:out value="${ admin.getSurname() }"></c:out>'>
								<span class="glyphicon glyphicon-user form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="email" name="email" class="form-control"
									placeholder="Email" id="email"
									value='<c:out value="${ admin.getEmail() }"></c:out>'>
								<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input type="text" class="form-control" name="job"
									placeholder="Meslek"
									value='<c:out value="${ admin.getJob() }"></c:out>'> <span
									class="glyphicon glyphicon-book form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input type="text" class="form-control" name="description"
									placeholder="Açıklama"
									value='<c:out value="${ admin.getDescription() }"></c:out>'> <span
									class="glyphicon glyphicon-book form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="password" name="password"
									class="form-control" placeholder="Password" id="password"
									pattern=".{6,}" title="Six or more characters"
									value='<c:out value="${ admin.getPassword() }"></c:out>'>
								<span class="glyphicon glyphicon-lock form-control-feedback"></span>
							</div>
							<div class="form-group has-feedback">
								<input required type="password" class="form-control"
									placeholder="Retype password" id="confirm_password"
									pattern=".{6,}" title="Six or more characters"
									value='<c:out value="${ admin.getPassword() }"></c:out>'>
								<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
							</div>
							<div class="row">
								<div class="col-xs-8">
									<div class="checkbox icheck"></div>
								</div>
								<!-- /.col -->
								<div class="col-xs-4">
									<button type="submit"
										class="btn btn-primary btn-block btn-flat">Güncelle</button>
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

				</div>
				<!-- /.row -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->


		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li><a href="#control-sidebar-home-tab" data-toggle="tab"><i
						class="fa fa-home"></i></a></li>
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-user bg-yellow"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Frodo Updated His
										Profile</h4>

									<p>New phone +1(800)555-1234</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-envelope-o bg-light-blue"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Nora Joined Mailing
										List</h4>

									<p>nora@example.com</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-file-code-o bg-green"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Cron Job 254
										Executed</h4>

									<p>Execution time 5 seconds</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span
										class="label label-danger pull-right">70%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Update Resume <span class="label label-success pull-right">95%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-success"
										style="width: 95%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Laravel Integration <span
										class="label label-warning pull-right">50%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-warning"
										style="width: 50%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Back End Framework <span class="label label-primary pull-right">68%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-primary"
										style="width: 68%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Allow mail
								redirect <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Other sets of options are available</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Expose author
								name in posts <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Allow the user to show his name in blog posts</p>
						</div>
						<!-- /.form-group -->

						<h3 class="control-sidebar-heading">Chat Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Show me as
								online <input type="checkbox" class="pull-right" checked>
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Turn off
								notifications <input type="checkbox" class="pull-right">
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Delete chat
								history <a href="javascript:void(0)" class="text-red pull-right"><i
									class="fa fa-trash-o"></i></a>
							</label>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>

		<jsp:include page="${request.contextPath}/footer"></jsp:include>
	</div>

	<jsp:include page="${request.contextPath}/js"></jsp:include>
</body>
</html>