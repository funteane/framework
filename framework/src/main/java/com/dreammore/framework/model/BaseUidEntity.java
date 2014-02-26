package com.dreammore.framework.model;

import java.io.Serializable;

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
public abstract class BaseUidEntity<T> implements Serializable {

	@Id
	@GenericGenerator(name="uu_id", strategy="uuid")
	@GeneratedValue(generator="uu_id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	
} 
