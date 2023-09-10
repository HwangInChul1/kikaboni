console.log('upload');

$(function(){

	// 첨부파일 목록 배열
	let uploadResultList = [];
	
	// 파일 업로드 결과를 화면에 표시
	let showUploadResult = function(attachList){
		let fileList = '';
		$(attachList).each(function(i,e){
			uploadResultList.push(e); // 첨부파일 배열의 요소로 추가
			fileList += 
			`<li class="list-group-item" data-uuid="${e.uuid}" style="height:120px;">
				<div class="float-left">`
				if(e.fileType){ // 이미지 파일일 때
					let filePath = e.uploadPath+"/s_"+e.uuid+"_"+e.fileName;
					let encodingFilePath = encodeURIComponent(filePath) 
					fileList += 
					`<div class="d-inline-block mr-4">
						<img alt="첨부파일" src="${ctxPath}/files/display?fileName=${encodingFilePath}">
					</div>`
				} else { // 이미지 파일이 아닐 때 
					fileList += 
					`<div class="d-inline-block mr-4" style="width: 40px">
						<img alt="첨부파일" src="${ctxPath}/resources/images/attach.png" style="width: 100%">
					</div>`
				}
				fileList += 	
					`<div class="d-inline-block">
						${e.fileName}
					</div>
				</div>
			</li>`;

		});
		$('.uploadResultDiv').append(fileList);
		
	}

	$('input[type="file"]').change(function(){
	
		let formData = new FormData(); // 임시로 폼데이터 생성
		let files = this.files; // this.files는 input type="file"을 통해 선택한 파일들이고 그 파일들이 files에 저장
		
		for(let f of files){
			formData.append('uploadFile',f);
		}
			console.log(formData);
	
		$.ajax({
			type : 'post',
			url : `${ctxPath}/files/upload`,
			processData : false,
			contentType : false,
			data : formData, // 전달할 데이터는 임시로 생성한 폼 데이터
			dataType : 'json',
			success : function(attachList){
				console.log(attachList);
				showUploadResult(attachList) // 첨부 파일을 화면에 표시
			}
		});
	
	})
	
	// 파일 삭제
	$('.uploadResultDiv ul').on('click','.delete',function(e){
	
		e.preventDefault();
		let file = $(this).closest('li');
		let uuid = file.data('uuid');	
		let targetFile = null;
		let targetFileIdx = -1;
		
		$(uploadResultList).each(function(i,e){
			if(e.uuid == uuid){
				targetFileIdx = i;
				targetFile = e;
				return;
			}
		})

		if(targetFileIdx != -1) uploadResultList.splice(targetFileIdx, 1);
		
		console.log(targetFile);
		console.log(uploadResultList);
		
		$.ajax({
			type : 'post',
			url : `${ctxPath}/files/deleteFile`,
			data : targetFile,
			success : function(result){
				console.log(result);
			}
		});
		file.remove();
		
	});
	
	
	// 글쓰기 처리
	$('.regibtn').click(function(){
	
		let form = $('form');
		if(uploadResultList.length > 0){
		
			$(uploadResultList).each(function(i,e){
			console.log(uploadResultList);
				let uuid = $('<input/>', {type:'hidden', name:`attachList[${i}].uuid`, value:`${e.uuid}`});
				let fileName = $('<input/>', {type:'hidden', name:`attachList[${i}].fileName`, value:`${e.fileName}`});
				let fileType = $('<input/>', {type:'hidden', name:`attachList[${i}].fileType`, value:`${e.fileType}`});
				let uploadPath = $('<input/>', {type:'hidden', name:`attachList[${i}].uploadPath`, value:`${e.uploadPath}`});
				form.append(uuid)
					.append(fileName)
					.append(fileType)
					.append(uploadPath);
			});
		}
		form.submit();
	})
	
})