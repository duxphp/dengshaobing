package com.tom.dengshaobing.service;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tom.dengshaobing.common.bo.sys.TableMeta;
import com.tom.dengshaobing.common.bo.wmp.json.Errorable;
import com.tom.dengshaobing.service.eggshop.EggShopBussService;
import com.tom.dengshaobing.sqlstatements.SqlStatements;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月14日 上午11:31:23
 *
 */
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private Environment env;

	@Autowired
	DataAccessService dataAccessService;

	@Autowired
	EggShopBussService bussService;

	@Autowired
	ServletContext servletContext;

	@Override
	@Transactional
	public void logVisit(String visitorName, String deviceType) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name".toUpperCase(), visitorName);
		paramMap.put("remark".toUpperCase(), deviceType);
		dataAccessService.insertSingle("LOG_VISIT", paramMap);
	}

	@Override
	public TableMeta listVisit() {
		return dataAccessService.queryTableMeta(SqlStatements.get("002"), new HashMap<>());
	}

	@Override
	public void logErrorable(String uri, Errorable error) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uri".toUpperCase(), uri);
		paramMap.put("errorable".toUpperCase(), error.toString());
		paramMap.put("create_time".toUpperCase(), Calendar.getInstance());
		dataAccessService.insertSingle("LOG_ERRORABLE", paramMap);
	}

	@Override
	public String getAppTokenByEntranceId(String entranceId, String entranceType) throws Exception {
		// 简单实现,使用USER_UC作为token
		if (entranceId == null) {
			return entranceId;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("OPENID", entranceId);
		UUID userUC = dataAccessService.queryForOneObject("BUSS001", paramMap, UUID.class);

		return userUC == null ? null : userUC.toString();
	}

	@Override
	public UUID getUserUCByAppToken(String AT) {
		// 暂时使用USER_UC作为token,所以直接返回UUID类型即可
		if (AT == null)
			return null;
		return UUID.fromString(AT);
	}

	@Override
	public Map<String, Object> getWXUserInfo(String AT) throws Exception {
		UUID userUC = getUserUCByAppToken(AT);
		return dataAccessService.queryRowMapById("SYS_USERINFO_WX", userUC);
	}

	@Override
	public UUID storeUploadFile(CommonsMultipartFile thumbnailFile) throws Exception {
		UUID storedUUID = UUID.randomUUID();
		String extendsion = FilenameUtils.getExtension(thumbnailFile.getOriginalFilename());

		String filePath = env.getProperty("FileStore.BaseFolder") + storedUUID.toString()
				+ FilenameUtils.EXTENSION_SEPARATOR + extendsion;

		File destinationFile = new File(servletContext.getRealPath(filePath));

		FileUtils.copyInputStreamToFile(thumbnailFile.getInputStream(), destinationFile);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("UNIQUE_CODE", storedUUID);
		paramMap.put("NAME", thumbnailFile.getOriginalFilename());
		paramMap.put("PATH", filePath);
		paramMap.put("FILE_EXTENSION", extendsion);
		paramMap.put("FILE_SIZE", thumbnailFile.getSize());
		paramMap.put("REMARK", thumbnailFile.getContentType() + ";" + destinationFile.getAbsolutePath());

		dataAccessService.insertSingle("SYS_FILE_STORE_MAPPING", paramMap);
		return storedUUID;
	}
}
