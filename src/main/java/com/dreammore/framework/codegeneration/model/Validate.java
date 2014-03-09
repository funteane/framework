package com.dreammore.framework.codegeneration.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Validate {
	
	public String max() default "";
	public String min () default "";
	public String maxlength() default "";
	public String minlength() default "";
	public boolean number() default false;
	public boolean doublenumber() default false;
	public boolean integernumber() default false;
	public boolean unique() default false;
	public boolean email() default false;
	public boolean required() default false;
	public String[] rangelength() default {};
	public String[] range()  default {};
	
	
	
	

}
