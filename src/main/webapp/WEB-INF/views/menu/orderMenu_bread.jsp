<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>


<div class="container d-flex justify-content-center mt-3">
	<h1>메뉴 / 주문하기</h1>
</div>

<div class="d-flex justify-content-center p-2">
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
	<form action="${ctxPath}/order/orderSubmit" method="post" class="menuForm">
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
								<input type="hidden" name="mno" value="${menu.mno}">
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
								<a class="getmove" href="${menu.mno}">${menu.name}</a>
							</td>
							<td>
								<input type="hidden" name="price" value="${menu.price}">
								${menu.price}
							</td>
							<td><input type="text" name="mcount" style="width:100%;" placeholder="수량을 입력하세요"></td>
						</tr>
					</tbody>
				</c:forEach>
			</c:if>
		</table>
			<div style="position:absolute; right:320px;">
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



<!-- 원본 이미지 모달창 -->
<div class="modal fade" id="showImage">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
	        <div class="modal-header">
	            <h4 class="modal-title">원본 이미지 보기</h4>
	            <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        <div class="modal-body"></div>
        </div>
    </div>
</div>

<form class="fmno">
	<input type="hidden" name="pageNum" value="${criteria.pageNum}">
	<input type="hidden" name="amount" value="${criteria.amount}">
</form>





<script>

let pageForm = $('.pageMove');

let menuForm = $('.menuForm');

	// 페이지 버튼 클릭
	$('.pagination a').click(function(e) {
		e.preventDefault();
		let pageNum = $(this).attr('href');
		pageForm.find('[name="pageNum"]').val(pageNum);
		pageForm.appendTo('body').submit();
		
	})
	
	// 조회 페이지로 이동
	$('.getmove').click(function(e) {
		e.preventDefault();
		let getValue = $(this).attr('href');
		$('.fmno').find('[name="mno"]').remove(); // 기존 bno input 제거
		 let pageNumAndAmount = $('.fmno').find('[name="pageNum"], [name="amount"]');
		 if (pageNumAndAmount.length == 0) {
			  $('.fmno').html($('<input/>',{type:'hidden',name:'pageNum',value:'1'}))
    		 			.html($('<input/>',{type:'hidden',name:'amount',value:'5'}))
		 } 
			 
		$('.fmno').append($('<input/>', {type:'hidden',name:'mno',value:getValue}))
				  .attr('action','${ctxPath}/menu/goodBreadGet')
				  .attr('method','get')
				  .submit();
	})

	
	$('.order').click(function(){ // 주문하기 클릭하면
		
		let selectMenus = []; // 선택한 메뉴들이 올 수 있게 배열 생성
		let countCheck = true; // 개수 체크할 변수 생성(기본값 true)
		
		$('input[name=selectMenus]:checked').each(function(){ // 체크 된 내용들만큼 반복
			
			let menus = $(this).closest('tr'); // button클릭에서 부터 조상으로 올라가 tr을 찾아 menus라는 변수에 저장
			// menu의 정보를 selectMenus에 객체로 담을거라서, menuInfo 정보 객체를 생성
			let menuInfo = {
				mno : menus.find('input[name="mno"]').val(), // tr에서 input의 name이 mno를 찾아서 mno에 저장
				name : menus.find('input[name="name"]').val(),
				price : menus.find('input[name="price"]').val(),
				mcount : menus.find('input[name="mcount"]').val(),
			};
			selectMenus.push(menuInfo); // 메뉴 정보를 배열에 저장
			console.log(selectMenus);
			
			if(!menuInfo.mcount){ // trim을 써서 좌우의 공백을제거하고, 개수가 ' ' 즉, 비어있다면
				countCheck = false;  // 개수 체크를 false로 변경
			}
			
		});
		
		// ajax통신
		if(selectMenus.length > 0 && countCheck){
			$.ajax({
				type : 'post',
				url : '${ctxPath}/order/myOrder',
				data : JSON.stringify(selectMenus),	// data로는 메뉴의 정보가 담긴 배열변수를 보냄, 그런데 그걸 json형식으로 변경해서 보내기
				contentType : 'application/json; charset=utf-8',
				success : function(result){
					console.log(result)
					if(result == "success")alert('주문에 성공했습니다')
					
				},
				error : function(xhr, status, er){
					alert('주문에 실패했습니다.')
				}
			})
		} else {
			alert('주문할 메뉴를 선택하거나, 수량을 체크해주세요')
		}
		
		
		menuForm.find('input[name="selectMenus"]').remove(); // 기존 필드 제거
		for(let i=0; i < selectMenus.length; i++){
			let menu = selectMenus[i];
			menuForm.append($('<input/>', {type:'hidden',name:'selectMenus',value:JSON.stringify(menu) }));
		}
		menuForm.submit();
		
		
	//	menuForm.append(selectMenus);
	//			.submit();
		
		console.log(menuForm);
	})
	

	

	
</script>

