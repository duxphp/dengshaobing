package com.tom.dengshaobing.service.eggshop;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tom.dengshaobing.common.bo.sys.TableMeta;

/**
 * @author TommyDeng <250575979@qq.com>
 * @version 创建时间：2016年9月29日 上午10:33:46
 *
 */

public interface EggShopBussService {

	/**
	 * 查询用户订单
	 * 
	 * @param userUC
	 * @return
	 */
	List<Map<String, Object>> getOrderList(String AT, String orderStatus);

	/**
	 * 废弃order
	 * 
	 * @param orderUC
	 * @param userUC
	 * @throws Exception
	 */
	void discardOrder(UUID orderUC, String AT) throws Exception;

	/**
	 * 删除order
	 * 
	 * @param orderUC
	 * @param userUC
	 * @throws Exception
	 */
	void deleteOrder(UUID orderUC, String AT) throws Exception;

	/**
	 * 获取cart信息
	 * 
	 * @param AT
	 * @return
	 */
	Map<String, Object> getShoppingCartInfo(String AT);

	/**
	 * 添加到cart
	 * 
	 * @param productUC
	 * @param productCount
	 * @param AT
	 * @return
	 */
	Long addItemShoppingCart(UUID productUC, Long productCount, String AT);

	/**
	 * 获取cart信息
	 * 
	 * @param AT
	 * @return
	 */
	List<Map<String, Object>> listShoppingCart(String AT);

	/**
	 * 变更cart项数量
	 * 
	 * @param fromString
	 * @param parseInt
	 * @param aT
	 * @return
	 * @throws Exception
	 */
	Long changeCartItemQty(UUID cartUC, Long productCount, String AT) throws Exception;

	/**
	 * 删除cart项
	 * 
	 * @param cartItemUC
	 * @param aT
	 * @throws Exception
	 */
	void deleteCartItem(UUID cartItemUC, String AT) throws Exception;

	/**
	 * 选择/取消 cart项
	 * 
	 * @param cartItemUC
	 * @param AT
	 * @param selected
	 */
	void selectCartItem(UUID cartItemUC, String AT);

	/**
	 * 全部选择/取消 cart项
	 * 
	 * @param AT
	 * @param selected
	 */
	void selectAllCartItem(String AT, boolean selected);


	/**
	 * 获取用户微信信息
	 * 
	 * @param AT
	 * @return
	 */
	Map<String, Object> getWeixinUserInfo(String AT);

	/**
	 * 查询用户订单数量
	 * @param AT
	 * @return
	 */
	Map<String, Object> getOrderCountInfo(String AT);
	
	/**
	 * 获取用户配送地址列表
	 * 
	 * @param aT
	 * @return
	 */
	List<Map<String, Object>> getUserDeliveryAddressList(String AT);

	/**
	 * 获取选中的购物车中商品项作为下单的配送列表
	 * 
	 * @param AT
	 * @return
	 */
	List<Map<String, Object>> getSelectedItemList(String AT);

	/**
	 * 生成订单
	 * 
	 * @param AT
	 * @param selectedAddressUC
	 * @param paymentType
	 * @param ipAddress
	 * @return
	 */
	UUID submitOrder(String AT, UUID selectedAddressUC, String paymentType, String ipAddress);

}
