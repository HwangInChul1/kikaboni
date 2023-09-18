<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container d-flex justify-content-center mt-3">
	<h1>내가 쓴 글</h1>
</div>

<div class="d-flex justify-content-center p-2">
	<div>
		<ul class="list-group mt-3" style="width:200px;">
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
				<a href="${ctxPath}/member/myMenuList">나의 주문 내역</a>
			</li>
		</ul>
	</div>
	
	<div class="container">
				<ul class="list-group texts list-group-horizontal justify-content-center mt-3 mr-3 col-10">
			<li class="list-group-item text">
				<a href="${ctxPath}/member/myCommendtext">추천게시판</a>
			</li>
			<li class="list-group-item text">
				<a href="${ctxPath}/member/myMenutext">메뉴건의 게시판</a>
			</li>	
			<li class="list-group-item text">
				<a href="${ctxPath}/member/myTalktext">사장님께 한 마디 게시판</a>
			</li>
			<li class="list-group-item text">
				<a href="${ctxPath}/member/myEventtext">이벤트/공지사항 게시판</a>
			</li>
		</ul>
	</div>
	
	
	
	
	
</div>



<script>

$(function(){
	
	let pageForm = $('.pageMove');
	
	// 페이지 버튼 클릭
	$('.pagination a').click(function(e) {
		e.preventDefault();
		let pageNum = $(this).attr('href');
		pageForm.find('[name="pageNum"]').val(pageNum);
		pageForm.appendTo('body').submit();
		
	})
	
})

</script>


<style>

.text{
	
	width : 25%;
	text-align : center;

}

.texts{

	
	

}


</style>



