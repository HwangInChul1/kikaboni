package kikaboni.project.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kikaboni.project.domain.BoardAttachVO;
import kikaboni.project.domain.MenuAttachVO;
import kikaboni.project.domain.MenuVO;
import kikaboni.project.service.MenuService;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("/files")
@Log4j
public class EventBoardFileUploadController {

	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/eventupload")
	public ResponseEntity<List<BoardAttachVO>> upload1(@RequestParam("eventuploadFile") MultipartFile[] multipartFiles){
		
		List<BoardAttachVO> list = new ArrayList<BoardAttachVO>();
		File uploadPath = new File("c:/storage", getFolder());
		
		if(!uploadPath.exists()) { // uploadPath가 존재하지 않는다면, 즉, c:/storage에 파일이 없다면
			uploadPath.mkdirs(); // 폴더를 생성
		}
		
		log.info("파일 업로드!");
		
		for(MultipartFile multipartFile : multipartFiles) {
			log.info(multipartFile.getOriginalFilename());
			
			BoardAttachVO vo = new BoardAttachVO();
			
			String fileName = multipartFile.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			File saveFile = new File(uploadPath, uuid+"_"+fileName);
			Long bno = vo.getBno();
			
			log.info("fileName : " + fileName);
			log.info("saveFile : " + saveFile);
			
			vo.setBno(bno);
			vo.setFileName(fileName);
			vo.setUuid(uuid);
			vo.setUploadPath(getFolder());
			
			try {
				if(checkImageType(saveFile)) {
					vo.setFileType(true);
					// 섬네일 이미지 업로드
					File thumbFile = new File(uploadPath, "s_" + uuid + "_" + fileName);
					FileOutputStream thumbnail = new FileOutputStream(thumbFile);
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 50, 50);
				}
				multipartFile.transferTo(saveFile); // 실제 디렉토리에 물리적으로 파일 저장
				list.add(vo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 이미지 파일인지 체크 여부
	private boolean checkImageType(File file) throws IOException {
		
		String contentType = Files.probeContentType(file.toPath());
		return contentType !=null ? contentType.startsWith("image") : false;
		
	}
	
	// 날짜 별 디렉토리 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(new Date());
	}
	
	// 이미지 보여주기
	@GetMapping("/event/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File("c:/storage/" + fileName);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> result = null;
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/eventDeleteFile")
	public ResponseEntity<String> deleteFile(BoardAttachVO vo){
			
		// 원본파일 삭제
		File file = new File("c:/storage/"+vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName());
		file.delete();
			
		// 섬네일 삭제
		if(vo.isFileType()) {
			file = new File("c:/storage/"+vo.getUploadPath(), "s_"+vo.getUuid() + "_" + vo.getFileName());
			file.delete();
		}
			
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	// 파일 다운로드
	@GetMapping("/event/download")
	public ResponseEntity<Resource> downloadFile(String fileName){
		Resource resource = new FileSystemResource("c:/storage/" + fileName);
		
		if(!resource.exists()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String resourceName = resource.getFilename();
		String resourceOriginName = resourceName.substring(resourceName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = URLEncoder.encode(resourceOriginName,"utf-8");
			headers.add("content-Disposition","attachment; fileName = " + downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	
}
