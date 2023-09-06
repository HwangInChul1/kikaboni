<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container d-flex justify-content-center mt-5">
	<h1>로그인</h1>
</div>


<div class="container d-flex justify-content-center mt-2">
	<div class="row" style="width:350px;">
		<div class="col-12">
			<form action="${ctxPath}/member/login" method="post">			
				<div class="form-group">
					<input type="text" name="memberId" class="form-control mb-3" placeholder="아이디를 입력해주세요">
				</div>
				<div class="form-group">
					<input type="text" name="memberPwd" class="form-control" placeholder="비밀번호를 입력해주세요">
				</div>
				<c:if test="${not empty loginFail}">
					<p style="color:red; font-size:10px; ">${loginFail}</p>
				</c:if>
				<label>
					<input type="checkbox" name="remember-me" class="mr-2">자동 로그인
				</label>
				
				<button class="btn btn-outline-primary float-right">로그인</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			</form>
		</div>
	</div>
</div>




