package com.dutyMS.modules.sys.user.vo;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2018-01-25
 */
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;

    private String deviceId;
    private String isScanLogin;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIsScanLogin() {
        return isScanLogin;
    }

    public void setIsScanLogin(String isScanLogin) {
        this.isScanLogin = isScanLogin;
    }
}
