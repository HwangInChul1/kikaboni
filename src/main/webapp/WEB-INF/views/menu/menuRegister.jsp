<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container d-flex justify-content-center mt-3">
	<h1>메뉴 등록</h1>
</div>

<div class="container d-flex justify-content-center p-2">
		<div class="jumbotron col-8">
			<div class="row">
				<div class="col-12">
				<form action="${ctxPath}/menu/breadOrder" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<div class="form-group">
						<select class="form-control" name="type">
							<option value="bread">bread</option>
							<option value="cake">cake</option>
							<option value="coffee">coffee</option>
						</select>
					</div>
			  		
					<div>
						<input type="hidden" name="mno" value="${menu.mno}">
					</div>
					<div class="form-group">
	 					<input type="text" name="proId" placeholder="메뉴 id" class="form-control">
	 				</div>
			
					<div class="form-group">
	 					<input type="text" name="name" placeholder="메뉴 이름" class="form-control">
	 				</div>
	 <!-- 
	 				<div class="form-group">
						<textarea rows="5" name="content" placeholder="메뉴 간단 설명" class="form-control" style="resize:none;"></textarea>
					</div>
	 -->			
					<div class="form-group">
						<input type="text" name="price" placeholder="메뉴 가격" class="form-control">
					</div>
					<div class="uploadDiv form-group">
						<input type="file" name="uploadFile" multiple="multiple" class="form-control" style="height:50px;">
					</div>
					<input type="hidden" name="mcount">
					<div class="uploadResultDiv form-group">
						<ul class="list-group">
							
						</ul>
					</div>
					<div class="d-flex justify-content-center mt-3">
						<button class="btn btn-outline-primary regibtn">등록</button>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>


<script src="${ctxPath}/resources/js/upload/menu_upload.js"></script>

