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


<link rel="stylesheet"
	href='<s:url value="/resources/dist/css/bootstrap.min.css"></s:url>'>
<link rel="stylesheet"
	href='<s:url value="/resources/dist/css/bootstrap-theme.min.css"></s:url>'>
<jsp:include page="${request.contextPath}/css"></jsp:include>
<style>
.deneme {
	height: 95%;
	position: relative;
	overflow-x: hidden;
	overflow-y: auto;
}

.section {
	height: 100%;
}
</style>
</head>
<body class="hold-transition skin-green sidebar-mini">
	<div class="wrapper ">

		<jsp:include page="${request.contextPath}/header"></jsp:include>
		<jsp:include page="${request.contextPath}/menu"></jsp:include>


		<div class="content-wrapper well">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Kategoriler</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-list"></i> Kategori</a></li>
					<li class="active">Kategori Görüntüle/Ekle</li>
				</ol>


			</section>

			<!-- Main content -->
			<section class="content well">
				<h1 class="bg-white text-white">${category}</h1>
				<p>${writing.getDate()}</p>
				<c:if test="${ not empty writing }">
					<h2 class="bg-warning text-white" align="Center">
						<c:out value="${ writing.getTitle() }"></c:out>
					</h2>
					<hr>
					<section>
						<h4>
							<c:out value="${ writing.getContent() }" escapeXml="false"></c:out>
						</h4>
					</section>
					<hr class="bg-warning">
					<p>
						<strong>Yazar: </strong>${writing.getWriter()}</p>
				</c:if>
			
			<div class="well">
				<div>
					<h2>Yorumlar</h2>
				</div>
				<c:if test="${ not empty comments}">
					<c:forEach items="${ comments }" var="item">
						<div class="well">
							<div>
								<h3>
									<c:out value="${ item.getName() }"></c:out>
								</h3>
							</div>
							<div>
								<p>
									<c:out value="${ item.getContent() }"></c:out>
								</p>
							</div>
						</div>

					</c:forEach>
				</c:if>
				<div class=" col-md-8 well">
				<h2>Yorum Ekle</h2>
					<form class="form" action='<s:url value="/commentEkle"></s:url>'
						method="post">
						<input type="hidden" name="writingId" value="${ writing.getId() }">
						<div class="col-md-12">
							<h5>İsim Soyisim</h5>
							<input required name="name" id="title"
								class="form-control col-md-6" type="text" />
							<h5>Email</h5>
							<input required name="email" id="title"
								class="form-control col-md-6" type="email" />
							<h5>Web Sitesi</h5>
							<input name="website" id="title" class="form-control col-md-6"
								type="text" />
						</div>
						<div class="col-md-12">
							<h5>Yorum</h5>
							<textarea id="aciklama" name="content"
								class="form-control col-md-6" rows="5"></textarea>

						</div>

						<div class="col-md-3">

							<input id="yaziEkle" class="form-control" type="submit"
								value="Ekle" />
							<hr>

						</div>
						<c:if test="${ not empty error }">
							<script>
								document.getElementById("title").select();
							</script>
							<div class="col-md-12">
								<div id="alertDiv" class="alert alert-danger">
									<strong>Hatalar</strong>
									<c:out value="${error }"></c:out>
								</div>

							</div>

						</c:if>
						<c:if test="${ not empty success }">
							<hr>
							<div class="col-md-12">
								<div id="alertDiv" class="alert alert-success">
									<c:out value="${success }"></c:out>
								</div>

							</div>

						</c:if>
					</form>
				</div>
			</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->


	</div>
	<jsp:include page="${request.contextPath}/footer"></jsp:include>
	<!-- ./wrapper -->
	<jsp:include page="${request.contextPath}/js"></jsp:include>

</body>
</html>
