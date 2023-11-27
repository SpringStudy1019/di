package com.ssafy.trend_gaza.bookmark.service;


import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.bookmark.dto.BookmarkRequest;
import com.ssafy.trend_gaza.bookmark.entity.Bookmark;
import com.ssafy.trend_gaza.bookmark.repository.BookmarkMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private BookmarkMapper bookmarkMapper;

    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper) {
        super();
        this.bookmarkMapper = bookmarkMapper;
    }

    @Override
    public int register(BookmarkRequest bookmarkRequest) throws Exception {
        int result = 0;
        Bookmark bookmark = bookmarkMapper.check(bookmarkRequest);
        if (bookmark == null) {
            result = bookmarkMapper.register(bookmarkRequest);
        }
        return result;
    }

    @Override
    public int delete(Map<String, Object> param) throws Exception {
        int result = 0;
        String userId = (String) param.get("userId");
        Integer contentId = (Integer) param.get("contentId");

        BookmarkRequest bookmarkRequest = new BookmarkRequest();
        bookmarkRequest.setUserId(userId);
        bookmarkRequest.setContentId(contentId);
        Bookmark bookmark = bookmarkMapper.check(bookmarkRequest);
        if (bookmark != null) {
            result = bookmarkMapper.delete(param);
        }
        return result;
    }

    @Override
    public List<AttractionInfo> list(String userId) throws Exception {
        return bookmarkMapper.list(userId);
    }
}
