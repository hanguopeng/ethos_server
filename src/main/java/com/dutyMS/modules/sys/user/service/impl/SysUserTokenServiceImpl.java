package com.dutyMS.modules.sys.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.common.utils.R;
import com.dutyMS.modules.sys.user.dao.SysUserTokenDao;
import com.dutyMS.modules.sys.user.entity.SysUserTokenEntity;
import com.dutyMS.modules.sys.utils.oauth2.TokenGenerator;
import com.dutyMS.modules.sys.user.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	//一小时未操作需要重新登录
	@Value("${ethos.login.expireTime}")
	private Integer EXPIRE;


	@Override
	public R createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = this.selectById(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			this.insert(tokenEntity);
		}else{
			if(tokenEntity.getExpireTime().getTime()<now.getTime()){
				tokenEntity.setToken(token);
				tokenEntity.setUpdateTime(now);
				tokenEntity.setExpireTime(expireTime);
			}else{
				tokenEntity.setUpdateTime(now);
				tokenEntity.setExpireTime(expireTime);
			}
			//更新token
			this.updateById(tokenEntity);
		}

		R r = R.ok().put("token", tokenEntity.getToken()).put("expire", EXPIRE);

		return r;
	}

	@Override
	public void logout(long userId) {
		// modify by Mela.S @20200305 退出时不修改token

		//生成一个token
//		String token = TokenGenerator.generateValue();

		//修改token
//		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
//		tokenEntity.setUserId(userId);
//		tokenEntity.setToken(token);
//		this.updateById(tokenEntity);
	}
}
