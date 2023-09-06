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
						<button class="btn btn-inline btn-primary list">목록</button>
						<button class="btn btn-inline btn-info update">수정</button>
					</div>
				</div>
			</div>
		</div>	
	</div>
</div>

<!-- 
<div class="container text-center mt-3">
	<div class="row">
		<div class="col-12"> 
			<input type="hidden">
		</div>
	</div>
</div>
-->

<form>
	<input type="hidden" name="bno" value="${vo.bno}">
</form>



<script>



$(function() {
	
	let form = $('form');
	
	 $('.list').click(function() {
		form.find('[name="bno"]').remove();
		
		form.attr('method','get')
			.attr('action','${ctxPath}/boardkind/CEOtalkList')
			.submit();
	})
	
	
	$('.update').click(function() {
		form.attr('method','get')
			.attr('action','${ctxPath}/boardModify/talkmodify')
			.submit();
	})
	
	
	
})



</script>


