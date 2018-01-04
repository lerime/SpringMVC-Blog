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

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Yazı Ekle</h1>
				<ol class="breadcrumb">
					<li><a href='<s:url value="/yaziEkle"></s:url>'><i
							class="fa fa-edit"></i> Yazi Ekle</a></li>
				</ol>
			</section>
			<form class="form col-md-12"
				action='<s:url value="/yaziEkle"></s:url>' method="post">
				<div class="col-md-12">
					<h5>Yazı Başlığı</h5>
					<input required name="title" id="title"
						class="form-control col-md-6" type="text" name="WritingTitle" />
				</div>
				<div class="col-md-12">
					<h5>Yazı İçeriği</h5>
					<textarea id="aciklama" name="content" class="ckeditor" rows="5"
						id="comment"></textarea>

				</div>

				<div class="col-md-3">
					<div></div>
					<h5>Yazı Kategori</h5>



					<select required class="form-control" name="cmbKategori">
						<c:if test="${ not empty categoryList }">
							<c:forEach items="${categoryList }" var="item">
								<option><c:out value="${ item.getName() }"></c:out></option>

							</c:forEach>
						</c:if>

					</select>
					<hr>
					<input id="yaziEkle" class="form-control" type="submit"
						value="Ekle" />
					<hr>

				</div>
				<c:if test="${ not empty hataYaziEkle }">
					<script>
						document.getElementById("title").select();
					</script>
					<div class="col-md-12">
						<div id="alertDiv" class="alert alert-danger">
							<strong>Hatalar</strong>
							<c:out value="${hataYaziEkle }"></c:out>
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
<script type="text/javascript"
	src='<s:url value="/resources/ckeditor/ckeditor.js"></s:url>'></script>
</html>