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
					<h3 style="text-align:center;">회원정보 변경</h3>
				</div>
				<form action="${ctxPath}/member/modify" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<div class="form-group ml-5">
						<input type="text" class="form-control" name="memberName" value="${vo.memberName}" readonly="readonly">
					</div>
					<div class="form-group ml-5">
						<input type="text" class="form-control" name="memberId" value="${vo.memberId}" readonly="readonly">
					</div>
					<div class="form-group ml-5">
						<input type="text" class="form-control" name="email" value="${vo.email}">
					</div>
					<div class="form-group ml-5">
						<input type="text" class="form-control" name="phoneNumber" value="${vo.phoneNumber}">
					</div>
					<div class="form-group ml-5">
						<input type="text" class="form-control" name="address" value="${vo.address}">
					</div>
					<div class="form-group ml-5">
						<button class="btn btn-outline-primary form-control">회원 수정</button>
					</div>
					<div class="form-group ml-5">
						<button type="button" class="btn btn-outline-info form-control changePwdForm">비밀번호 변경</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<!-- 비밀번호 변경 창 -->
<div class="modal" id="changePwdModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">비밀번호 변경</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
       	<form>
       		<input type="text" autocomplete="username" style="display:none;"/>
       		<div class="form-group"> 
       			현재 비밀번호 : <input type="password" class="form-control currentPwd" autocomplete="new-password">
       		</div>
       		<div class="form-group"> 
       			변경할 비밀번호 : <input type="password" class="form-control newPwd" autocomplete="new-password">
       		</div>
       	</form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-outline-info showPwd">비밀번호 보기</button>
        <button class="btn btn-outline-danger changePwd">변경</button>
      </div>

    </div>
  </div>
</div>


<script>

$(function(){
	
	$('.changePwdForm').click(function(){
		$('#changePwdModal').find('input').val('');
		$('#changePwdModal').modal('show');
	})
	
$('.changePwd').click(function(){
	
		var currentPwd = $('.currentPwd').val();
		var newPwd = $('.newPwd').val();
		
		if(currentPwd === '' || newPwd === '' ){
			alert('비밀번호를 입력해주세요');
			return;
		}
	
		$.ajax({
			type : 'post',
			url : '${ctxPath}/mypage/changePwd',
			data : {  		// 데이터를 보내야 하는데 객체로 보내야 한다.
				memberId : $('[name="memberId"]').val(),
				currentPwd : currentPwd,	// 모달창에 있는 값을 보내줘야 한다.
				newPwd: newPwd
			},
			success : function(result){
				alert(result)
				$('#changePwdModal').modal('hide'); // modal속성에 'hide'를 주면 모달창 닫김
			},
			error : function(xhr, status, er){
				alert(xhr.responseText) // 비밀번호 틀린경우 에러 남
				$('#changePwdModal').find('input').val('');
			}
		});
		
	})
	

})

</script>





