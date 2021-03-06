package com.tom.dengshaobing.common.bo.wmp.json;

import com.tom.dengshaobing.common.bo.wmp.type.Oauth2Scope;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月26日 下午5:48:32
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Oauth2AccessToken extends Errorable {
	private static final long serialVersionUID = -7473308181839434498L;

	public String access_token;

	public Long expires_in;

	public String refresh_token;

	public String openid;

	public Oauth2Scope scope;

}
