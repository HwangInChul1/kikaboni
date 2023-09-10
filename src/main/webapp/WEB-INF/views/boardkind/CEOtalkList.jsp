<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container mt-2">
	<div class="text-center">
		<h1>사장님께 한마디~.</h1>
		<button class="btn btn-outline-primary float-right mb-2 register">
			<a href="${ctxPath}/boardRegister/talkregister">글 작성</a>
		</button>
	</div>
	<table class="table table-primary table-striped table-hover table-border text-center">
		<thead>
			<tr>
				<th>게시물 번호</th>
				<th>게시물 제목</th>
				<th>게시물 내용</th>
				<th>작성자</th>
				<th>작성날짜</th>
				<th>수정날짜</th>
			</tr>
		</thead>
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="b">	
				<tbody>
					<tr>
						<td>${b.bno}</td>
						<td><a class="getmove" href="${b.bno}">${b.title}</a></td>
						<td>${b.content}</td>
						<td>${b.writer}</td>
						<td><fmt:formatDate value="${b.writeDate}" pattern="yyyy년 MM월 dd일 HH시 mm분"/></td>
						<td><fmt:formatDate value="${b.updateDate}" pattern="yyyy년 MM월 dd일 HH시 mm분"/></td>
					</tr>
				</tbody>
			</c:forEach>
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
	<form action="${ctxPath}/boardkind/CEOtalkList" method="get" class="pageMove">
		<input type="hidden" name="pageNum" value="${page.criteria.pageNum}"/>
		<input type="hidden" name="amount" value="${page.criteria.amount}"/>
	</form>
	
	
</div>


<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">처리 완료</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        처리가 완료되엇습니다.
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<form class="fbno">
	<input type="hidden" name="pageNum" value="${criteria.pageNum}">
	<input type="hidden" name="amount" value="${criteria.amount}">
</form>
  
<script>

$(function() {	
	
	let form = $('<form/>');
	let result = "${result}"; // result에 담긴 게시물의 번호를 받아옴
	let operation = "${operation}"; // operation을 통해 문자열을 받아와서 변수에 저장
	let pageForm = $('.pageMove');
	
	checkModal(result);
	
	
	// 이 문자열을 매개변수로 줘서, 조건문을 통해 조건문데 따라 모달창을 열어주면 되는데, 내용확인하는 함수 생성
	// 조건을 줘서 각 내용에 따라 modal-body의 내용을 변경, 함수 생성은 function, 변수는 var, let 
	function checkModal(result){
		if(result == '') return; // result값이(게시판 번호) 없으면 함수 종료
		if(operation == 'register'){
			$('.modal-body').html(result + "번을 작성하였습니다.");
		} else if(operation == 'update'){
			$('.modal-body').html(result + "번을 수정하였습니다.");
		} else if(operation == 'delete'){
			$('.modal-body').html(result + "번을 삭제하였습니다.");
		}
		$('.modal').modal('show'); // 모달창 열기
	}

	// 게시 글 작성으로 이동
	$('.register a').click(function(e) {
		e.preventDefault(); // 기본 동작막고
		let regiform = $(this).attr('href'); // 클릭하면 attr을 통해 href의 속성의 값을 가져와서 변수에 주고
		form.attr('method','get') // form 추가한 태그에 get 속성을 주고
			.attr('action',regiform) // 가져온 주소를 aciton의 값으로 추가
			.appendTo('body')
			.submit(); // 전송
	})
	
	// 조회 페이지로 이동
	$('.getmove').click(function(e) {
		e.preventDefault();
		let getValue = $(this).attr('href');
		$('.fbno').find('[name="bno"]').remove(); // 기존 bno input 제거
		$('.fbno').append($('<input/>', {type:'hidden',name:'bno',value:getValue}))
				  .attr('action','${ctxPath}/board/talkList/get')
				  .attr('method','get')
				  .submit();
	})
	
	// 페이지 버튼 클릭
	$('.pagination a').click(function(e) {
		e.preventDefault();
		let pageNum = $(this).attr('href');
		pageForm.find('[name="pageNum"]').val(pageNum);
		pageForm.appendTo('body').submit();
		
	})
	
	

	
	
	
	
})



</script>

