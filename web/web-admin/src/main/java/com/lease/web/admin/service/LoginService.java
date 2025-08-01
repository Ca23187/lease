package com.lease.web.admin.service;

import com.lease.web.admin.vo.login.CaptchaVo;
import com.lease.web.admin.vo.login.LoginVo;
import com.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);

    SystemUserInfoVo getSystemUserInfoVoById(Long userId);
}
