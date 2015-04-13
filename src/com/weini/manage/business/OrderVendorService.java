package com.weini.manage.business;

import java.util.List;

import com.weini.manage.dao.OrderVendorDao;
import com.weini.manage.entity.TOrder;

public class OrderVendorService extends GeneralService{
	private OrderVendorDao orderVendorDao = new OrderVendorDao(this.session);

	public List<TOrder> getOrderList(int vendorId,String flag){
		List<TOrder> res = null;
	    res = this.orderVendorDao.getOrderList(vendorId,flag);

		this.close();
		return res;
	}
}
