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
<div class="wrapper">

 <jsp:include page="${request.contextPath}/header"></jsp:include>
 <jsp:include page="${request.contextPath}/menu"></jsp:include>
  

  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Kategoriler
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-list"></i> Kategori</a></li>
        <li class="active">Kategori Görüntüle/Ekle</li>
      </ol>
      
      
    </section>

    <!-- Main content -->
    <section class="content">
    <div class="well">
				<h2>${category}</h2></div>
					<hr>
					<c:if test="${ not empty writings }">
						<c:set var="count" value="1" scope="page" />
						<c:forEach items="${writings }" var="item">
							<section>
								<h3>
									<a
										href='<s:url value="/${item.getId()}/${item.getId()}  "></s:url>'><c:out
											value="${ item.getTitle() }"></c:out></a>
								</h3>
							</section>
						</c:forEach>
					</c:if>
				</section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


</div>
<!-- ./wrapper -->

<jsp:include page="${request.contextPath}/footer"></jsp:include>
<jsp:include page="${request.contextPath}/js"></jsp:include>

</body>
</html>
