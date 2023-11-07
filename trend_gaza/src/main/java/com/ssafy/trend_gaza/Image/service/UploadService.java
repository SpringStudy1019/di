package com.ssafy.trend_gaza.Image.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trend_gaza.Image.domain.ImageFile;
import com.ssafy.trend_gaza.Image.dto.ImagesResponse;
import com.ssafy.trend_gaza.Image.exception.ImageException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadService {

	private static final int MAX_IMAGE_LIST_SIZE = 5;
	private static final int EMPTY_LIST_SIZE = 0;
	
	private final S3Service s3Service;
	
	public UploadService(S3Service s3Service) {
		this.s3Service = s3Service;
	}
	
	public ImagesResponse save(final List<MultipartFile> images) {
		validateSizeOfImages(images);
		final List<ImageFile> imageFiles = images.stream()
				.map(ImageFile::new)
				.toList();
		final List<String> imageNames = uploadImages(imageFiles);
		return new ImagesResponse(imageNames);
	}
	
	private List<String> uploadImages(final List<ImageFile> imageFiles) {
		try {
			return s3Service.uploadImages(imageFiles);
		} catch (final ImageException e) {
			imageFiles.forEach(imageFile -> publisher.publishEvent(new S3ImageEvent(imageFile.getHashedName())));
		}
	}
	
	private void validateSizeOfImages(final List<MultipartFile> images) {
		if (images.size() > MAX_IMAGE_LIST_SIZE) {
			throw new ImageException(CustomExceptionStatus.EXCEED_IMAGE_LIST_SIZE);
		}
		if(images.size() == EMPTY_LIST_SIZE) {
			throw new ImageException(CustomExceptionStatus.EMPTY_IMAGE_LIST);
		}
	}

}
