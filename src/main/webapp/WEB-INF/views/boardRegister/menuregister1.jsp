<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

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
					<h1>자유 게시판</h1>
				</div>
				<div class="card-body">
					<div class="form-group">			
						<input type="text" placeholder="제목" name="title" class="form-control mb-3">
						<textarea rows="10" class="form-control mb-3" placeholder="글 내용"></textarea>
						<input type="text" placeholder="작성자" name="writer" class="form-control mb-3">
					<div class="text-right">
						<button class="btn btn-inline btn-primary register">작성</button>
					</div>
				</div>
				
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

let form = $('<form/>');
$('.register').click(function() {
	form.attr('method','post')
		.attr('action','${ctxPath}/boardkind/breadGootList')
		.submit();
})

</script>