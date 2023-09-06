<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="jumbotron text-center d-flex justify-content-center">
		  		<h1>메뉴 상세 페이지</h1>
		  	</div>
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
							<input type="text" value="${vo.mno}" name="mno" readonly="readonly" class="form-control" style="width:95%">
						</div>
						<div style="float:left; width:70%"> 
							<input type="text" value="${vo.name}" name="name" class="form-control" style="width:100%">
						</div>
					</div>
					<div class="form-group">
						<textarea rows="10" class="form-control mb-3">${vo.price}</textarea>

						<input type="text" value="${vo.type}" name="type" readonly="readonly" class="form-control mb-3">
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
					
				</div>
			</div>
		</div>
	</div>
</div>
	
	
<script src="${ctxPath}/resources/js/menu.js"></script>
	
	