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

	// 댓글 수정 및 삭제 처리
	 replyContainer.on('click','.reply_modify a', function(e){
	// 클래스가 chat을 클릭하면 reply_modify의 클래스 이름을가지며 하위의 a태그에 클릭이벤트 권한 넘김
		e.preventDefault(); // a태그 기본동작 금지
		
		let rno = $(this).closest('li').data('rno'); // 클릭하면 조상중에 li를 찾아 data가 rno인 것을 가져옴
		let operation = $(this).attr('href'); // 클릭하면 href속성의 값을 가져와서 operation에 저장
		
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
		// let replyWriteForm = $('.replyWriteForm');
		// listTag.append(replyWriteForm); // li태그를 가진 변수에 댓글작성 창을 추가
		
		// 이렇게 li를 찾아서 댓글작성창을 추가하는것까지는 좋지만, 밑의 댓글작성창이 사라짐, 그래서 clone로 복사해서 붙이자
		
		// 위의 댓글 작성하는 창을 li에 추가하는건 주석처리 
		let replyUpdateForm = $('.replyWriteForm').clone();
		//	listTag.append(replyUpdateForm);
		
		// 여기에서 문제점, clone된 작성창이 1씩 더해가면서 계속 늘어남, 이 부분 수정
		replyUpdateForm.attr('class','my-3 replyUpdateForm'); // replyUpdateForm의 class 속성에 이름주고, 왼쪽3칸 띄움
		let updateBtn = replyUpdateForm.find('.replyBtn').html('수정');
		
		// 이제는 1씩 더해가면서 안나오고 한개씩 추가 됨, replyUpdateForm의 클래스 명을 바꿨기 때문이다. 하지만 그래도 추가되는걸 수정필요
		let replyUpdateFormLength = listTag.find('.replyUpdateForm').length;
		// li에서 replyUpdateForm을 찾아서 길이를 계산해서 변수에 저장
		console.log(replyUpdateFormLength); // 콘솔로 찍어보니 확실히 1씩 늘어난다.
		// 그래서 조건문을 써서 0초과하면 즉, 1이상이 되면 수정창을 삭제하면 됨
		if(replyUpdateFormLength > 0){
			listTag.find('.replyUpdateForm').remove();
			$(this).html('수정');
			$(this).next().show();
			return;
		}
		
		// 그리고 다른 문제는 다른 댓글의 수정창도 같이 열린다. 이거 바꿔야 한다.
		
		// 그 전에 조회를 통해 댓글내용을 불러와서 수정하자
		replyService.get(rno, function(result){
			replyUpdateForm.find('.replyContent').val(result.reply);
			replyUpdateForm.find('.replyer').html(result.replyer);
		}, function(er){
			console.log(er)
		})
		// get을 통해 댓글내용을 댓글수정창에 출력하게 되었다.
		
		listTag.append(replyUpdateForm); // 위에 clone이 만들어지고 바로 추가하는게 아닌
		// get으로 댓글내용 나오게 하고 난 뒤 추가(아무튼 밑부분에 추가)
		$(this).html('취소');
		$(this).next().hide();
		
		// 댓글 수정처리 해야 됨
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



















