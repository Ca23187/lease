package com.lease.web.admin.service.impl;

import com.lease.common.constant.RedisConstant;
import com.lease.common.exception.LeaseException;
import com.lease.common.result.ResultCodeEnum;
import com.lease.common.utils.JwtUtil;
import com.lease.model.entity.SystemUser;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.repository.SystemUserRepository;
import com.lease.web.admin.service.LoginService;
import com.lease.web.admin.vo.login.CaptchaVo;
import com.lease.web.admin.vo.login.LoginVo;
import com.lease.web.admin.vo.system.user.SystemUserInfoVo;
import com.wf.captcha.SpecCaptcha;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    private final StringRedisTemplate redisTemplate;
    private final SystemUserRepository repository;

    @Autowired
    public LoginServiceImpl(StringRedisTemplate redisTemplate, SystemUserRepository repository) {
        this.redisTemplate = redisTemplate;
        this.repository = repository;
    }

    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 40, 4);
        String code = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        String image = specCaptcha.toBase64();
        redisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        return new CaptchaVo(image, key);
    }

    @Override
    public String login(LoginVo loginVo) {
        //1.是否输入验证码
        if (loginVo.getCaptchaCode() == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        //2.校验验证码
        String code = redisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }

        if (!code.equals(loginVo.getCaptchaCode().toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        //3.校验用户是否存在
        SystemUser systemUser = repository.findByUsername(loginVo.getUsername());

        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }

        //4.校验用户是否被禁
        if (systemUser.getStatus() == BaseStatus.DISABLE) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        //5.校验用户密码
        if (!systemUser.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }

        //6.创建并返回TOKEN
        return JwtUtil.createToken(systemUser.getId(), systemUser.getUsername());
    }

    @Override
    public SystemUserInfoVo getSystemUserInfoVoById(Long userId) {
        return repository.findSystemUserInfoVoById(userId);
    }
}
