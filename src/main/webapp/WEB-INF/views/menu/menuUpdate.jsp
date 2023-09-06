<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container d-flex justify-content-center mt-3">
	<h1>메뉴 수정/삭제</h1>
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
	
	<div class="container">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</div>
	
	
</div>