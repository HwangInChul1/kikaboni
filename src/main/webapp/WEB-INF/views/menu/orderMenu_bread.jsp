<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>


<div class="container d-flex justify-content-center mt-3">
	<h1>메뉴 / 주문하기</h1>
</div>


<div class="d-flex justify-content-center p-2 mt-3">
	<div>
		<ul class="list-group mt-3 mr-5" style="width:200px;">
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

	<div class="container justify-content-center mt-3">
	<form action="${ctxPath}/order/myOrder" method="post" class="menuForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<table class="table table-hover table-bordered text-center" style="width:900px;">
			<thead>
				<tr>
					<th>선택</th>
					<th>메뉴</th>
					<th>이름</th>
					<th>가격</th>
					<th>수량</th>
				</tr>
			</thead>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="menu">	
					<tbody>
						<tr>
							<td>
								<input type="checkbox" name="selectMenus" class="check">	
								<input type="hidden" name="type" value="cake"> 
								<input type="hidden" name="mno" value="${menu.mno}">
								<input type="hidden" name="proId" value="${menu.proId}">
								<input type="hidden" name="memberId" value="${authInfo.memberId}">
								
							</td>
							<td>
								<div class="uploadResultDiv form-group">
									<c:forEach items="${menu.attachList}" var="attach">
										<img src="${ctxPath}/files/display?fileName=${attach.uploadPath}/${attach.uuid}_${attach.fileName}" 
										class="menu-image" style="width:120px; height:100px;">
									</c:forEach>
								</div>
							</td>
							<td>
								<input type="hidden" name="name" value="${menu.name}">
								${menu.name}
							</td>
							<td>
								<input type="hidden" name="price" value="${menu.price}">
								${menu.price}
							</td>
							<td><input type="text" name="proCount" id="proCount" class="proCount" style="width:100%;" placeholder="수량을 입력하세요" ></td>
						</tr>
					</tbody>
				</c:forEach>
			</c:if>
		</table>
			<div style="position:absolute; right:420px; top:220px;">
				<button type="reset" class="btn btn-outline-primary">취소</button>
				<button type="button" class="btn btn-outline-primary order">주문하기</button>
			</div>
		</form>
	</div>
</div>


<div class="d-flex justify-content-center">
		<ul class="pagination d-flex justify-content-center">
			<c:if test="${page.prev}"> <!-- 이전페이지 활성화 -->
				<li class="page-item">
					<a class="page-link" href="${page.startPage-1}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${page.startPage}" end="${page.endPage}" var="p"> <!-- 페이지네이션을 반복 --> 
				<li class="page-item ${page.criteria.pageNum == p ? 'active' : '' }">
					<a class="page-link" href="${p}">${p}</a>
				</li>
			</c:forEach>
			<c:if test="${page.next}">
				<li class="page-item">
					<a class="page-link" href="${page.endPage+1}">다음</a>
				</li>
			</c:if>
		</ul>
		<form action="${ctxPath}/menu/orderMenu_bread" method="get" class="pageMove">
			<input type="hidden" name="pageNum" value="${page.criteria.pageNum}"/>
			<input type="hidden" name="amount" value="${page.criteria.amount}"/>
		</form>
</div>


<form class="fmno">
	<input type="hidden" name="pageNum" value="${criteria.pageNum}">
	<input type="hidden" name="amount" value="${criteria.amount}">
</form>


<script>


$(function(){
	
	let pageForm = $('.pageMove');

	let menuForm = $('.menuForm');

		// 페이지 버튼 클릭
		$('.pagination a').click(function(e) {
			e.preventDefault();
			let pageNum = $(this).attr('href');
			pageForm.find('[name="pageNum"]').val(pageNum);
			pageForm.appendTo('body').submit();
			
		})
		
		$('.order').click(function(){ 
			
			let selectMenus = []; 
			let countCheck = true; 
			
			$('input[name=selectMenus]:checked').each(function(){
				
				let menus = $(this).closest('tr');
				
				let menuInfo = {
					mno : menus.find('input[name="mno"]').val(), 
					name : menus.find('input[name="name"]').val(),
					price : menus.find('input[name="price"]').val(),
					proCount : menus.find('input[name="proCount"]').val(),
					proId : menus.find('input[name="proId"]').val(),
					type : menus.find('input[name="type"]').val(),
					memberId : menus.find('input[name="memberId"]').val(),
				};
				
				selectMenus.push(menuInfo); 

			})
								
				// ajax통신
				if(selectMenus.length > 0 && countCheck){
						console.log(selectMenus);
					$.ajax({
						type : 'post',
						url : '${ctxPath}/order/myOrder',
						data : JSON.stringify(selectMenus),
						contentType : 'application/json; charset=utf-8',
						success : function(result){
							console.log(result)
							console.log(selectMenus);
							if(result == "success")alert('주문에 성공했습니다')
							
						},
						error : function(xhr, status, er){
					
							alert('주문에 실패했습니다.')
						}
					})
				} else {
					alert('주문할 메뉴를 선택하거나, 수량을 체크해주세요')
				}
			
		})
		
})

</script>
