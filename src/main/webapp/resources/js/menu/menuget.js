console.log('menu.js');

$(function(){

let mnoValue = $('[name="mno"]').val();

// 첨부파일 목록 조회
$.getJSON( `${ctxPath}/menu/menuAttachList`, {mno : mnoValue},
	function(attachList){
		console.log(attachList);
		console.log(mnoValue);

	let fileList = '';
	$(attachList).each(function(i,e){
	
		if(e.fileType){ // 이미지 파일이면 섬네일 표시
			let filePath = e.uploadPath + "/s_" + e.uuid + "_" + e.fileName;
			let encodingFilePath = encodeURIComponent(filePath);
			fileList += `
				<div class="thumnail d-inline-block mr-3">
					<img alt="" src="${ctxPath}/files/display?fileName=${encodingFilePath}">	
				</div>`
		} else {
			fileList += `
				<div class="thumnail d-inline-block mr-3" style="width:40px;">
					<img alt="" src="${ctxPath}/resources/images/attach.png" style="width:100%">
				</div>`
		}

	});
		$('.uploadResultDiv').html(fileList);
		console.log(attachList[0]);
	});
	
	// 원본 이미지 보기
	$('.uploadResultDiv').on('click','.showImage', function(e){
		e.preventDefault();
		let filePath = $(this).attr('href');
		let imgSrc = `${ctxPath}/files/display?fileName=${filePath}`
		let imgTag = $('<img>', {src:imgSrc, class:'img-fluid'});
		$('#showImage').find('.modal-body').html(imgTag);
		$('#showImage').modal();
	})
	
})