package com.ssafy.trend_gaza.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	
	private static final FileUtil instance = new FileUtil();
	
	public static FileUtil getInstance() {
		return instance;
	}

	
	public String getNewFileName(String originalFileName) {
		// saveFileName 생성
        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString() + originalFileName;
		
        return saveFileName;
	}
	
	/*
	 * 업로드하려는 위치에 폴더가 없으면 생성한다.
	 */
	public void makeFolder(String saveFolder) {
		File folder = new File(saveFolder);
		
		if(!folder.exists()) {
			try {
				folder.mkdir();		// 폴더 생성
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} 
	}
	
	public String getFolder(String realPath) {
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		return realPath + File.separator + today;
	}
	
	public String upload(String originalFileName, MultipartFile multipartFile, File folder) throws Exception {
		String saveFileName = UUID.randomUUID().toString()
				+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//		fileInfoDto.setSaveFolder(today);
//		fileInfoDto.setOriginalFile(originalFileName);
//		fileInfoDto.setSaveFile(saveFileName);
		//logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
		
		multipartFile.transferTo(new File(folder, saveFileName));
		
		return saveFileName;
	}

}
