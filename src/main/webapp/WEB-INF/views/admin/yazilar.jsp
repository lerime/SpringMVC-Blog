<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Kategoriler</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<jsp:include page="${request.contextPath}/css"></jsp:include>
<script
	src='<s:url value="/resources/dist/js/jquery-3.2.1.min.js"></s:url>'></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include page="${request.contextPath}/header"></jsp:include>
		<jsp:include page="${request.contextPath}/menu"></jsp:include>



		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					<c:out value="${ writing.getCategory() }"></c:out>
					-&nbsp;Yazilar
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-list"></i> Yazılar</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Info boxes -->

				<div class="col-md-12">
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">Yazılar</h3>
						</div>
						<!-- /.box-header -->

						<div class="box-body">
							<table class="table table-bordered">
								<tr>
									<th style="width: 10px">#</th>
									<th>Başlık</th>
									<th>Kategori</th>
									<th>Yazar</th>
									<th>Eklenme Tarihi</th>
									<th>Düzenle</th>
									<th>Sil</th>
								</tr>

								<c:if test="${ not empty writings }">
									<c:set var="count" value="1" scope="page" />
									<c:forEach items="${writings }" var="item">
										<tr>
											<td><c:out value="${ count }"></c:out></td>
											<td><c:out value="${ item.getTitle() }"></c:out></td>
											<td><c:out value="${ item.getCategory() }"></c:out></td>
											<td><c:out value="${ item.getWriter() }"></c:out></td>
											<td><c:out value="${ item.getDate() }"></c:out></td>
											<c:if test="${ item.getWriterId() == kulid }">
												<td><a id="sil"
													href='<s:url value="/sil/${ item.getId() }"></s:url>'
													class="btn btn-danger"
													onclick="if (confirm('Are you sure you want to delete?')) href='<s:url value="/sil/${ item.getId() }"></s:url>'; else return false;">Sil</a></td>
												<td>
											</c:if>
											<c:if test="${ item.getWriterId() == kulid }">
												<a
													href='<s:url value="/yaziGuncelle/${ item.getId() }"></s:url>'
													method="post" class="btn btn-info">Düzenle</a>
											</c:if>
											</td>
										</tr>
										<c:set var="count" value="${count + 1}" scope="page" />

									</c:forEach>
								</c:if>

							</table>
						</div>
						




					</div>
					<!-- /.box -->
				</div>



			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<jsp:include page="${request.contextPath}/footer"></jsp:include>

	</div>
	<!-- ./wrapper -->
	<jsp:include page="${request.contextPath}/js"></jsp:include>

</body>


</html>
