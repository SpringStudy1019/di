package com.ssafy.trend_gaza.Image.domain;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trend_gaza.Image.exception.ImageException;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;

public class ImageFile {

	private static final String EXTENSION_DELIMITER=".";
	
	private final MultipartFile file;
	private final String hashedName;
	private final String userId;
	
	public ImageFile(final MultipartFile file, final String userId) {
        validateNullImage(file);
        this.file = file;
        this.hashedName = hashName(file);
        this.userId = userId;
    }

    private void validateNullImage(final MultipartFile file) {
        if (file.isEmpty()) {
            throw new ImageException(CustomExceptionStatus.NULL_IMAGE);
        }
    }

    public static String getExtensionDelimiter() {
		return EXTENSION_DELIMITER;
	}

	private String hashName(final MultipartFile image) {
        final String name = image.getOriginalFilename();
        final String filenameExtension = name.substring(name.lastIndexOf('.'));
        final String nameAndDate = name + LocalDateTime.now();
        try {
            final MessageDigest hashAlgorithm = MessageDigest.getInstance("SHA3-256");
            final byte[] hashBytes = hashAlgorithm.digest(nameAndDate.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes) + filenameExtension;
        } catch (final NoSuchAlgorithmException e) {
            throw new ImageException(CustomExceptionStatus.FAIL_IMAGE_NAME_HASH);
        }
    }

    private String bytesToHex(final byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> String.format("%02x", bytes[i] & 0xff))
                .collect(Collectors.joining());
    }
    
    public String getUserId() {
		return userId;
	}

    public String getContentType() {
        return this.file.getContentType();
    }

    public long getSize() {
        return this.file.getSize();
    }

    public InputStream getInputStream() throws IOException {
        return this.file.getInputStream();
    }


	public MultipartFile getFile() {
		return file;
	}

	public String getHashedName() {
		return hashedName;
	}
    
    
}
