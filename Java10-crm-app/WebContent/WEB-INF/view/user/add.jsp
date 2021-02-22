<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Thêm mới thành viên</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href='<%= request.getContextPath() %>/static/css/style.css'>
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
                <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse"
                    data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false"
                    aria-label="Toggle navigation"></button>
                <div class="collapse navbar-collapse" id="collapsibleNavId">
                    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="dropdownId"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${ user.fullname }
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownId">
                                <a class="dropdown-item" href="">Thông tin cá nhân</a>
                                <a class="dropdown-item" href="#">Cài đặt</a>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/logout">Thoát</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>

            <!-- CONTENT -->
            <section id="admin-content" class="p-3">
                <h3 class="mb-4">Thêm mới thành viên</h3>
                <form method="post" action="<%= request.getContextPath() %>/user/add">
                    <div class="row">
                    <p>${message }</p>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" name="email" class="form-control" placeholder="Email" />
                            </div>
                             <div class="form-group">
                                <label>User name</label>
                                <input type="text" name="username" class="form-control" placeholder="Username" />
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu</label>
                                <input type="password" name="password" class="form-control" placeholder="Password" />
                            </div>
                             <div class="form-group">
                                <label>Họ tên</label>
                                <input type="text" name="fullname" class="form-control" placeholder="Fullname" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Loại người dùng</label>
                                <select class="form-control" name="roleId">
                                	<c:forEach var="item" items="${roles}">
                                    <option value="${item.id }">${item.description }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="text" name="phone" class="form-control" placeholder="Phone" />
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <input type="text" name="address" class="form-control" placeholder="Address" />
                            </div>
                        </div>
                        <div class="col-12 mt-3">
                            <button type="submit" class="btn btn-success">Lưu lại</button>
                            <a class="btn btn-secondary" href="<%= request.getContextPath() %>/user">Quay lại</a>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
    <script src="<%= request.getContextPath() %>/static/js/jquery.slim.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/static/js/bootstrap.min.js"></script>
</body>

</html>