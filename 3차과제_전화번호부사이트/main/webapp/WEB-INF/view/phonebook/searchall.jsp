<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet" />
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<!-- MDB -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
	rel="stylesheet" />
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}

.form-outline  {
	border-radius: .25rem;
	box-shadow: inset 0 0 0 1px silver;
}

.form-control:focus {
	border-color: #3b71ca;
	box-shadow: inset 0 0 0 1px #3b71ca;
}

.input-group {
	height: 35px;
	width: 300px;
	float: right;
}

.pn_ok {
	color: #008000;
	display: none;
}

.pn_already {
	color: #6A82FB;
	display: none;
}

.nm_ok {
	color: #008000;
	display: none;
}

.nm_already {
	color: #6A82FB;
	display: none;
}

.add_ok {
	color: #008000;
	display: none;
}

.add_already {
	color: #6A82FB;
	display: none;
}
</style>
</head>
<body>
	<header>
		<!-- Navbar -->
		<%@include file="nav.jsp"%>
		<!-- Navbar -->


		<!-- Jumbotron -->
		<form method="get" action="/phonebook/login/searchname">
			<div>
				<table class="table align-middle mb-0 bg-white w-75">
					<thead class="bg-light">
						<tr>
							<th>총 ${listsize}명의 연락처가 있습니다.
								<div class="input-group">
									<div class="form-outline">
										<input id="search-input" type="text" class="form-control"
											placeholder="이름 검색" name="searchnm" required />
									</div>
									<button id="search-button" type="submit"
										class="btn btn-primary">
										<i class="fas fa-search"></i>
									</button>
								</div>
							</th>
						</tr>
					</thead>
				</table>
			</div>
		</form>

		<table class="table align-middle mb-0 bg-white w-75" id="table_id">
			<thead class="bg-light">
				<tr>
					<th>이름</th>
					<th>전화번호</th>
					<th>구분</th>
					<th>주소</th>
					<th>&nbsp;&nbsp;수정</th>
					<th>&nbsp;&nbsp;삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="members" items="${phonebooklist}" varStatus="status">

					<tr>
						<td>
							<div class="d-flex align-items-center">
								<div class="ms-3">
									<p class="fw-bold mb-1">${members.membernm}</p>
									<p class="text-muted mb-0">${members.userid}</p>
								</div>
							</div>
						</td>
						<td>
							<p class="fw-normal mb-1" id="phonenumber">${members.phonenumber}</p>
						</td>
						<td><c:if test="${members.groupnm == '가족'}">
								<span class="badge badge-success rounded-pill d-inline">가족</span>
							</c:if> <c:if test="${members.groupnm == '친구'}">
								<span class="badge badge-primary rounded-pill d-inline">친구</span>
							</c:if> <c:if test="${members.groupnm == '기타'}">
								<span class="badge badge-warning rounded-pill d-inline">기타</span>
							</c:if></td>
						<td>${members.address}</td>
						<td>
							<!-- 수정 버튼 -->
							<button type="button"
								class="btn btn-link btn-rounded btn-sm fw-bold"
								data-mdb-toggle="modal"
								data-mdb-target="#exampleModal${members.phonenumber}"
								data-mdb-ripple-color="dark">
								<i class="fas fa-pen-to-square"></i>
							</button> <!-- Modal -->
							<div class="modal fade" id="exampleModal${members.phonenumber}"
								tabindex="-1" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">${members.membernm}의
												연락처 수정</h5>
											<button type="button" class="btn-close"
												data-mdb-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body mx-4">
											<form method=post
												action="/phonebook/login/update?phonenumber=${members.phonenumber}"
												enctype="multipart/form-data">
												<div class="form-outline mb-4">
													<input type="text" id="membernm" name="membernm"
														class="form-control" oninput="namecheck(membernm.value)"
														placeholder="이름" minlength="2" maxlength="20" required> 
												</div>
												<p class="nm_ok">등록 가능한 이름입니다.</p>
												<p class="nm_already">2글자 이상 20글자 미만으로 입력하세요.</p>

												<div class="form-outline mb-4">
													<input type="text" name="phonenumber"
														value="${members.phonenumber}" id="form3Example1"
														class="form-control" disabled="disabled"> 
												</div>
												<div class="form-outline mb-4">
													<input type="text" id="form3Example3" name="address"
														class="form-control" oninput="addresscheck(address.value)"
														placeholder="주소" minlength="2" required /> 
												</div>
												<p class="add_ok">등록 가능한 주소입니다.</p>
												<p class="add_already">2글자 이상 입력하세요.</p>
												<div class="form-outline mb-4">
													<select class="form-select" name="groupno"
														aria-label="Default select example">
														<option disabled>그룹 선택</option>
														<option selected value="10">가족</option>
														<option value="20">친구</option>
														<option value="30">기타</option>
													</select>
												</div>


												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-mdb-dismiss="modal">취소</button>
													<button type="submit" class="btn btn-primary"
														id="form_submit">저장</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</td>
						<td>
							<!-- 삭제 버튼 --> 
							<a href="/phonebook/login/delete/${members.phonenumber}"><span
								class="btn btn-link btn-rounded btn-sm fw-bold"><i
									class="fas fa-x"></i></span></a> <!-- Modal -->
						</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</header>


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="/js/check.js"></script>
</body>
</html>