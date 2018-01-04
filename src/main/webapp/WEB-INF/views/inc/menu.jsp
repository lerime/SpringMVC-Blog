
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<aside class="main-sidebar well">
	<!-- sidebar: style can be found in sidebar.less -->
	<c:if test="${not empty kulid }">
	<section class="sidebar">
	</c:if>
	<c:if test="${ empty kulid }">
	<section class="sidebar well">
	</c:if>
	
		<c:if test="${not empty kulid }">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img
						src='http://localhost:80/resim/server/php/files/${ pictureId }/${ pictureName}'
						class="img-circle" alt="User Image" width="160" height="160">
				</div>
				<div class="pull-left info ">
					<p>
						<c:out value="${ admin.getName() } ${admin.getSurname() }"></c:out>
					</p>

				</div>
			</div>

			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu" data-widget="tree">

				<li class=""><a href='<s:url value="/yaziEkle"></s:url>'> <i
						class="fa fa-edit"></i> <span>Yeni Yazı</span> <span
						class="pull-right-container"> <i class="fa fa-angle"></i>
					</span>
				</a></li>
				<li class="treeview menu"><a href=""> <i class="fa fa-book"></i>
						<span>Tüm Yazılar</span> <span class="pull-right-container">
							<i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href='<s:url value="/yonetim/yazilar/${-1}"></s:url>'><i
								class="fa fa-circle-o"></i>Tümünü Göster</a></li>
						<c:if test="${ not empty categoryList }">
							<c:forEach items="${categoryList }" var="item">

								<input type="hidden" name="categoryId"
									value='<c:out value="${ item.getId() }"></c:out>' />

								<li><a
									href='<s:url value="/yonetim/yazilar/${ item.getId() }"></s:url>'><i
										class="fa fa-circle-o"></i> <c:out value="${ item.getName() }"></c:out></a></li>
							</c:forEach>
						</c:if>
					</ul></li>
				<li class=""><a href='<s:url value="/kategori"></s:url>'> <i
						class="fa fa-list"></i> <span>Kategoriler</span> <span
						class="pull-right-container"> <i class="fa fa-angle"></i>
					</span>
				</a></li>
				<li class="treeview menu"><a href=""> <i class="fa fa-user"
						aria-hidden="true"></i> <span>Admin İşlemleri</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href='<s:url value="/adminGuncelle"></s:url>'><i
								class="fa fa-circle-o"></i>Düzenle</a></li>
						<li><a href='<s:url value="/adminEkle"></s:url>'><i
								class="fa fa-circle-o"></i>Yeni Admin Ekle</a></li>
					</ul></li>
			</ul>
		</c:if>
		<c:if test="${ empty kulid }">
			<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">About Me</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">

								<strong><i class="fa fa-file-text-o margin-r-5"></i>
									Notes</strong>
								<p class="text-muted">
									Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune kitabı oluşturmak üzere bir yazı galerisini alarak karıştırdığı 1500'lerden beri endüstri standardı sahte metinler olarak kullanılmıştır. Beşyüz yıl boyunca varlığını sürdürmekle kalmamış, aynı zamanda pek değişmeden elektronik dizgiye de sıçramıştır. 1960'larda Lorem Ipsum pasajları da içeren Letraset yapraklarının yayınlanması ile ve yakın zamanda Aldus PageMaker gibi Lorem Ipsum sürümleri içeren masaüstü yayıncılık yazılımları ile popüler olmuştur.
								</p>

							</div>
							<!-- /.box-body -->
						</div>

		</c:if>
	</section>
	<!-- /.sidebar -->
</aside>