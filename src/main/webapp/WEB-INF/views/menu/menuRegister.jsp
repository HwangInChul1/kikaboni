<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container d-flex justify-content-center mt-3">
	<h1>메뉴 등록</h1>
</div>

<div class="d-flex justify-content-center p-2">
	
	<div class="mr-5">
		<ul class="list-group mt-3" style="width:200px;">
			<li class="list-group-item">
				<a href="${ctxPath}/menu/menuRegister">메뉴등록</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/menu/menuUpdate">메뉴수정/삭제</a>
			</li>
		</ul>
	</div>


	<div class="container" style="position:relative; left:60px;"> 
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
</div>

<script src="${ctxPath}/resources/js/menu_upload.js"></script>

