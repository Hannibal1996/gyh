package com.littlecat.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.littlecat.dao.HistoryRecordMapper;
import com.littlecat.dao.ProductMapper;
import com.littlecat.entity.HistoryRecord;
import com.littlecat.entity.Product;
import com.littlecat.service.ProductService;
import com.littlecat.utils.UuidUtil;
 
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	@Resource(name = "productDao")
	private ProductMapper productDao;
	@Resource(name = "hrDao")
	private HistoryRecordMapper hrDao;
	@Override
	public List<Product> findHotProducts(int isHot, int offset, int rows) {
		// TODO Auto-generated method stub
		return productDao.findHotProducts(isHot, offset, rows);
	}

	@Override
	public List<Product> findNewProductList(int offset, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findProductListByCid(String cid, int offset, int rows) {
		// TODO Auto-generated method stub
		return productDao.findProductListByPage(cid, offset, rows);
	}

	@Override
	public int productCount(String cid) {
		// TODO Auto-generated method stub
		return productDao.productCount(cid);
	}

	@Override
	public Product findProductById(String pid) {
		// TODO Auto-generated method stub
		return productDao.selectByPrimaryKey(pid);
	}

	@Override
	public List<Object> findProductListByName(String pname, int offset, Integer rows) {
		// TODO Auto-generated method stub
		return productDao.findProductListByName(pname,offset,rows);
	}

	@Override
	public int productCountByName(String pname) {
		// TODO Auto-generated method stub
		return productDao.productCountByName(pname);
	}

	@Override
	public int addHistoryRecored(String  pid,String uid) {
		// TODO Auto-generated method stub
		
		HistoryRecord hr=hrDao.findHRByPidUid(pid,uid);
		Date date = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date2 = temp.format(date);
		Date date3;
		try {
			date3 = temp.parse(date2);
	
		if(hr!=null){
			hr.setTime(date3);
			
			return hrDao.updateByPrimaryKey( hr);
		}else{
			HistoryRecord hisR=new HistoryRecord();
			hisR.sethId(UuidUtil.get32UUID());
			hisR.setTime(date3);
			hisR.setuId(uid);
			hisR.setpId(pid);
			return hrDao.insertSelective(hisR);	
		}
		} catch (ParseException e) {
		 
		return 0;
		}
		
	}

	@Override
	public List<HistoryRecord> findByUid(String uid, int offset, int rows) {
		// TODO Auto-generated method stub
		return hrDao.findByUid(uid, offset, rows);
	}

	@Override
	public int hrCount(String uid) {
		// TODO Auto-generated method stub
		return hrDao.hrCount(uid);
	}

	@Override
	public int delHistory(String uid) {
		// TODO Auto-generated method stub
		return hrDao.deleteByuid(uid);
	}

	 
 
 
}
