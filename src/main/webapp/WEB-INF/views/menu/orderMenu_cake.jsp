<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>


<div class="container d-flex justify-content-center">
	<h1>메뉴 / 주문하기</h1>
</div>

<div class="float-left mr-3">
	<div class="container">
		<div class="row float-left">
			<div class="col-12 float-left">
				<ul class="list-group mt-5 float-left" style="width:200px;">
					<li class="list-group-item">
						<a href="${ctxPath}/menu/orderMenu_bread">빵</a>
					</li>
					<li class="list-group-item">
						<a href="${ctxPath}/menu/orderMenu_cake">케이크</a>
					</li>
					<li class="list-group-item">
						<a href="${ctxPath}/menu/orderMenu_coffee">커피</a>
					</li>
				</ul>	
			</div>
		</div>
	</div>
</div>