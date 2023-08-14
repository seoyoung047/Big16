<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이트 메인</title>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
	rel="stylesheet" />
	<style type="text/css">
	.search {
    position: relative;
    text-align: center;
    width: 400px;
    margin: 0 auto;
}
input {
    width: 100%;
    border-radius: 20px;
    border: 1px solid #bbb;
    margin: 10px 0;
    padding: 10px 12px;
}
.fa-search {
    position: absolute;
    left: 370px;
    top: 25px;
    margin: 0;
}
.fa-keyboard {
    position: absolute;
    right: 35px;
    top: 20px;
}
.fa-microphone {
    position: absolute;
    right: 15px;
    top: 20px;
    color: blue;
}
	</style>
</head>
<!-- Navbar -->
<%@include file ="nav.jsp" %>
  <!-- Navbar -->

<body>
 <div class="p-5 text-center bg-image"
      style="background-image: url('/images/123456789.jpg'); height: 650px;">
      <div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
         <div class="d-flex justify-content-center align-items-center h-100">
            <div class="text-white">
               <h1 class="mb-3">PHONE BOOK</h1>
               <div class="container">
                  <div class="d-flex justify-content-center">
                     <div class="search">
                        <form action="/phonebook/login/searchname" method=get>
                        <input class="search_input" type="text" name="searchnm" 
                           placeholder="이름 검색">
							<a href="#searchnm" class="search_icon"><i class="fas fa-search"></i>
                           </a>

                           <c:if test="${error != null}">
								<div class="alert alert-danger alert-dismissible fade show mt-3">${error}
									<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
								</div>
							</c:if>
						</form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>


</body>
<!-- Footer -->
<footer class="text-center text-lg-start bg-white text-muted">

  <!-- Section: Links  -->
  <section class="">
    <div class="container text-center text-md-start mt-5">
      <!-- Grid row -->
      <div class="row mt-3">
        <!-- Grid column -->
        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
          <!-- Content -->
          <h6 class="text-uppercase fw-bold mb-4">
            <i class="fas fa-gem me-3 text-secondary"></i>6조
          </h6>
          <p>
           서울특별시 강남구 역삼동 831-3 한국빌딩
          </p>
        </div>

        <!-- Grid column -->
        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
          <!-- Links -->
          <h6 class="text-uppercase fw-bold mb-4">조원</h6>
          <p><i></i> &#127819; &nbsp;&nbsp;&nbsp;&nbsp;김서영</p>
          <p>
            <i></i>
            &#127844; &nbsp;&nbsp;&nbsp;&nbsp;민유진
          </p>
          <p><i></i> 🐷 &nbsp;&nbsp;&nbsp;&nbsp;전원석</p>
          <p><i></i> &#128187; &nbsp;&nbsp;&nbsp;&nbsp;전 찬</p>
        </div>
        <!-- Grid column -->
      </div>
      <!-- Grid row -->
    </div>
  </section>
  <!-- Section: Links  -->

  <!-- Copyright -->
  <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.025);">
    © 2023 Copyright:
    <a class="text-reset fw-bold" href="https://mdbootstrap.com/">phonebook.com</a>
  </div>
  <!— Copyright —>
</footer>


<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
</html>