package com.tom.dengshaobing.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tom.dengshaobing.common.DefaultSetting;
import com.tom.dengshaobing.common.bo.wmp.json.AccessToken;
import com.tom.dengshaobing.common.bo.wmp.json.Oauth2AccessToken;
import com.tom.dengshaobing.common.bo.wmp.xml.MessageXml;
import com.tom.utils.JsonParseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月19日 上午10:35:02
 *
 */
@Slf4j
@Service
public class WexinMessagePlatformServiceImpl implements WexinMessagePlatformService {

	@Autowired
	private Environment env;

	@Autowired
	CommonService commonService;

	@Autowired
	WmpBussService wmpBussService;

	@Autowired
	HttpProcessSerice httpProcessSerice;

	@Override
	public MessageXml dispatch(MessageXml message) {
		if (message == null) {
			return null;
		}
		String eventKey = message.EventKey == null ? "" : message.EventKey;

		MessageXml returnMessage = null;
		switch (eventKey) {

		case "rselfmenu_1_1":
			returnMessage = wmpBussService.processMenu_1_1(message);
			break;
		case "rselfmenu_1_2":
			returnMessage = wmpBussService.processMenu_1_2(message);
			break;
		case "rselfmenu_1_3":
			returnMessage = wmpBussService.processMenu_1_3(message);
			break;
		case "rselfmenu_1_4":
			returnMessage = wmpBussService.processMenu_1_4(message);
			break;
		case "rselfmenu_1_5":
			returnMessage = wmpBussService.processMenu_1_5(message);
			break;

		case "rselfmenu_2_1":
			returnMessage = wmpBussService.processMenu_2_1(message);
			break;
		case "rselfmenu_2_2":
			returnMessage = wmpBussService.processMenu_2_2(message);
			break;
		case "rselfmenu_2_3":
			returnMessage = wmpBussService.processMenu_2_3(message);
			break;
		case "rselfmenu_2_4":
			returnMessage = wmpBussService.processMenu_2_4(message);
			break;
		case "rselfmenu_2_5":
			returnMessage = wmpBussService.processMenu_2_5(message);
			break;

		case "rselfmenu_3_1":
			returnMessage = wmpBussService.processMenu_3_1(message);
			break;
		case "rselfmenu_3_2":
			returnMessage = wmpBussService.processMenu_3_2(message);
			break;
		case "rselfmenu_3_3":
			returnMessage = wmpBussService.processMenu_3_3(message);
			break;
		case "rselfmenu_3_4":
			returnMessage = wmpBussService.processMenu_3_4(message);
			break;
		case "rselfmenu_3_5":
			returnMessage = wmpBussService.processMenu_3_5(message);
			break;
		default:
			returnMessage = wmpBussService.processInput(message);
			break;
		}
		return returnMessage;
	}

	AccessToken accessToken;
	AccessTokenStatus accessTokenStatus = AccessTokenStatus.NOT_INIT;

	@Override
	public void setAccessTokenStatus(AccessTokenStatus status) {
		this.accessTokenStatus = status;
	}

	@Override
	public AccessTokenStatus getAccessTokenStatus() {
		return this.accessTokenStatus;
	}

	@Override
	public boolean checkSignature(String signature, String timestamp, String nonce) {
		if (signature == null)
			return false;
		String token = env.getProperty("WeixinPlatform.Token");
		String[] sArray = { token, timestamp, nonce };
		Arrays.sort(sArray);
		String sha1Str = DigestUtils.sha1Hex(StringUtils.join(sArray));
		return signature.equals(sha1Str);
	}

	@Override
	public AccessToken getAccessToken() throws Exception {
		if (accessToken == null) {
			fetchAccessToken();
		}
		return accessToken;
	}

	@Override
	public void fetchAccessToken() throws Exception {
		URI uri = new URIBuilder("https://api.weixin.qq.com/cgi-bin/token")
				.setParameter("grant_type", "client_credential")
				.setParameter("appid", env.getProperty("WeixinPlatform.AppID"))
				.setParameter("secret", env.getProperty("WeixinPlatform.AppSecret")).build();
		String content = httpProcessSerice.httpGet(uri);
		accessToken = JsonParseUtils.generateJavaBean(content, AccessToken.class);
		log.info("AccessToken fetched =======================>");
		log.info(this.accessToken.access_token);
	}

	@Override
	public Oauth2AccessToken getOauth2AccessToken(String code) throws Exception {
		URI uri = new URIBuilder("https://api.weixin.qq.com/sns/oauth2/access_token")
				.setParameter("appid", env.getProperty("WeixinPlatform.AppID"))
				.setParameter("secret", env.getProperty("WeixinPlatform.AppSecret"))
				.setParameter("code", code).setParameter("grant_type", "authorization_code").build();
		String content = httpProcessSerice.httpGet(uri);
		return JsonParseUtils.generateJavaBean(content, Oauth2AccessToken.class);
	}

	@Override
	public List<String> getIPList() throws Exception {
		URI uri = new URIBuilder("https://api.weixin.qq.com/cgi-bin/getcallbackip")
				.setParameter("access_token", getAccessToken().access_token).build();
		String content = httpProcessSerice.httpGet(uri);
		return JsonParseUtils.getListValueByFieldName(content, "ip_list");
	}

	@Override
	public void createMenu(String menuJsonStr) throws Exception {
		URI uri = new URIBuilder("https://api.weixin.qq.com/cgi-bin/menu/create").setCharset(DefaultSetting.CHARSET)
				.setParameter("access_token", getAccessToken().access_token).build();
		httpProcessSerice.httpPost(uri, menuJsonStr);
	}

	@Override
	public void deleteMenu() throws Exception {
		URI uri = new URIBuilder("https://api.weixin.qq.com/cgi-bin/menu/delete")
				.setParameter("access_token", getAccessToken().access_token).build();
		httpProcessSerice.httpGet(uri);
	}

}
