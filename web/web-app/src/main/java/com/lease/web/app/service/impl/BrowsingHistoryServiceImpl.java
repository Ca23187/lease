package com.lease.web.app.service.impl;

import com.lease.model.entity.BrowsingHistory;
import com.lease.web.app.repository.BrowsingHistoryRepository;
import com.lease.web.app.repository.GraphInfoRepository;
import com.lease.web.app.vo.graph.GraphVo;
import com.lease.web.app.vo.history.HistoryItemVo;
import com.lease.web.app.vo.graph.RoomGraphVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 针对表【browsing_history(浏览历史)】的数据库操作Service实现
 */
@Service
public class BrowsingHistoryServiceImpl implements com.lease.web.app.service.BrowsingHistoryService {

    private final BrowsingHistoryRepository browsingHistoryRepository;
    private final GraphInfoRepository graphInfoRepository;

    @Autowired
    public BrowsingHistoryServiceImpl(BrowsingHistoryRepository browsingHistoryRepository, GraphInfoRepository graphInfoRepository) {
        this.browsingHistoryRepository = browsingHistoryRepository;
        this.graphInfoRepository = graphInfoRepository;
    }

    @Override
    public Page<HistoryItemVo> pageItemByUserId(Long userId, Pageable pageable) {
        Page<HistoryItemVo> page = browsingHistoryRepository.pageItemByUserId(userId, pageable);

        List<Long> roomIds = page.stream().map(HistoryItemVo::getRoomId).toList();
        Map<Long, List<GraphVo>> graphMap = graphInfoRepository.findRoomGraphVoByRoomIds(roomIds)
                .stream()
                .collect(Collectors.groupingBy(
                        RoomGraphVo::getRoomId,
                        Collectors.mapping(vo -> new GraphVo(vo.getName(), vo.getUrl()), Collectors.toList())
                ));
        for (HistoryItemVo r : page) {
            r.setRoomGraphVoList(graphMap.getOrDefault(r.getRoomId(), List.of()));
        }
        return page;
    }

    @Override
    @Transactional
    @Async
    public void saveHistory(Long userId, Long roomId) {
        if (browsingHistoryRepository.existsByUserIdAndRoomId(userId, roomId)) {
            browsingHistoryRepository.updateBrowseTimeByUserIdAndRoomId(userId, roomId);
        }
        else {
            BrowsingHistory browsingHistory = new BrowsingHistory();
            browsingHistory.setUserId(userId);
            browsingHistory.setRoomId(roomId);
            browsingHistory.setBrowseTime(new Date());
            browsingHistoryRepository.save(browsingHistory);
        }
    }
}