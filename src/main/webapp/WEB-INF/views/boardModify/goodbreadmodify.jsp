<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="jumbotron text-center d-flex justify-content-center">
		  		<h1>게시판 수정</h1>
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
				<form action="${ctxPath}/boardModify/goodmodify" class="modifyForm" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div class="form-group mb-3" style="height:auto; width:100%">
						<div style="width:30%; float:left;">
							<input type="text" value="${vo.bno}" name="bno" readonly="readonly" style="width:95%" class="form-control">
						</div>
						<div style="width:70%; float:left;"> 
							<input type="text" value="${vo.title}" name="title" style="width:100%" class="form-control mb-3">
						</div>
					</div>
					<div class="form-group">
						<textarea rows="10" class="form-control mb-3" name="content">${vo.content}</textarea>

						<input type="text" name="writer" value="${vo.writer}" readonly="readonly" class="form-control mb-3">

						<input type="text" value="<fmt:formatDate value="${vo.writeDate}" pattern="yyyy년MM월dd일 HH시mm분"/>" readonly="readonly" class="form-control mb-3">
						
						<input type="text" value="<fmt:formatDate value="${vo.updateDate}" pattern="yyyy년MM월dd일 HH시mm분" />" readonly="readonly" class="form-control mb-3">
					</div>
					<div class="text-right">
						<button data-oper="list" class="btn btn-inline btn-primary list">목록</button>
						<button data-oper="update" class="btn btn-inline btn-info update">수정</button>
						<button data-oper="delete" class="btn btn-inline btn-danger delete">삭제</button>
					</div>
				</form>
				</div>
			</div>
		</div>	
	</div>
	
	<div class="row mt-3 mb-5">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h4>첨부파일</h4>
				</div>
				<div class="card-body">
					<div class="uploadDiv form-group">
						<input type="file" name="uploadFile" class="form-control" multiple="multiple">
					</div>
					<div class="uploadResultDiv form-group">
						<ul class="list-group"></ul>
					</div>
				</div>
			</div>
		</div>	
	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="showImage">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">원본 이미지보기</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body"></div>
        </div>
    </div>
</div>


<script>

	let form = $('.modifyForm');
	let content = $('.modifyForm').find('[name="content"]');

	function addCriteria(){
	
		form.append($('<input/>', {type:'hidden',name:'pageNum',value:'${criteria.pageNum}'}))
			.append($('<input/>', {type:'hidden',name:'amount',value:'${criteria.amount}'}))
	}

</script>

<script src="${ctxPath}/resources/js/modify.js"></script>

