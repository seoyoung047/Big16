<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
.btn.blue {
	box-shadow: 0px 4px #74a3b0;
}

.btn.blue:active {
	box-shadow: 0 0 #74a3b0;
	background-color: #709CA8;
}

.btn.cyan {
	box-shadow: 0px 4px 0px #73B9C9;
}

.btn.cyan:active {
	box-shadow: 0 0 #73B9C9;
	background-color: #70B4C4;
}
.mainlogo {
	width:120px;

}
</style>
</head>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<!-- Container wrapper -->
	<div class="container-fluid">
		<!-- Toggle button -->
		<button class="navbar-toggler" type="button"
			data-mdb-toggle="collapse" data-mdb-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-bars"></i>
		</button>

		<!-- Collapsible wrapper -->
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<!-- Navbar brand -->
			<a class="navbar-brand mt-2 mt-lg-0" href="/phonebook/beforelogin">
				<img class ="mainlogo"
				src="/images/phonebookmainlogo.png" alt="PHONEBOOK"/>
			</a>
			<!-- Left links -->
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="">연락처 등록</a></li>
				<li class="nav-item"><a class="nav-link" href="">연락처 목록</a></li>
			</ul>
			<!-- Left links -->
		</div>
		<!-- Collapsible wrapper -->

		<!-- Right elements -->
		<span> <a href="/phonebook/signin" class="btn blue">로그인</a> <a
			href="/phonebook/signup" class="btn cyan">회원가입</a>
		</span>


		<!-- Right elements -->
	</div>
	<!-- Container wrapper -->
</nav>
<body>
<div
    class="p-5 text-center bg-image"
    style="
      background-image: url('/images/123456789.jpg');
      height: 650px;">
    <div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
      <div class="d-flex justify-content-center align-items-center h-100">
        <div class="text-white">
          <h1 class="mb-3">이용하시려면 로그인을 해주세요</h1>
          <a class="btn btn-outline-light btn-lg" href="/phonebook/signin" role="button"
          >로그인하기</a
          >
        </div>
      </div>
    </div>
  </div>

</body>
<!-- Footer -->
<footer class="text-center text-lg-start bg-white text-muted">
  <!-- Section: Social media -->
  <!-- Section: Social media -->

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
     <!-- Grid row  --> 
    </div>
  </section>
  <!-- Section: Links   --> 

  <!--  Copyright  -->
  <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.025);">
    © 2023 Copyright:
    <a class="text-reset fw-bold" href="https://mdbootstrap.com/">phonebook.com</a>
  </div>
  <!-- Copyright  --> 
</footer>
<!-- Footer  --> 
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
<script type="text/javascript">
	$('a').click(function(event) {
		event.preventDefault();
	});
</script>
</html>