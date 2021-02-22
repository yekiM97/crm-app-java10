<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/static/css/style.css'>
</head>

<body>

	<div class="d-flex justify-content-between">
		<!-- SIDE BAR -->
		<div id="side-bar">
			<div class="logo">ADMIN PAGE</div>
			<ul class="list-group rounded-0">
				<li class="dashboard">DASHBOARD</li>
				<li><a href="<%=request.getContextPath()%>/user"> <i
						class="fa fa-user mr-2"></i> Quản lý thành viên
				</a></li>
				<li><a href="<%=request.getContextPath()%>/role"> <i
						class="fa fa-book mr-2"></i> Quản lý quyền
				</a></li>
				 <li>
                    <a href="<%= request.getContextPath() %>/task">
                        <i class="fa fa-cogs mr-2"></i> Quản lý dự án
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/taskCategory">
                        <i class="fa fa-slack mr-2"></i> Quản lý công việc
                    </a>
                </li>
                 <li>
                    <a href="<%= request.getContextPath() %>/status">
                        <i class="fa fa-slack mr-2"></i> Trạng thái công việc
                    </a>
                </li>
			</ul>
		</div>

		<div id="admin-wrapper">
			<!-- HEADER -->
			<nav class="navbar navbar-expand-sm navbar-light bg-light w-100">
				<a class="navbar-brand" href="#"><i class="fa fa-align-justify"></i></a>
				<button class="navbar-toggler d-lg-none" type="button"
					data-toggle="collapse" data-target="#collapsibleNavId"
					aria-controls="collapsibleNavId" aria-expanded="false"
					aria-label="Toggle navigation"></button>
				<div class="collapse navbar-collapse" id="collapsibleNavId">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdownId"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								${ user.fullname } </a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="dropdownId">
								<a class="dropdown-item" href="">Thông tin cá nhân</a> <a
									class="dropdown-item" href="#">Cài đặt</a> <a
									class="dropdown-item" href="<%= request.getContextPath() %>/logout">Thoát</a>
							</div></li>
					</ul>
				</div>
			</nav>

			<!-- CONTENT -->
			<section id="admin-content" class="p-3">
				<h3 class="mb-3">Danh sách dự án</h3>
				<div class="row">
					<div class="col-md-8">
						<a href="<%=request.getContextPath()%>/task/add"
							class="btn btn-primary">Thêm mới</a>
							<p class="text-danger">${ message }</p>
					</div>
					<div class="col-md-4">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Tìm kiếm...">
							<div class="input-group-append">
								<span class="input-group-text" id="basic-addon2"><i
									class="fa fa-search"></i></span>
							</div>
						</div>
					</div>
				</div>
				<table class="table table-bordered table-hover mt-3">
					<thead>
						<tr>
							<th>STT</th>
							<th>Tên dự án</th>
							<th>Ngày bắt đầu</th>
							<th>Ngày kết thúc</th>
							<th>Mã người tạo</th>
							<th>#</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="task" items="${task}" varStatus="index">

							<tr>
								<td>${index.count}</td>
								<td>${task.name}</td>
								<td>${task.startDate}</td>
								<td>${task.endDate}</td>
								<td>${task.createUserID}</td>
								<td><a
									href="<%= request.getContextPath() %>/task/edit?id=${task.id}"
									class="btn btn-sm btn-info"> <i
										class="fa fa-pencil-square-o"></i>Edit
								</a> <a
									href="<%= request.getContextPath() %>/task/delete?id=${task.id}"
									class="btn btn-sm btn-danger"> <i class="fa fa-trash-o"></i>Delete
								</a></td>
							</tr>
						</c:forEach> 

					</tbody>
				</table>
			</section>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/static/js/jquery.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
</body>



</html>