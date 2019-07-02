package entity;


import java.util.HashSet;
import java.util.Set;

public class Classify {
	private String cid;
	private String cname;
	private String cdesc;
	//在分类里面表示多个商品，因为一个分类有多个商品
	//hibernate中要求用set集合表示多个商品
	private Set<Goods> setGoods = new HashSet<Goods>();
	
	public Set<Goods> getSetGoods() {
		return setGoods;
	}
	public void setSetGoods(Set<Goods> setGoods) {
		this.setGoods = setGoods;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCdesc() {
		return cdesc;
	}
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

}
