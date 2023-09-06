<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="jumbotron text-center d-flex justify-content-center">
		  		<h1>게시판 상세 페이지</h1>
		  	</div>
	  	</div> <!-- col end -->
  	</div> <!-- row end -->
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h1>자유게시판</h1>
				</div>
				<div class="card-body">
					<form action="${ctxPath}/boardRegister/goodregister" method="post">	
						<input type="hidden" name="memberId" value="${authInfo.memberId}">
						<input type="hidden" name="board_id" value="1">
						<div class="form-group">
							<input class="form-control" name="title" placeholder="제목"/>
						</div>
						<div class="form-group">
							<textarea class="form-control" rows="10" name="content" placeholder="내용"></textarea>
						</div>
						<div class="form-group">
							<input class="form-control" name="writer" value="${authInfo.memberId}" readonly="readonly"/>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<div class="uploadDiv form-group">
							<input type="file" name="uploadFile" multiple="multiple" class="form-control">
						</div>
						<div class="uploadResultDiv form-group">
							<ul class="list-group">
						
							</ul>
						</div>
						<div class="d-flex float-right">
							<button type="button" class="btn btn-outline-primary register mr-2">작성</button>					
							<button type="button" class="btn btn-outline-primary list">목록</button>
						</div>					
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container text-center mt-3">
	<div class="row">
		<div class="col-12"> 
			<input type="hidden">
		</div>
	</div>
</div>


<script>

$(function() {

	let form = $('form');
	
	$('.register').click(function() {
		form.attr('method','post')
			.attr('action','${ctxPath}/boardRegister/goodregister')
			.appendTo('body')
			.submit();
	})
	
	
	$('.list').click(function() {
		console.log('aaa');	
		form.find('[name="title"],[name="content"],[name="writer"]').remove();
		
		form.attr('method','get')
			.attr('action','${ctxPath}/boardkind/breadGoodList')
			.submit();
	})

})



</script>
<script src="${ctxPath}/resources/js/board_upload.js"></script>
