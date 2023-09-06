<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>


${reply}



<div class="d-flex justify-content-center p-2">
	<div>
		<ul class="list-group mt-3 mr-5" style="width:200px;">
			<li class="list-group-item">
				<a href="${ctxPath}/mypage">회원 정보</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/mypageUpdate">회원정보 변경</a>
			</li>	
			<li class="list-group-item">
				<a href="${ctxPath}/member/mytext">내가 쓴 글</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/member/myreply">내가 쓴 댓글</a>
			</li>
			<li class="list-group-item">
				<a href="${ctxPath}/member/orderhistory">주문 내역</a>
			</li>
		</ul>
	</div>

	<div class="container d-flex justify-content-center mt-3" style="position:relative">
		<div class="row" style="position:absolute; left:200px;">
			<div class="col-12">
				<table class="table table-boarder table-primary text-center">
					<thead>
						<tr>
							<td>게시판 종류</td>
							<td>게시글 번호</td>
							<td>댓글 번호</td>
							<td>내가 쓴 댓글</td>						
						</tr>
					</thead>
					<c:if test="${not empty reply}">
						<c:forEach items="${reply}" var="r">
							<tbody>
								<tr>
									<td>
										<c:forEach items="${r.myReplyList}" var="kind">
	                     				   ${kind.board_name}<br/>
	                    				</c:forEach>
									</td>
									<td>${r.bno}</td>
									<td>${r.rno}</td>
									<td>${r.reply}</td>
								</tr>
							</tbody>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</div>







