package com.ssafy.trend_gaza.Image.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trend_gaza.Image.domain.ImageFile;
import com.ssafy.trend_gaza.Image.domain.S3ImageEvent;
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
	private final ApplicationEventPublisher publisher;
	
	public UploadService(S3Service s3Service, ApplicationEventPublisher publisher) {
		this.s3Service = s3Service;
		this.publisher = publisher;
	}
	
	public ImagesResponse save(final List<MultipartFile> images, String userId) {
		validateSizeOfImages(images);
		final List<ImageFile> imageFiles = images.stream()
				.map(file -> new ImageFile(file, userId))
				.collect(Collectors.toList());
		final List<String> imageNames = uploadImages(imageFiles);
		return new ImagesResponse(imageNames);
	}
	
	private List<String> uploadImages(final List<ImageFile> imageFiles) {
		try {
			return s3Service.uploadImages(imageFiles);
		} catch (final ImageException e) {
			imageFiles.forEach(imageFile -> publisher.publishEvent(new S3ImageEvent(imageFile.getHashedName())));
			throw e;
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
