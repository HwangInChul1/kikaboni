<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="jumbotron text-center d-flex justify-content-center">
		  		<h1>게시판 상세 페이지</h1>
		  	</div>
	  	</div>
  	</div>
	<input type="hidden" value="${vo.board_id}" name="id">
	<div class="row">
		<div class="col-12">
			<div class="card"> 
				<div class="card-header">
					<h1>자유 게시판</h1>
				</div>
				<div class="card-body">
					<div class="form-group mb-5" style="height:auto; width:100%">
						<div style="float:left; width:30%;">
							<input type="text" value="${vo.bno}" name="bno" readonly="readonly" class="form-control" style="width:95%">
						</div>
						<div style="float:left; width:70%"> 
							<input type="text" value="${vo.title}" name="title" class="form-control" style="width:100%">
						</div>
					</div>
					<div class="form-group">
						<textarea rows="10" class="form-control mb-3">${vo.content}</textarea>

						<input type="text" value="${vo.writer}" name="writer" readonly="readonly" class="form-control mb-3">

						<input type="text" value="<fmt:formatDate value="${vo.writeDate}" pattern="yyyy년MM월dd일 HH시mm분"/>" name="writeDate" readonly="readonly" class="form-control mb-3">
						
						<input type="text" value="<fmt:formatDate value="${vo.updateDate}" pattern="yyyy년MM월dd일 HH시mm분" />" name="updateDate" readonly="readonly" class="form-control mb-3">
					</div>
					<div class="text-right">
						<button data-oper="list" class="btn btn-inline btn-primary list">목록</button>
						<sec:authorize access="isAuthenticated() and #authInfo.memberId == #vo.writer or hasRole('ROLE_ADMIN')">
							<button data-oper="update" class="btn btn-inline btn-info update">수정</button>
						</sec:authorize>
					</div>
				</div>
			</div>
		</div>	
	</div>
	
	<div class="row my-5">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h4>첨부파일</h4>
				</div>
				<div class="card-body">
					<div class="uploadResultDiv mt-3">
						<ul class="list-group"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<div class="container">
<h3 class="mt-5">댓글</h3>
	<div class="row">
		<div class="col-12">
			<ul class="list-group chat">
				
			</ul>		
		</div>
	</div>
	

	
</div>

<div class="d-flex justify-content-center">
	<div class="row mt-3">
		<div class="col-12 pagination_wrap"></div>
	</div>
</div>

<div class="container mt-3 mb-3 replyWriteForm">
	<div class="row">
		<div class="col-12">
			<div class="card">
			  <div class="card-header">댓글 작성</div>
			  <div class="card-body form-group">
			  	<sec:authorize access="isAnonymous()">
					<textarea rows="3" class="form-control mb-3 replyContent" style="resize:none;" 
						placeholder="로그인한 사용자만 댓글을 작성할 수 있습니다."></textarea>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<textarea rows="3" class="form-control mb-3 replyContent" style="resize:none;" 
						placeholder="댓글을 작성해주세요."></textarea>
			  <div class="submit d-flex justify-content-end">
			  	<span class="btn btn-outline-info col-2 replyer float-left mr-2">${authInfo.memberId}</span>
			  	<button class="replyBtn form-control btn btn-outline-primary float-left" style="width:100px;">등록</button>
			  </div>
			  	</sec:authorize>
			  </div>
			</div>
		</div>
	</div>
</div>

<form>
	<input type="hidden" name="bno" id="bno" value="${vo.bno}">	
	<input type="hidden" name="board_id" id="board_id" value="${vo.board_id}">	
</form>

<!-- 원본 이미지 모달창 -->
<div class="modal fade" id="showImage">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
	        <div class="modal-header">
	            <h4 class="modal-title">원본 이미지 보기</h4>
	            <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        <div class="modal-body"></div>
        </div>
    </div>
</div>


<script>

$(function() {
	let id = $('input[name="id"]').val();
	console.log(id);
	
	let form = $('form');
	
	function pageList(){
		form.append($('<input/>',{type:'hidden',name:"pageNum",value:'${criteria.pageNum}'}))
			.append($('<input/>',{type:'hidden',name:"amount",value:'${criteria.amount}'}))
	}
	
	$('button').click(function() {

		let operation = $(this).data('oper');
		
		if(operation == 'list'){
			console.log('같음');
			form.find('[name="bno"]').remove();
			pageList();
			form.attr('method','get')
				.attr('action','${ctxPath}/boardkind/breadGoodList');
		} else if(operation == 'update'){
			pageList();
			form.attr('action','${ctxPath}/boardModify/goodbreadmodify');
		}
		form.submit();
	})
})

</script>

<script src="${ctxPath}/resources/js/board/goodget.js"></script>
<script src="${ctxPath}/resources/js/reply/goodReplyService.js"></script>
<script src="${ctxPath}/resources/js/reply/goodReply.js"></script>



