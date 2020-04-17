package com.dutyMS.modules.sys.login.controller;

import com.dutyMS.common.utils.R;
import com.dutyMS.modules.sys.utils.controller.AbstractController;
import com.dutyMS.modules.sys.user.entity.SysUserEntity;
import com.dutyMS.modules.sys.user.vo.SysLoginForm;
import com.dutyMS.modules.sys.user.service.SysUserService;
import com.dutyMS.modules.sys.user.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
@Api(tags = "登录接口")
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    /**
     * 登录
     */
    @PostMapping("/sys/login")
    @ApiOperation("登录")
    public Map<String, Object> login(@RequestBody SysLoginForm form) throws IOException {
        SysUserEntity user;
        user = sysUserService.queryByUserName(form.getUsername());
        if(user==null){
            return R.error("账号不存在");
        }else if(!user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())){
            return R.error("密码不正确");
        }
        //账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }
        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());
        r.put("user",user);
        return r;
    }


    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public R logout() {
        sysUserTokenService.logout(getUserId());
        return R.ok();
    }



}
