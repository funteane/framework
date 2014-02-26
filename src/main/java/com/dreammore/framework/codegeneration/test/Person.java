package com.dreammore.framework.codegeneration.test;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;


public class Person {
	
	@Comment(value = "姓名", searchable = true)
	@Validate(required = true, maxlength = "10", minlength = "6", unique = true)
	private String name;
	
	@Comment(value = "年龄", searchable = true)
	@Validate(required = true)
	private int age;

	@Comment(value ="薪水", searchable = true)
	@Validate(required = true, min = "3500.0")
	private double salary;
	
	@Comment("描述")
	@Validate(required=true, maxlength = "250")
	private String description;
	
	@Comment("地址")
	@Validate(required=true)
	private String address;
	
	@Comment("邮政编码")
	@Validate(required=true, maxlength = "6", minlength = "6")
	private String postcode;
	
	@Comment("电话")
	@Validate(required=true, unique = true)
	private String telephone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	
	
	

}
