<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>

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
.a {
	float: left;
}

.form-image {
	position: static;
	right: 560px;
	top : 95px;
}
.id_ok{
color:#008000;
display: none;
}

.id_already{
color:#6A82FB; 
display: none;
}



</style>
</head>
<body>
	<!-- Section: Design Block -->
	<section class="text-center">
		<!-- Background image -->
		<div class="p-5 bg-image"
			style="background-image: url('/images/111122223333.jpg'); height: 300px;"></div>
		<!-- Background image -->

		<div class="card mx-4 mx-md-5 shadow-5-strong"
			style="margin-top: -100px; background: hsla(0, 0%, 100%, 0.8); backdrop-filter: blur(30px);">
			<div class="card-body py-5 px-md-5">

				<div class="row d-flex justify-content-center">
					<div class="col-lg-8">
						<h2 class="fw-bold mb-5">회원 가입</h2>
						<form method="post" action="/phonebook/up" enctype="multipart/form-data">
							<div class="row d-flex justift-left-left mx-1" >프로필 이미지
		                     <div class="form-outline mb-4">
		                        <input type="file" name="file" class="form-control">
		                     </div>
							</div>
							<div class="form-outline mb-4">
								<input type="text" name="username" id="username"
									class="form-control" required /> <label class="form-label"
									for="form3Example1">이름</label>
							</div>
							<!-- Email input -->

							<div class="form-outline mb-4">
								<input type="text" name="userid" id="userid"
									class="form-control" oninput="checkId(userid.value)" required /><label class="form-label"
									for="form3Example3">아이디</label>
							</div>
							<p class="id_ok">사용 가능한 아이디입니다.</p> 
							<p class="id_already">*이 아이디는 이미 사용중 입니다.</p>
							<!-- Password input -->
							<div class="form-outline mb-4">
								<input type="password" name="userpassword" id="password"
									class="form-control" required /> <label class="form-label"
									for="form3Example4">비밀번호</label>
							</div>
							<div class="a">
								<label class="form-check-label mb-4 " for="show-password">비밀번호
									표시:</label> <input class="form-check-input me-2" type="checkbox"
									id="show-password" onchange="showPassword()">
							</div>

							<!-- Submit button -->
							<button type="submit" class="btn btn-primary btn-block mb-4">가입하기</button>
							<p>
								이미 계정이 있으십니까? <a href="/phonebook/signin">로그인하기</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
	<script type="text/javascript">
		<!--비밀번호 보이게하기-->
		function showPassword() {
			var passwordInput = document.getElementById("password");
			var showPasswordCheckbox = document.getElementById("show-password");
			if (showPasswordCheckbox.checked) {
				passwordInput.type = "text";
			} else {
				passwordInput.type = "password";
			}
		}
	</script>
	<script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
  </script>
 <!--  id 확인 -->
   <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script>
    function checkId(userid){
      $.ajax({
          url:'/phonebook/idcheck',
          type:'post',
          data:{userid: userid},
          success:function(check){
              if(check == 0){
                  $('.id_ok').css("display","inline-block"); 
                  $('.id_already').css("display", "none");
              } else {
                  $('.id_already').css("display","inline-block");
                  $('.id_ok').css("display", "none");
                  $('userid').val('');
              }
          },
          error:function(){
              alert("에러입니다");
          }
      });
      };
  </script>
</body>
</html>