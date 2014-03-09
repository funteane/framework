package com.dreammore.framework.codegeneration.test;

import java.io.Serializable;
import java.util.Date;

import javax.sound.midi.Track;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;

/**
 * 冷库或基地费率
 * @author dreamone
 *
 */

public class Rate implements Serializable{
	
	private static final long serialVersionUID = 6537602147745368706L;

	
	private Long id;
	
	@Comment(value = "费率")
	@Validate(doublenumber = true, required = true)
	private Double price;  //费用
	
	@Comment(value = "是否活体")
	private boolean live = false; //是否活体 
	
	@Comment(value = "是否使用")
	private boolean active = false; //是否使用

	@Comment(value = "创建日期")
	@Validate(required = true)
	private Date createDate; //创建日期
	
	
	private String operator; //操作者

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Rate [id=" + id + ", price=" + price + ", live=" + live
				+ ", active=" + active + ", createDate=" + createDate
				+ ", operator=" + operator + "]";
	}


}
