package com.dreammore.framework.common.web;


import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * @author 
 *
 */
public class WebDataBindingInitializer implements  WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
    		System.out.println("init binder =======================");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
			binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, false));
			binder.registerCustomEditor(Integer.class, null, new	CustomNumberEditor(Integer.class, null, true));
			binder.registerCustomEditor(int.class, null, new CustomNumberEditor(Integer.class, null, true));
			binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
			binder.registerCustomEditor(long.class, null, new CustomNumberEditor(Long.class, null, true));
			binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
			binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
			binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
			binder.registerCustomEditor(String.class, new MyStringEditor());

	}
}

class MyStringEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("------------------------------------------------- " + text);
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(text);
		}
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		if (value == null) {
			return "";
		}
		return value.toString();
	}
}
