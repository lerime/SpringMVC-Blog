<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Kategoriler</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <jsp:include page="${request.contextPath}/css"></jsp:include>
  
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

 <jsp:include page="${request.contextPath}/header"></jsp:include>
 <jsp:include page="${request.contextPath}/menu"></jsp:include>
  

  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Yorumlar
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-list"></i> Yorumlar</a></li>
        <li class="active">Yorum Görüntüle/Sil</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Info boxes -->
      
      <div class="col-md-12">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Yorumlar Tablosu</h3>
            </div>
            <!-- /.box-header -->
            
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>İsim-Soyisim</th>
                  <th>Yorum</th>
                </tr>
                
                  <c:if test="${ not empty comment }">
							<tr>
								<td><c:out value="${ comment.getName() }"></c:out></td>
							
								<td><c:out value="${ comment.getContent() }"></c:out></td>
							</tr>
							
					</c:if>
                
              </table>
            </div>
            <!-- /.box-body -->
           
            <hr/>
          
            

            <c:if test="${ not empty categorySaveError }">
					<script>
						document.getElementById("title").select();
					</script>
					<div class="col-md-12">
						<div id="alertDiv" class="alert alert-danger">
							<c:out value="${categorySaveError }"></c:out>
						</div>

					</div>

				</c:if>
				<c:if test="${ not empty categorySaveSuccess }">
					<hr>
					<div class="col-md-12">
						<div id="alertDiv" class="alert alert-success">
							<c:out value="${categorySaveSuccess }"></c:out>
						</div>

					</div>

				</c:if>
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

<script>
	setTimeout(function() {
		$('#alertDiv').delay(3000).hide('slow');
	});
</script>

</html>
