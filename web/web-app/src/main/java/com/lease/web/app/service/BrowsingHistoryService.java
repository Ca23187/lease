package com.lease.web.app.service;

import com.lease.web.app.vo.history.HistoryItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @description 针对表【browsing_history(浏览历史)】的数据库操作Service
*/
public interface BrowsingHistoryService {
    Page<HistoryItemVo> pageItemByUserId(Long userId, Pageable pageable);

    void saveHistory(Long userId, Long roomId);
}
