console.log('get.js');

$(function(){

let bnoValue = $('[name="bno"]').val();

// 첨부파일 목록 조회
	$.getJSON( `${ctxPath}/board/talkAttachList`, {bno : bnoValue},
	function(attachList){
		console.log(attachList);

	let fileList = '';
	$(attachList).each(function(i,e){
	
		fileList += `<li class="list-group-item" data-uuid=${e.uuid}">
					<div class="float-left">`
		if(e.fileType){ // 이미지 파일이면 섬네일 표시
			let filePath = e.uploadPath + "/s_" + e.uuid + "_" + e.fileName;
			let encodingFilePath = encodeURIComponent(filePath);
			fileList += `
				<div class="thumnail d-inline-block mr-3">
					<img alt="" src="${ctxPath}/files/talk/display?fileName=${encodingFilePath}">	
				</div>`
		} else {
			fileList += `
				<div class="thumnail d-inline-block mr-3" style="width:40px;">
					<img alt="" src="${ctxPath}/resources/images/attach.png" style="width:100%">
				</div>`
		}
			fileList += 
				`<div class="d-inline-block">
					${e.fileName}
				</div>
				</div>
				<div class="float-right">`
		if(e.fileType){ // 이미지 파일일떄
			let imgUrl = encodeURIComponent(e.uploadPath + "/" + e.uuid + "_" + e.fileName);
			fileList += `<a href="${imgUrl}" class="showImage">원본보기</a>`
		}else {
			let fileName = encodeURIComponent(e.uploadPath + "/" + e.uuid + "_" + e.fileName);
			fileList += `<a href="${ctxPath}/files/talk/download?fileName=${fileName}" class="download">다운로드</a>` 
		}
			fileList += `</div></li>`
	});
		$('.uploadResultDiv ul').html(fileList);

	});

	// 원본 이미지 보기
	$('.uploadResultDiv ul').on('click','.showImage', function(e){
		e.preventDefault();
		let filePath = $(this).attr('href');
		let imgSrc = `${ctxPath}/files/talk/display?fileName=${filePath}`
		let imgTag = $('<img>', {src:imgSrc, class:'img-fluid'});
		$('#showImage').find('.modal-body').html(imgTag);
		$('#showImage').modal();
	})

	// 파일 다운로드
//	$('.uploadResultDiv ul').on('click','.download',function(e){
	//	e.preventDefault();
	//	self.location = `${ctxPath}/files/talk/download?fileName=${$(this).attr('href')}`
//	});

})


