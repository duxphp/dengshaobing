package com.tom.dengshaobing.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.tom.dengshaobing.service.CommonService;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月28日 下午5:30:03
 *
 */
@Component
public class BaseController {

	@Autowired
	protected CommonService commonService;

	protected final String SxTableMeta = "tableMeta";// 页面tableMeta变量(表格数据列表:TableMeta)

	protected final String SxFormData = "formData";// 页面formData变量(新增，修改时的form:MapForm)

	protected final String SxMapList = "mapList";// 页面mapList变量(简单数据列表:List<Map<String,
													// Object>>)

	protected final String PxAT = "AT"; // AppToken

	/**
	 * 初始化页面变量AT，并传递
	 * 
	 * @param visitId
	 * @param map
	 * @throws Exception
	 */
	public String pageInit(String AT, String visitId, String visitType, ModelMap map) throws Exception {
		if (StringUtils.isEmpty(AT)) {
			AT = this.getAppToken(visitId, visitType, commonService);
		}
		map.put(PxAT, AT);
		return AT;
	}

	public String getAppToken(String visitId, String visitType, CommonService commonService) throws Exception {
		return commonService.getAppToken(visitId, visitType);
	}
}
