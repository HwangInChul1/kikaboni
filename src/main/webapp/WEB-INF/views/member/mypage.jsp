<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="d-flex justify-content-center p-2">
	<div>
		<ul class="list-group mt-3 mr-5" style="width:200px;">
			<li class="list-group-item">
				<a href="${ctxPath}/mypage">회원 정보</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/mypageUpdate">회원정보 변경</a>
			</li>	
			<li class="list-group-item">
				<a href="${ctxPath}/member/mytext">내가 쓴 글</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/order/myMenuList">나의 주문 내역</a>
			</li>
		</ul>
	</div>
	
	<div class="container justify-content-center mt-3">
		<div class="row">
			<div class="col-9">
				<div class="jumbotron ml-5">
					<ul class="list-group mb-3 d-flex justify-content-center">
						<li class="list-group-item" style="text-align:center; border:1px solid;">
							<div>내 정보</div>
						</li>
					</ul>
					<ul class="list-group list-group-horizontal mb-3">
						<li class="list-group-item mr-3" style="width:200px; border:1px solid;">
							<div>이름</div>
						</li>
						<li class="list-group-item" style="width:600px; border:1px solid;">
							<div>${vo.memberName}</div>
						</li>
					</ul>
					<ul class="list-group list-group-horizontal mb-3">
						<li class="list-group-item mr-3" style="width:200px; border:1px solid;">
							<div>아이디</div>
						</li>
						<li class="list-group-item" style="width:600px; border:1px solid;">
							<div>${vo.memberId}</div>
						</li>
					</ul>
					<ul class="list-group list-group-horizontal mb-3">
						<li class="list-group-item mr-3" style="width:200px; border:1px solid;">
							<div>이메일</div>
						</li>
						<li class="list-group-item" style="width:600px; border:1px solid;">
							<div>${vo.email}</div>
						</li>
					</ul>
					<ul class="list-group list-group-horizontal mb-3">
						<li class="list-group-item mr-3" style="width:200px; border:1px solid;">
							<div>전화번호</div>
						</li>
						<li class="list-group-item" style="width:600px; border:1px solid;">
							<div>${vo.phoneNumber}</div>
						</li>
					</ul>
					<ul class="list-group list-group-horizontal mb-3">
						<li class="list-group-item mr-3" style="width:200px; border:1px solid;">
							<div>주소</div>
						</li>
						<li class="list-group-item" style="width:600px; border:1px solid;">
							<div>${vo.address}</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


<script>

let result = "${result}";

$(function(){
	if(result == 'modify'){
		alert('회원정보를 수정하였습니다.')
	}
})

</script>




