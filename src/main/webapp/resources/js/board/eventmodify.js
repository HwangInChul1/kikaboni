console.log('modify.js');

$(function(){

	let bnoValue = $('[name="bno"]').val(); // name이 bno의 값을 가져와서 bnoValue에 담음
	let uploadResultList = []; // 첨부한 업로드 파일 목록을 담기 위한 배열 생성
	let toBeDelList = []; // 삭제 대상 파일 목록을 담기 위한 배열 생성

	
// 파일 업로드 결과를 화면에 표시
	let showUploadResult = function(attachList){
		let fileList = '';
		$(attachList).each(function(i,e){
			uploadResultList.push(e); // 첨부파일 배열의 요소로 추가
			fileList += 
			`<li class="list-group-item" data-uuid="${e.uuid}">
				<div class="float-left">`
				if(e.fileType){ // 이미지 파일일 때
					let filePath = e.uploadPath+"/s_"+e.uuid+"_"+e.fileName;
					let encodingFilePath = encodeURIComponent(filePath) 
					fileList += 
					`<div class="d-inline-block mr-4 mb-4">
						<img alt="첨부파일" src="${ctxPath}/files/event/display?fileName=${encodingFilePath}">
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
				<div class="float-right">
					<a href="#" class="delete">삭제</a>
				</div>
			</li>`;
		});
		$('.uploadResultDiv ul').append(fileList);
		
	//showUploadResult();
	}


	// 첨부파일 목록 불러오기
	$.getJSON(
		`${ctxPath}/board/eventAttachList`, {bno : bnoValue}, 
		function(attachList){
			console.log(attachList);
			let fileList = '';
			$(attachList).each(function(i,e){
			
			fileList += `<li class="list-group-item" data-uuid="${e.uuid}">
			<div class="float-left">`
			
			if(e.fileType){ // 이미지 파일일때
				let filePath = e.uploadPath + "/s_" + e.uuid + "_" + e.fileName;
				let encodingFilePath = encodeURIComponent(filePath);
				fileList += `
					<div class="d-inline-block mr-4">
						<img alt="첨부파일" src="${ctxPath}/files/event/display?fileName=${encodingFilePath}">
					</div>`
			} else {
				fileList +=
					`<div class="d-inline-block mr-4" style="width:40px;">
						<img alt="첨부파일" src="${ctxPath}/resources/images/attach.png" style="width:100%;">
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
			} else {
				let fileName = encodeURIComponent(e.uploadPath + "/" + e.uuid + "_" + e.fileName);
				fileList += `<a href="${ctxPath}/files/event/download?fileName="${fileName}">다운로드</a>`	
			}
			fileList += `
				<div class="form-check-inline ml-2">
					<label class="form-check-label">
						<input type="checkbox" class="form-check-input toBeDelFile">
						<span>삭제</span>
					</label>
				</div>
			</div></li>`;
			});
			$('.uploadResultDiv ul').html(fileList);
		}); // $.getJSON END

	// 원본 이미지 보기
	$('.uploadResultDiv ul').on('click','.showImage',function(e){
		e.preventDefault();
		let filePath = $(this).attr('href'); // 원본보기의 경로를 가져와서 filePath에 값으로 답기, 경로에는 파일 이름이 들어가있음
		let imgSrc = `${ctxPath}/files/display?fileName=${filePath}` // 원본이미지를 보여주기 위한 컨트롤러의 경로를 값으로 저장
		let imgTag = $('<img>' , {src:imgSrc, class : "img-fluid"}) // img 태그를 추가해서 경로는 imgSrc로 주기
		$('#showImage').find('.modal-body').html(imgTag); // id가 showImage중에서 class가 modal-body를 찾아서 imgTag를 밀어넣기
		$('#showImage').modal(); // 모달창 보여주기
	});

	// 파일 업로드
	$('input[type="file"]').change(function(){
	
		let formData = new FormData(); // 임시로 폼데이터 생성
		let files = this.files; // this.files는 input type="file"을 통해 선택한 파일들이고 그 파일들이 files에 저장
		
		for(let f of files){
			formData.append('eventuploadFile',f);
		}
	
		$.ajax({
			type : 'post',
			url : `${ctxPath}/files/eventupload`,
			processData : false,
			contentType : false,
			data : formData, // 전달할 데이터는 임시로 생성한 폼 데이터
			dataType : 'json',
			success : function(attachList){
				console.log(attachList);
				showUploadResult(attachList) // 첨부 파일을 화면에 표시
			//	uploadResultList = attachList;
			}
		});
	
	})

	// 새로 첨부한 파일 삭제
	$('.uploadResultDiv ul').on('click','.delete',function(e){
		e.preventDefault();
		let file = $(this).closest('li');
		let uuid = file.data('uuid');
		let targetFile = null;
		let targetFileIdx = -1;
		
		$(uploadResultList).each(function(i, e){
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
			url : `${ctxPath}/files/eventDeleteFile`,
			data : targetFile,
			success : function(result){
				console.log(result)
			}
		});
		file.remove();
	});

	// 기존에 업로드 된 파일 삭제
	$('.uploadResultDiv ul').on('change','.toBeDelFile',function(e){
		let listTag = $(this).closest('li');
		let uuidValue = listTag.data('uuid');
		
		if($(this).is(':checked')){ // 체크
			$.ajax({
				type : 'get',
				url : `${ctxPath}/board/eventAttachFileInfo`,
				data : {uuid : uuidValue},
				success : function(boardAttachVO){
					toBeDelList.push(boardAttachVO);
				}
			}); // ajax end
		} else {
			toBeDelList = toBeDelList.filter(e => e.uuid != uuidValue);
		} 
		console.log(toBeDelList);
	})

	// 목록 삭제,수정
	$('.modifyForm button').click(function() {
			let operation = $(this).data('oper');  // oper값은 버튼 클릭할때 마다 가져옴
			addCriteria();
			
			if(operation == 'delete'){
				form.attr('action',`${ctxPath}/boardModify/eventdelete`);
			} else if(operation == 'list'){
				form.empty();
				addCriteria();
				form.attr('method','get')
					.attr('action',`${ctxPath}/boardkind/eventList`);
			} else { // 수정처리(삭제 대상 첨부파일 목록이 있을 때 삭제)
			let idx = 0;
			if(toBeDelList.length > 0){
				console.log(toBeDelList);
				$(toBeDelList).each(function(i, e){
					let bno = $('<input/>',{type:'hidden', name : `attachList[${i}].bno`, value : `${e.bno}`});
					let uuid = $('<input/>',{type:'hidden', name : `attachList[${i}].uuid`, value : `${e.uuid}`});
					let fileName = $('<input/>',{type:'hidden', name : `attachList[${i}].fileName`, value : `${e.fileName}`});
					let fileType = $('<input/>',{type:'hidden', name : `attachList[${i}].fileType`, value : `${e.fileType}`});
					let uploadPath = $('<input/>',{type:'hidden', name : `attachList[${i}].uploadPath`, value : `${e.uploadPath}`});
				
					form.append(bno)
						.append(uuid)
						.append(fileName)
						.append(fileType)
						.append(uploadPath);
					
					idx = ++i;
				});
			}
				
			if(uploadResultList.length > 0 ){
		
				console.log(uploadResultList);
				$(uploadResultList).each(function(i, e){
					console.log(idx+i);
					let uuid = $('<input/>',{type:'hidden', name : `attachList[${idx+i}].uuid`, value : `${e.uuid}`});
					let fileName = $('<input/>',{type:'hidden', name : `attachList[${idx+i}].fileName`, value : `${e.fileName}`});
					let fileType = $('<input/>',{type:'hidden', name : `attachList[${idx+i}].fileType`, value : `${e.fileType}`});
					let uploadPath = $('<input/>',{type:'hidden', name : `attachList[${idx+i}].uploadPath`, value : `${e.uploadPath}`});
					form.append(uuid)
						.append(fileName)
						.append(fileType)
						.append(uploadPath);	
					});
				}
			}	
				
			form.submit();
			
		})


})