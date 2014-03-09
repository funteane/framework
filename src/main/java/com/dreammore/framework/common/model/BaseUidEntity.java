package com.dreammore.framework.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


/**
 * 主键id为uuid的entity基类.
 * 
 * @author 
 * @param <T>
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseUidEntity implements Serializable {

	@Id
	@GenericGenerator(name="uu_id", strategy="uuid")
	@GeneratedValue(generator="uu_id")
	@Column(name = "ID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
	
} 
