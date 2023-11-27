package com.ssafy.trend_gaza.attraction.controller;


import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.service.AttractionService;
import com.ssafy.trend_gaza.common.CommonResponse;
import com.ssafy.trend_gaza.common.ResponseService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/attractions")
public class AttractionAdminController {

    @Value("${file.path}")
    private String uploadPath;

    private final AttractionService attractionService;
    private final ResponseService responseService;

    public AttractionAdminController(
            AttractionService attractionService, ResponseService responseService) {
        this.attractionService = attractionService;
        this.responseService = responseService;
    }

    @PostMapping
    public ResponseEntity<Void> createAttraction(
            @RequestBody AttractionAdminRequest attractionAdminRequest) {
        attractionService.registerAdminAttraction(attractionAdminRequest);
        return ResponseEntity.created(URI.create("/admin/attractions")).build();
    }

    @PostMapping("/upload")
    public ResponseEntity<CommonResponse> uploadAttractionImage(
            @RequestPart("image") MultipartFile multipartFile) throws Exception {
        if (multipartFile.isEmpty() || multipartFile.getOriginalFilename().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String imageUrl = attractionService.uploadAttractionImage(multipartFile, uploadPath);

        return ResponseEntity.ok().body(responseService.getDataResponse(imageUrl));
    }
}
