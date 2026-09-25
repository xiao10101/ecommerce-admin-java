package com.george.java.controller;


import com.george.java.common.exception.BusinessException;
import com.george.java.mapper.DbPingMapper;
import com.george.java.common.result.ErrorCode;
import com.george.java.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PingController {

    private final DbPingMapper dbPingMapper;

    @GetMapping("/ping")
    public R<String> ping() {
        return R.ok("pong");
    }

    @GetMapping("/boom")
    public R<Void> boom() {
        throw new BusinessException(ErrorCode.PARAM_ERROR, "这是模拟业务异常");
    }

    @GetMapping("/db-ping")
    public R<Integer> dbPing() {
        return R.ok(dbPingMapper.ping());
    }
}
