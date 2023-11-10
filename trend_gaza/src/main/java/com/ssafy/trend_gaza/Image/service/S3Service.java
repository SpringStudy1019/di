package com.ssafy.trend_gaza.Image.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.trend_gaza.Image.domain.ImageFile;
import com.ssafy.trend_gaza.Image.dto.ImagesResponse;
import com.ssafy.trend_gaza.Image.exception.ImageException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;
import com.ssafy.trend_gaza.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {

	private static final String CACHE_CONTROL_VALUE = "max-age=3153600";

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    @Value("${cloud.aws.s3.folder}")
//    private String folder;
	
	public S3Service(AmazonS3 amazonS3) {
		this.s3Client = amazonS3;
	}
	
	public List<String> uploadImages(final List<ImageFile> imageFiles) {
        return imageFiles.stream()
                .map(this::uploadImage)
                .collect(Collectors.toList());
    }

	private String uploadImage(final ImageFile imageFile) {
		final MultipartFile file = imageFile.getFile();
        final String path = FileUtil.findFolder(file.getOriginalFilename(), imageFile.getUserId(),
        		FileUtil.findContentType(file.getContentType())) + imageFile.getHashedName();
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imageFile.getContentType());
        metadata.setContentLength(imageFile.getSize());
        metadata.setCacheControl(CACHE_CONTROL_VALUE);

        try (final InputStream inputStream = imageFile.getInputStream()) {
            s3Client.putObject(bucket, path, inputStream, metadata);
        } catch (final AmazonServiceException e) {
            throw new ImageException(CustomExceptionStatus.INVALID_IMAGE_PATH);
        } catch (final IOException e) {
            throw new ImageException(CustomExceptionStatus.INVALID_IMAGE);
        }
        return imageFile.getHashedName();
	}
}
