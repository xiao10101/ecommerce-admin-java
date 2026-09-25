package com.george.java.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.george.java.auth.jwt.JwtService;
import com.george.java.common.exception.BusinessException;
import com.george.java.common.result.ErrorCode;
import com.george.java.common.result.R;
import com.george.java.dto.LoginRequest;
import com.george.java.dto.LoginResponse;
import com.george.java.system.entity.SysUser;
import com.george.java.system.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/auth/login")
    public R<LoginResponse> login(@RequestBody LoginRequest req) {
        SysUser user = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, req.username()));

        if (user == null || user.getStatus() != 1) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED,  "用户名或密码错误");
        }
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED,  "用户名或密码错误");
        }

        String token = jwtService.generate(user.getUsername(), user.getId());

        return R.ok(new LoginResponse(token, "Bearer", 60));
    }
}
