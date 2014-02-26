package com.dreammore.framework.common.model;

import java.io.Serializable;

public class SearchParameter implements Serializable {

	private static final long serialVersionUID = -8751700867826345332L;

	private String name;
	private String value;

	public SearchParameter(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}