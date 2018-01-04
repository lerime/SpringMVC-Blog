<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<header class="main-header">

	<!-- Logo -->
	<c:if test="${ not empty kulid }">
		<a href='<s:url value="/yonetim"></s:url>' class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>A</b>LT</span> <!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>Yönetim Sayfası</b></span>
		</a>
	</c:if>
	<c:if test="${empty kulid }">
		<a href='<s:url value="/"></s:url>' class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>A</b>LT</span> <!-- logo for regular state and mobile devices -->
			<span class="logo-lg"><b>Ana Sayfa</b></span>
		</a>

	</c:if>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<c:if test="${ not empty kulid }">
			<a href="#" class="sidebar-toggle" data-toggle="push-menu"
				role="button"> <span class="sr-only">Toggle navigation</span>
			</a>
		</c:if>

		<c:if test="${empty kulid }">
			<ul class="nav navbar-nav">
				<c:if test="${ not empty categoryList }">
					<c:forEach items="${categoryList }" var="item">

						<input type="hidden" name="categoryId"
							value='<c:out value="${ item.getId() }"></c:out>' />

						<li><a href='<s:url value="/${ item.getId() }"></s:url>'><i></i>
								<c:out value="${ item.getName() }"></c:out></a></li>
					</c:forEach>
				</c:if>
				<li><a href="#">Hakkımızda</a></li>

			</ul>
		</c:if>

		<c:if test="${ not empty kulid }">
			<!-- Navbar Right Menu -->
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="dropdown messages-menu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="fa fa-envelope-o"></i> <span class="label label-success"><c:if test="${ nonReadComments.size() > 0}"><c:out value="${ nonReadComments.size() }"></c:out></c:if></span>
					</a>
						<ul class="dropdown-menu">
						<!-- Buraya ordan gelen okunmamis mesaj count unu yazdir -->
							<li class="header">You have <c:out value="${ nonReadComments.size() }"></c:out> messages</li>
							
							<!-- Sonra forecah ile yardiracaksin burdan -->
							<c:forEach items="${ nonReadComments }" var="item">
							
						
							<li>
								<!-- inner menu: contains the actual data -->
								<ul class="menu">
									<li>
									<input type="hidden" name="id" value='<c:out value="${ item.getId() }"></c:out>'>
										<!-- start message --> <a href='<s:url value="/yorumGoster/${item.getId()}"></s:url>'>
											
											<h4>
												<c:out value="${ item.getName() }"></c:out>
											</h4>
											<!-- ilk 50 karakteri alip burada yazdirabilirsin -->
											<p><c:out value="${item.getContent() }"></c:out></p>
									</a>
									</li>
									
									
								</ul>
							</li>
								</c:forEach>
							<!-- Mesajlarin gosterildigi bir sayfa yapilip oraya yonlendirilebilir -->
							<li class="footer"><a href="#">Tüm Mesajlar</a></li>
						</ul></li>

					<!-- User Account: style can be found in dropdown.less -->
					<li class="dropdown user user-menu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <img
							src='http://localhost:80/resim/server/php/files/${ pictureId }/${pictureName}'
							class="img-circle" alt="User Image" width="20" height="20">
							<span class="hidden-xs"><c:out
									value="${ admin.getName() } ${admin.getSurname() }"></c:out></span>
					</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header"><img
								src='http://localhost:80/resim/server/php/files/${ pictureId }/${pictureName}'
								class="img-circle" alt="User Image" width="160" height="160">
								<p>
									<c:out
										value="${ admin.getName() } ${admin.getSurname() } - ${ admin.getJob() }"></c:out>
								</p></li>
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
									<a href='<s:url value="/userProfile"></s:url>'
										class="btn btn-default btn-flat">Profile</a>
								</div>
								<div class="pull-right">
									<a href='<s:url value="/cikisYap"></s:url>'
										class="btn btn-default btn-flat">Sign out</a>
								</div>
							</li>
						</ul></li>
				</ul>
			</div>
		</c:if>
	</nav>
</header>