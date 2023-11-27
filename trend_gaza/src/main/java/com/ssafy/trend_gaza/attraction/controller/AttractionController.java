package com.ssafy.trend_gaza.attraction.controller;


import com.ssafy.trend_gaza.attraction.dto.AttractionAutoSearchResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionCategoryResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionDetailResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionPlanResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionResponse;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.attraction.service.AttractionService;
import com.ssafy.trend_gaza.common.CommonResponse;
import com.ssafy.trend_gaza.common.CustomExceptionStatus;
import com.ssafy.trend_gaza.common.ResponseService;
import com.ssafy.trend_gaza.util.AuthenticationUtil;
import com.ssafy.trend_gaza.util.TrieAlgorithmUtil.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("/attractions")
@CrossOrigin("*")
public class AttractionController {

    private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

    private final AttractionService attractionService;
    private final ResponseService responseService;

    public AttractionController(
            AttractionService attractionService, ResponseService responseService) {
        this.attractionService = attractionService;
        this.responseService = responseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionDetailResponse> findAttraction(@PathVariable int id) {
        return ResponseEntity.ok(attractionService.findAttraction(id));
    }

    /*
     * 검색 결과로 보여주는 여행지 리스트
     */
    @GetMapping
    public ResponseEntity<List<AttractionResponse>> findAttractions(
            @RequestParam Map<String, String> map) {
        return ResponseEntity.ok(new ArrayList<AttractionResponse>());
    }

    /*
     * 여행 계획을 세울 때 보이는 여행지 리스트
     */
    @GetMapping("/plans")
    public ResponseEntity<List<AttractionPlanResponse>> getAttractionPlans(
            @RequestParam Map<String, String> map) {
        return ResponseEntity.ok(attractionService.getAttractionPlanResponse(map));
    }

    @GetMapping("/trend")
    public ResponseEntity<List<AttractionResponse>> findTrendAttractions() {
        return ResponseEntity.ok(new ArrayList<AttractionResponse>());
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchAttractions(@RequestParam Map<String, String> map) {
        logger.debug("searchAttractions call!");
        return ResponseEntity.ok(attractionService.searchAttractions(map));
    }

    @GetMapping("/auto-search")
    public ResponseEntity<CommonResponse> AutoSearchAttractions(@RequestParam String keyword) {
        Node node = attractionService.search(keyword);
        if (node == null) {
            return ResponseEntity.ok().build();
        }
        List<AttractionAutoSearchResponse> result = attractionService.autoComplete(keyword, node);

        return ResponseEntity.ok(responseService.getDataResponse(result));
    }

    @PostMapping("/{attractionId}/bookmark")
    public ResponseEntity<?> onBookmark(@PathVariable int attractionId) throws Exception {
        String userId = AuthenticationUtil.getCurrentUserSocialId();
        int result = attractionService.onBookmark(attractionId, userId);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            responseService.getExceptionResponse(
                                    CustomExceptionStatus.REGISTER_BOOKMARK_FAILED));
        }
        return ResponseEntity.ok().body(responseService.getSuccessResponse());
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<?> listByCategory(@RequestParam Map<String, String> map) {
        logger.debug("searchAttractions call!");
        AttractionCategoryResponse list = attractionService.listByCategory(map);
        return ResponseEntity.ok(list);
    }

    /*
     * 검색어를 중점으로 두고 검색.
     */
    @GetMapping("/searchByWord")
    public ResponseEntity<?> searchByWord(@RequestParam String keyword) {
        List<AttractionInfo> list = attractionService.searchByword(keyword);
        return ResponseEntity.ok(list);
    }

    /*
     * 사용자들이 올린 이미지 가져오기
     */
    @GetMapping("/image/{contentId}")
    public ResponseEntity<?> getUserImage(@PathVariable int contentId) throws Exception {
        // reviewIdx와 이미지 경로 주소를 리턴해야 함
        List<Map<String, String>> list = attractionService.getUserImage(contentId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/plan/{planIdx}/bookmark")
    public ResponseEntity<?> getParticipantsBookmarks(@PathVariable int planIdx) throws Exception {
        return ResponseEntity.ok(attractionService.findBookmarkAndAttractionByUserIds(planIdx));
    }
}
