package com.ssafy.trend_gaza.util;


import com.ssafy.trend_gaza.Image.exception.ImageException;
import com.ssafy.trend_gaza.common.CustomException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class FileUtil {

    public static String findContentType(String contentType) {
        String[] mediaContentType = contentType.split("/");
        if (mediaContentType.length <= 0)
            throw new ImageException(CustomExceptionStatus.FILE_CONVERT_FAIL);
        if (!(mediaContentType[0].toUpperCase().equals("IMAGE")
                || mediaContentType[0].toUpperCase().equals("VIDEO")))
            throw new CustomException(CustomExceptionStatus.FILE_CONVERT_FAIL);
        return mediaContentType[0].toUpperCase();
    }

    public String getNewFileName(String originalFileName) {
        // saveFileName 생성
        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString() + originalFileName;

        return saveFileName;
    }

    /*
     * 업로드하려는 위치에 폴더가 없으면 생성한다.
     * 1 depth만 생성함
     */
    public void makeFolder(String saveFolder) {
        File folder = new File(saveFolder);

        if (!folder.exists()) {
            try {
                folder.mkdir(); // 폴더 생성
                System.out.println("폴더가 생성되었습니다.");
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    public String getFolderName() {
        return new SimpleDateFormat("yyMMdd").format(new Date());
    }

    public String getFolder(String realPath, String today) {
        return realPath + today;
    }

    /*
     * S3에서 업로드할 때 폴더는 파일타입/유저아이디/파일타입/으로 한다
     */
    public static String findFolder(String userId, String contentType) {
        String folder = contentType + "/" + userId + "/" + contentType + "/";
        log.info("folder Name : " + folder);
        return folder;
    }

    public String upload(String originalFileName, MultipartFile multipartFile, File folder)
            throws Exception {
        String saveFileName =
                UUID.randomUUID().toString()
                        + originalFileName.substring(originalFileName.lastIndexOf('.'));
        //		fileInfoDto.setSaveFolder(today);
        //		fileInfoDto.setOriginalFile(originalFileName);
        //		fileInfoDto.setSaveFile(saveFileName);
        // logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(),
        // saveFileName);

        multipartFile.transferTo(new File(folder, saveFileName));

        return saveFileName;
    }
}
