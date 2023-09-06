<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="d-flex justify-content-center p-2 mt-5">
	<div>
		<ul class="list-group mt-5 mr-5" style="width:250px;">
			<li class="list-group-item">
				<a href="${ctxPath}/order/menuList">들어온 주문</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/order/menuHistory">완료한 주문(주문 내역)</a>
			</li>
		</ul>
	</div>
	
	${vo}
	
	<div class="container d-flex justify-content-center ml-5 mt-5" style="position:relative">
		<table class="table table-bordered text-center table-info" style="position:absolute; left:100px;">
			<thead>
				<tr>
					<td>회원 명</td>
					<td>제품 명</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${name.memberId}</td>
					<td>${vo.name}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="container">
	
	</div>
	
</div>

