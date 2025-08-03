package com.lease.web.app.controller.history;

import com.lease.common.result.Result;
import com.lease.web.app.vo.history.HistoryItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "浏览历史管理")
@RequestMapping("/app/history")
public class BrowsingHistoryController {

    @Operation(summary = "获取浏览历史")
    @GetMapping("pageItem")
    private Result<Page<HistoryItemVo>> page(@RequestParam int current, @RequestParam int size) {
        return Result.ok();
    }
}
