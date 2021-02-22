<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Cập nhật quyền mới</title>
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
				<li><a href="<%=request.getContextPath()%>/task"> <i
						class="fa fa-cogs mr-2"></i> Quản lý dự án
				</a></li>
				<li><a href="<%=request.getContextPath()%>/taskCategory"> <i
						class="fa fa-slack mr-2"></i> Quản lý công việc
				</a></li>
				<li><a href="<%=request.getContextPath()%>/status"> <i
						class="fa fa-slack mr-2"></i> Trạng thái công việc
				</a></li>
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
									class="dropdown-item"
									href="<%=request.getContextPath()%>/logout">Thoát</a>
							</div></li>
					</ul>
				</div>
			</nav>

			<!-- CONTENT -->
			<section id="admin-content" class="p-3">
				<h3 class="mb-4 text-center">Cập nhật công việc</h3>
				<p class="text-danger text-center">${ message }</p>
				<form method="post"
					action="<%=request.getContextPath()%>/taskCategory/edit">
					<div class="row">
						<div class="col-md-6 m-auto">
							<input name="id" type="hidden" value="${TaskCategory.id }" />


							<c:choose>
								<c:when test="${user.roleId == 3}">
									<div class="form-group">
										<label>Tên công việc</label> <input type="text"
											readonly="readonly" name="name" value="${TaskCategory.name}"
											class="form-control" placeholder="Nhập tên" />
									</div>
									<div class="form-group">
										<label>Ngày bắt đầu</label> <input type="text"
											readonly="readonly" name="startDate"
											value="${TaskCategory.startDate}" class="form-control"
											placeholder="Nhập ngày bắt đầu" />
									</div>
									<div class="form-group">
										<label>Ngày kết thúc</label> <input type="text" name="endDate"
											readonly="readonly" value="${TaskCategory.endDate}"
											class="form-control" placeholder="Nhập ngày kết thúc" />
									</div>
									<div class="form-group">
										<label>Tên người phụ trách</label>
										<c:forEach items="${listUserEdit }" var="item">
											<c:choose>
												<c:when test="${TaskCategory.userID == item.id }">
													<input type="text" readonly="readonly" hidden="true"
														name="userID" value="${item.id}" class="form-control" />
													<input type="text" readonly="readonly" name="userEdit"
														value="${item.fullname}" class="form-control" />
												</c:when>
											</c:choose>
										</c:forEach>
									</div>
									<div class="form-group">
										<label>Tên dự án</label>
										<c:forEach items="${listTaskEdit }" var="item">
											<c:choose>
												<c:when test="${item.id == TaskCategory.taskID }">
													<input type="text" readonly="readonly" hidden="true"
														name="taskID" value="${item.id}" class="form-control" />
													<input type="text" readonly="readonly" name="taskEdit"
														value="${item.name}" class="form-control" />
													<%-- <option value="${item.id }" selected>${item.name }</option> --%>
												</c:when>
												<%-- <c:otherwise>
														<option value="${item.id}">${item.name}</option>
													</c:otherwise> --%>
											</c:choose>
										</c:forEach>
									</div>
								</c:when>
								<c:otherwise>
									<div class="form-group">
										<label>Tên công việc</label> <input type="text" name="name"
											value="${TaskCategory.name}" class="form-control"
											placeholder="Nhập tên" />
									</div>
									<div class="form-group">
										<label>Ngày bắt đầu</label> <input type="text"
											name="startDate" value="${TaskCategory.startDate}"
											class="form-control" placeholder="Nhập ngày bắt đầu" />
									</div>
									<div class="form-group">
										<label>Ngày kết thúc</label> <input type="text" name="endDate"
											value="${TaskCategory.endDate}" class="form-control"
											placeholder="Nhập ngày kết thúc" />
									</div>
									<div class="form-group">
										<label>Tên người phụ trách</label> <select name="userID"
											class="form-control">
											<c:forEach items="${listUserEdit }" var="item">
												<c:choose>
													<c:when test="${TaskCategory.userID == item.id }">
														<option value="${item.id }" selected>${item.fullname }</option>
													</c:when>
													<c:otherwise>
														<option value="${item.id}">${item.fullname}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label>Tên dự án</label> <select name="taskID"
											class="form-control">
											<c:forEach items="${listTaskEdit }" var="item">
												<c:choose>
													<c:when test="${item.id == TaskCategory.taskID }">
														<option value="${item.id }" selected>${item.name }</option>
													</c:when>
													<c:otherwise>
														<option value="${item.id}">${item.name}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="form-group">
								<label>Tên trạng thái</label> <select name="statusID"
									class="form-control" data-placeholder="Chọn trạng thái">
									<c:forEach items="${listStatusEdit }" var="item">
										<c:choose>
											<c:when test="${item.id == TaskCategory.statusID }">
												<option value="${item.id }" selected>${item.name }</option>
											</c:when>
											<c:otherwise>
												<option value="${item.id}">${item.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success">Cập nhật</button>
								<a class="btn btn-secondary"
									href="<%=request.getContextPath()%>/taskCategory">Quay lại</a>
							</div>
						</div>
					</div>
				</form>
			</section>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/static/js/jquery.slim.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
</body>

</html>