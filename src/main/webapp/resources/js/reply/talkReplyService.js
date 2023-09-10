console.log('replyService.js');

let replyService = { // replyService라는 객체 생성

// 그리고 객체 안에 댓글 처리할 함수 생성
// 함수 안에 ajax생성

	// 댓글 작성
	add : function(reply, callback, error){
		console.log('add');
		
		$.ajax({
			type : 'post',
			url : `${ctxPath}/replies/newTalk`,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				if(callback) callback(result);
			},
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	},
	
	// 댓글 목록 
	getList : function(param, callback, error){
	
		let bno = param.bno;
		let page = param.page || 1;
	
		$.ajax({
			type : 'get',
			url : `${ctxPath}/replies/talkPages/${page}/${bno}`,
			success : function(replyPageDTO){
				console.log(replyPageDTO);
				if(callback) callback(replyPageDTO.replyCount, replyPageDTO.list);
			},
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	},
	
		// 댓글 조회
	get : function(rno, callback, error){

		$.ajax({
		
			type : 'get',
			url : `${ctxPath}/replies/talk/${rno}`,
			success : function(result){
				if(callback) callback(result)
			},
			error : function(xhr, status, er){
				if(error) error(er)
			}	
		});
	},
	
	// 댓글 수정
	update : function(reply, callback, error){
		
		$.ajax({
			type : 'put',
			url : `${ctxPath}/replies/talk/${reply.rno}`,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				if(callback) callback(result);
			},
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	},
	
	// 댓글 삭제
	remove : function(rno,callback,error){
		$.ajax({
			type : 'delete', 
			url : `${ctxPath}/replies/talk/${rno}`,
			success : function(result){
				if(callback) callback(result);
				console.log('aaa');
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	}

}

