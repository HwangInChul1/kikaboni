<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="d-flex justify-content-center p-2 mt-5">
	<div>
		<ul class="list-group mt-5 mr-5" style="width:250px;">
			<li class="list-group-item">
				<a href="${ctxPath}/admin/menuList">들어온 주문</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/admin/menuHistory">완료한 주문(주문 내역)</a>
			</li>
		</ul>
	</div>
	
	<div class="container d-flex justify-content-center ml-5 mt-5" style="position:relative">
		<table class="table table-bordered text-center table-info" style="position:absolute; left:50px; width:900px;">
			<thead>
				<tr>
					<td>주문 번호</td>
					<td>회원 명</td>
					<td>제품 명</td>
					<td>주문 날짜</td>
					<td>주문 개수</td>
					<td>제품 가격</td>
					<td>총 가격</td>
				</tr>
			</thead>
				<c:if test="${not empty list}">
					<c:forEach items="${list}" var="orderHistory">
					    <tbody>
					        <tr>
					            <td>${orderHistory.ono}</td>
					            <td>${orderHistory.memberId}</td>
					            <td>
					                <c:forEach items="${orderHistory.menuList}" var="menu">
					                    ${menu.name}<br>
					                </c:forEach>
					            </td>
					            <td><fmt:formatDate value="${orderHistory.orderDate}" pattern="yyyy년 MM월 dd일 HH시 mm분"/></td>
					            <td>${orderHistory.proCount}</td>
					            <td>
					                <c:forEach items="${orderHistory.menuList}" var="menu">
					                    ${menu.price}<br>
					                </c:forEach>
					            </td>
					            <td>${orderHistory.menuList[0].price * orderHistory.proCount}</td>
					            
					        </tr>
					        
					    </tbody>
					    
					</c:forEach>
	
				</c:if>
		</table>
	</div>
	<div class="container d-flex justify-content-center">
		<ul class="pagination" style="position:absolute; top:210px; right:400px;">
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
		<form action="${ctxPath}/admin/menuList" method="get" class="pageMove">
			<input type="hidden" name="pageNum" value="${page.criteria.pageNum}"/>
			<input type="hidden" name="amount" value="${page.criteria.amount}"/>
		</form>
	</div>

</div>


<script>

$(function(){
	
	let pageForm = $('.pageMove');
	
	// 페이지 버튼 클릭
	$('.pagination a').click(function(e) {
		e.preventDefault();
		let pageNum = $(this).attr('href');
		pageForm.find('[name="pageNum"]').val(pageNum);
		pageForm.appendTo('body').submit();
		
	})
	
	
})




</script>



