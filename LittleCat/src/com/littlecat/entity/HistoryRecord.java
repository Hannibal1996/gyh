package com.littlecat.entity;

import java.io.Serializable;
import java.util.Date;

public class HistoryRecord implements Serializable {
    private String hId;

    private String uId;

    private String pId;

    private Date time;
private Product product;
    private static final long serialVersionUID = 1L;

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId == null ? null : hId.trim();
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}