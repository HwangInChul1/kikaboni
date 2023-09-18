console.log('reply.js');

$(function(){

let bnoValue = $('[name="bno"]').val();
let boardId = $('[name="board_id"]').val();
let replyContainer = $('.chat');
let pageNum = 1;
let paginationWrap = $('.pagination_wrap');
let showPage = function(replyCount){ 

	let endPage = Math.ceil(pageNum/5.0)*5;
	
	let startPage = endPage - 4;
	
	let tempEndPage = Math.ceil(replyCount/5.0);
	
	let prev = startPage != 1;

	let next = endPage < tempEndPage;
	
	endPage = (endPage > tempEndPage) ? tempEndPage : endPage;
	
	let pagination = `<ul class="pagination">`;
	
	if(prev){
		pagination += `<li class="page-item">
		<a class="page-link" href="${startPage - 5}">이전</a></li>`
	}
	for(let pageLink = startPage; pageLink <= endPage; pageLink++){
		pagination += `<li class="page-item ${pageLink == pageNum ? 'active' : ''}">
						<a class="page-link" href="${pageLink}">${pageLink}</a></li>`;
	} 
	if(next){
		pagination += `<li class="page-item">
		<a class="page-link" href="${endPage+1}">다음</a></li>`;
	}
	pagination += `</ul>`

	paginationWrap.html(pagination);
}

	let showList = function(page){
		
		let param = {bno:bnoValue, page:page||1};
	
		replyService.getList(param, function(replyCount, list){
			console.log(replyCount);
			if(page == -1){ // 글 작성 후 마지막 페이지 호출
				pageNum = Math.ceil(replyCount/10.0);
				showList(pageNum);
				return;
			}
			
			if(page == -2){ // 글 삭제
				pageNum = replyCount%10 == 0 ? pageNum-1 : pageNum;
				showList(pageNum);
				return;
			}
			
			if(replyCount == 0){
				replyContainer.html('등록된 댓글이 없습니다.');
				return;
			}

			let replyListHtml = '';
		
			$.each(list, function(i, e){
			replyListHtml += `<li class="list-group-item" data-rno="${e.rno}" style="height:100%;">
					<div class="d-flex justify-content-between">
					  <div class="d-flex">
					    <div class="user_image mr-3">
					      <img class="rounded-circle" src="${ctxPath}/resources/images/userImage_basic.png" alt="유저이미지" 
					      style="width:70px; height:70px; border:solid 1px"/>
					    </div>
					    <div class="comment_wrap">
					      <div class="comment_info text-right" style="width:150px;">
					        <span class="userName badge badge-pill badge-info mr-2" style="width:100%;">${e.replyer}</span>
					        <span class="badge badge-dark" style="width:100%;">${e.replyDate}</span>
					      </div>
					      <div class="comment_content py-2">${e.reply}</div>
					    </div>
					  </div>`
					
				if(memberId == e.replyer || auth.includes('ROLE_ADMIN')){
					replyListHtml +=  `<div class="reply_modify">
								    <button type="button" class="btn btn-light dropdown-toggle mr-2" 
								    style="position:absolute; top:0px; right:0px;" data-toggle="dropdown">변경</button>
								    <div class="dropdown-menu">						   
								      <a class="dropdown-item replyModify" href="modify">수정</a>
								      <a class="dropdown-item replyDelete" href="delete">삭제</a>
								    </div>
							      </div>`
				}
				
				replyListHtml += `</div></li>`
				
			});

		replyContainer.html(replyListHtml);
		showPage(replyCount);
		});
	}
	showList(pageNum);

	// 페이지 이동
	paginationWrap.on('click','a',function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		pageNum = targetPageNum;
		showList(pageNum);
	})


// 댓글 추가 이벤트 처리
	$('.submit button').click(function(){
		let reply = {
			bno : bnoValue,
			reply : $('.replyContent').val(),
			replyer : $('.replyer').html(),
			boardId : boardId
		};
		
		replyService.add(reply, function(result){
		
			if(result == 'success'){
				alert('댓글을 등록하였습니다.');
				
			//	showList();
			} else {	
				alert('댓글 등록을 실패하였습니다.');
			}
			$('.replyContent').val('');
			showList(-1); // 목록 갱신
		}, function(er){
			console.log(er);
		});
	
	});


	 replyContainer.on('click','.reply_modify a', function(e){

		e.preventDefault(); 
		
		let rno = $(this).closest('li').data('rno'); 
		let operation = $(this).attr('href');
		
		if(operation == 'delete'){
			replyService.remove(rno, function(result){
			
				if(result == 'success'){
					alert(rno + '번 댓글을 삭제하였습니다.');
					showList(-2);
				} else {
					alert(rno + '번 댓글 삭제에 실패했습니다.');
				}
			})
			return;
		}
		
		if(operation == 'modify'){
			
		let listTag = $(this).closest('li');
	
		let replyUpdateForm = $('.replyWriteForm').clone();

		replyUpdateForm.attr('class','my-3 replyUpdateForm');
		let updateBtn = replyUpdateForm.find('.replyBtn').html('수정');
		

		let replyUpdateFormLength = listTag.find('.replyUpdateForm').length;
	
		console.log(replyUpdateFormLength); 
	
		if(replyUpdateFormLength > 0){
			listTag.find('.replyUpdateForm').remove();
			$(this).html('수정');
			$(this).next().show();
			return;
		}
		

		replyService.get(rno, function(result){
			replyUpdateForm.find('.replyContent').val(result.reply);
			replyUpdateForm.find('.replyer').html(result.replyer);
		}, function(er){
			console.log(er)
		})

		listTag.append(replyUpdateForm);

		$(this).html('취소');
		$(this).next().hide();
		

		updateBtn.click(function(){
			let reply = {
				rno : rno,
				reply : replyUpdateForm.find('.replyContent').val()
			};
			replyService.update(reply, function(result){
				alert(result)
				showList(pageNum);
			}, function(er){
				console.log(er);
			})
		
		})
			return;
		}
	});

});



















