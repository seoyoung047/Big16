<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.rounded-circle{
    width: 35px;
    height:35px;
    border-radius: 50px;
} 
.mainlogo {
	width:120px;

}
.username {
float: right;
margin-right: 20px;
}
</style>
</head>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <!-- Container wrapper -->
  <div class="container-fluid">
    <!-- Toggle button -->
    <button
      class="navbar-toggler"
      type="button"
      data-mdb-toggle="collapse"
      data-mdb-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

    <!-- Collapsible wrapper -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!-- Navbar brand -->
      <a class="navbar-brand mt-2 mt-lg-0"  href="/phonebook/login/afterloginmain">
       <img class="mainlogo" src="/images/phonebookmainlogo.png" alt = "PHONEBOOK"/>
      </a>
      <!-- Left links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="/phonebook/login/addmember">연락처 등록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/phonebook/login/searchall">연락처 목록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"></a>
        </li>
      </ul>
      <!-- Left links -->
    </div>
   <c:if test="${username != null}">
	<div class = "username">${username}님  환영합니다</div>
    <!-- Collapsible wrapper -->
    <!-- Right elements -->
    <div class="d-flex align-items-center">
      <!-- Avatar -->
      <div class="dropdown">
        <a
          class="dropdown-toggle d-flex align-items-center hidden-arrow"
          href="#"
          id="navbarDropdownMenuAvatar"
          role="button"
          data-mdb-toggle="dropdown"
          aria-expanded="false"
        >
          <img
            src="${image}"
            class="rounded-circle"
            height="25"
            alt="프로필 사진"
            loading="lazy"
          />
        </a>
        <ul
          class="dropdown-menu dropdown-menu-end"
          aria-labelledby="navbarDropdownMenuAvatar"
        >
        <li><a class="dropdown-item">${username}</a></li>
          <li>
            <a class="dropdown-item" href="/phonebook/lFamilySizeogout">로그아웃</a>
          </li>
        </ul>
      </div>
    </div>
    <!--  Right elements-->  
 </c:if>
  </div>
  <!-- Container wrapper -->  
</nav>
<body>

</body>
</html>
