<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container d-flex justify-content-center mt-3">
	<h1>이벤트 및 공지사항 게시판 내가 쓴 글</h1>
</div>

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
				<a href="${ctxPath}/member/myMenuList">나의 주문 내역</a>
			</li>
		</ul>
	</div>
	<div class="container d-flex justify-content-center mt-3" style="position:relative">
		<div style="position:absolute; left:0px;  width:900px;">
			<table class="table table-primary table-striped table-hover table-border text-center">
				<thead>
					<tr>
						<td>게시판 종류</td>
						<td>게시글 번호</td>
						<td>게시글 제목</td>
						<td>내가 쓴 글</td>						
					</tr>
				</thead>
				<c:if test="${not empty event}">
						<tbody>
					<c:forEach items="${event}" var="e">
							<tr>
								<td>
									<c:forEach items="${e.myTextList}" var="kind">
			                     		 ${kind.board_name}<br/>
			                    	</c:forEach>
								</td>
								<td>${e.bno}</td>
								<td>${e.title}</td>
								<td>${e.content}</td>
							</tr>
					</c:forEach>
						</tbody>
				</c:if>
			</table>
			<ul class="pagination d-flex justify-content-center">
				<c:if test="${page.prev}"> <!-- 이전페이지 활성화 -->
					<li class="page-item">
						<a class="page-link" href="${page.startPage-1}">이전</a>
					</li>
				</c:if>
				<c:forEach begin="${page.startPage}" end="${page.endPage}" var="p"> <!-- 페이지네이션을 반복 --> 
					<li class="page-item ${page.criteria.pageNum == p ? 'active' : '' }">
						<a class="page-link" href="${p}">${p}</a>
					</li>
				</c:forEach>
				<c:if test="${page.next}">
					<li class="page-item">
						<a class="page-link" href="${page.endPage+1}">다음</a>
					</li>
				</c:if>
			</ul>
			<form action="${ctxPath}/member/mytext" method="get" class="pageMove">
				<input type="hidden" name="pageNum" value="${page.criteria.pageNum}"/>
				<input type="hidden" name="amount" value="${page.criteria.amount}"/>
			</form>
		</div>
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


