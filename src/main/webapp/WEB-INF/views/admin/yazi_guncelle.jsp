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

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Yazı Güncelle</h1>
				<ol class="breadcrumb">
					<li><a href='<s:url value="/yaziEkle"></s:url>'><i
							class="fa fa-edit"></i> Yazi Güncelle</a></li>
				</ol>
			</section>
			<form class="form col-md-12" action='<s:url value="/yaziGuncelle"></s:url>' method="post">
				<div class="col-md-12">
					<h5>Yazı Başlığı</h5>
					<input type="hidden" name="id" value='<c:out value="${ writing.getId() }"></c:out>' >
					<input required name="title" class="form-control col-md-6" type="text"
						name="WritingTitle" value='<c:out value="${ writing.getTitle() }"></c:out>' />
				</div>
				<div class="col-md-12">
					<h5>Yazı İçeriği</h5>
					<textarea id="content" name="content" class="ckeditor"
						rows="5" id="comment" value='<c:out value="${ writing.getContent() }" escapeXml="false"></c:out>'><c:out value="${ writing.getContent() }" escapeXml="false"></c:out></textarea>

				</div>

				<div class="col-md-3">
					<div></div>
						<h5>Yazı Kategori</h5>
	


						<select required class="form-control" name="category">
							<c:if test="${ not empty categoryList }">
								<c:forEach items="${categoryList }" var="item">
									<option <c:if test = "${item.getId() == writing.getId()}">selected</c:if>><c:out value="${ item.getName() }"></c:out></option>
									
								</c:forEach>
							</c:if>

						</select>
					<hr>
					<input id="yaziGuncelle" class="form-control" type="submit"
						value="Guncelle" />
						
				</div>
			</form>

		</div>
		<jsp:include page="${request.contextPath}/footer"></jsp:include>


	</div>
	<!-- ./wrapper -->


	<jsp:include page="${request.contextPath}/js"></jsp:include>
</body>

</html>