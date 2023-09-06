<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="d-flex justify-content-center mt-5">
	<h1>회원가입</h1>
</div>

<div class="container">
	<div class="w-50 mx-auto"> <!-- class에 w를 50만 주면 반틈만 사용함 -->
		<form:form action="${ctxPath}/member/join" modelAttribute="memberVO">
			
				<div class="d-inline-flex">
					<div class="form-group mr-2" style="width:400px;">
						<form:input path="memberId" class="form-control" placeholder="아이디를 입력해주세요."/>
					</div>
					<div>
						<button type="button" class="btn btn-outline-primary form-control idCheck" style="font-color:white">아이디 중복확인</button>
					</div>
				</div>
			
			<div class="form-group" style="width:400px;">
					<form:password path="memberPwd" class="form-control" placeholder="비밀번호를 입력해주세요."/>
			</div>

			<div class="form-group" style="width:400px;">
				<form:input path="memberName" class="form-control" placeholder="이름을 입력해주세요."/>
			</div>
			
			<div class="form-group" style="width:400px;">
				<form:input path="email" class="form-control" placeholder="이메일을 입력해주세요."/>
			</div>
			
			<div class="form-group" style="width:400px;">
				<form:input path="phoneNumber" class="form-control" placeholder="전화번호를 입력해주세요."/>
			</div>
			
			<div class="form-group" style="width:400px;">
				<form:input path="address" class="form-control" placeholder="주소를 입력해주세요."/>
			</div>
			<div class="d-flex justify-content-center">
				<button type="reset" class="btn btn-outline-primary submit mr-2">취소</button>
				<button type="button" class="btn btn-outline-primary join">가입완료</button>
			</div>
		</form:form>
	</div>
</div>

<script>

let idCheckFlag = false;

$('.idCheck').click(function(){
	let idInput = $('#memberId');
	let memberId = idInput.val();
	
	if(idInput.attr('readonly')){
		idInput.attr('readonly', false);
		idInput.focus();
		$(this).html('ID 중복 확인');
		idCheckFlag = false;
		return;
	}
	
	if(memberId == ''){
		alert('아이디를 입력하세요');
		return;
	}
	
	$.ajax({
		type : 'post',
		url : '${ctxPath}/member/idCheck',
		data : {memberId : memberId},
		success : function(result){
			if(result){	
				alert('사용가능한 아이디 입니다.');
				idCheckFlag = true;
				$('.idCheck').html('변경');
				idInput.attr('readonly',true);
			} else {
				alert('사용할 수 없는 아이디입니다.');
				idInput.focus();
			}
		}
	})
})

$(function(){
	$('.join').click(function(){
		if(!idCheckFlag){
			alert('ID 중복 확인바람');
			return;
		}
		$('#memberVO').submit();
	})
})

</script>






