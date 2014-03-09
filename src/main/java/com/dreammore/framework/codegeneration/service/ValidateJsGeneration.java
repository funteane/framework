package com.dreammore.framework.codegeneration.service;

import java.lang.reflect.Field;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;

public class ValidateJsGeneration extends AbstractGeneration {

	public ValidateJsGeneration(String filePath) {
		super(filePath);
	}

	@Override
	public StringBuffer generate(Class<?> clazz) {
		StringBuffer sb = new StringBuffer();
		StringBuffer message = new StringBuffer();
		sb.append("jQuery(function(){   ").append(BR);
	    sb.append(getBlanks(1)).append("var validator = jQuery(\"#").append(firstLetterLower(clazz.getSimpleName())).append("Form\").validate({").append(BR);
	    sb.append(getBlanks(2)).append("debug: true, //调试模式取消submit的默认提交功能").append(BR);
	    sb.append(getBlanks(2)).append("errorClass: \"error\",//默认为错误的样式类为：error").append(BR);          
        sb.append(getBlanks(2)).append("focusInvalid: false,").append(BR);
        sb.append(getBlanks(2)).append("onkeyup: false,").append(BR);
        sb.append(getBlanks(2)).append("submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form").append(BR);   
        sb.append(getBlanks(3)).append("form.submit();").append(BR);   
        sb.append(getBlanks(2)).append("},   ").append(BR);   
        sb.append(getBlanks(2)).append("rules: {           //定义验证规则,其中属性名为表单的name属性   ").append(BR) 	;  
        
        Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Validate validate = field.getAnnotation(Validate.class);
			Comment comment = field.getAnnotation(Comment.class);
			if (validate != null) {
				//flag = true;
				sb.append(getBlanks(3)).append("\"").append(field.getName()).append("\": {").append(BR);
				message.append(getBlanks(3)).append("\"").append(field.getName()).append("\": {").append(BR);
				if (validate.required()) {
					sb.append(getBlanks(4)).append("required: ").append(validate.required()).append(",").append(BR);
					message.append(getBlanks(4)).append("required: \"").append(comment.value()).append("不能为空\"").append(",").append(BR);
				}
				if (!"".equals(validate.maxlength())) {
					sb.append(getBlanks(4)).append("maxlength: ").append(validate.maxlength()).append(",").append(BR);
					message.append(getBlanks(4)).append("maxlength: ").append("jQuery.validator.format(\"请输入一个长度最多是 {0} 的字符串\")").append(",").append(BR);
				}
				if (!"".equals(validate.minlength())) {
					sb.append(getBlanks(4)).append("minlength: ").append(validate.minlength()).append(",").append(BR);
					message.append(getBlanks(4)).append("minlength: ").append("jQuery.validator.format(\"请输入一个长度最少是 {0} 的字符串\")").append(",").append(BR);
				}
				if (!"".equals(validate.max())) {
					sb.append(getBlanks(4)).append("max: ").append(validate.max()).append(",").append(BR);
					message.append(getBlanks(4)).append("max: ").append("jQuery.validator.format(\"请输入一个最大为 {0} 的值\")").append(",").append(BR);
				}
				if (!"".equals(validate.min())) {
					sb.append(getBlanks(4)).append("min: ").append(validate.min()).append(",").append(BR);
					message.append(getBlanks(4)).append("min: ").append("jQuery.validator.format(\"请输入一个最小为 {0} 的值\")").append(",").append(BR);
				}			
				if (validate.doublenumber()) {
					sb.append(getBlanks(4)).append("doublenumber: ").append(validate.doublenumber()).append(",").append(BR);
					message.append(getBlanks(4)).append("doublenumber: ").append("\"请输入合法的正数\"").append(",").append(BR);
				}
				if (validate.integernumber()) {
					sb.append(getBlanks(4)).append("integernumber: ").append(validate.integernumber()).append(",").append(BR);
					message.append(getBlanks(4)).append("integernumber: ").append("\"请输入合法的正整数\"").append(",").append(BR);
				}
				if (validate.number()) {
					sb.append(getBlanks(4)).append("number: ").append(validate.number()).append(",").append(BR);
					message.append(getBlanks(4)).append("number: ").append("\"请输入合法的数字\"").append(",").append(BR);
				}
				if (validate.email()) {
					sb.append(getBlanks(4)).append("email: ").append(validate.email()).append(",").append(BR);
					message.append(getBlanks(4)).append("email: ").append("\"请输入正确格式的电子邮件\"").append(",").append(BR);
				}
				if (validate.range().length > 0 ) {
					sb.append(getBlanks(4)).append("range: [").append(validate.range()[0]).append(",").append(validate.range()[1]).append("]").append(BR);
					message.append(getBlanks(4)).append("range: ").append("jQuery.validator.format(\"请输入一个介于 {0} 和 {1} 之间的值\")").append(BR);
				}
				if (validate.rangelength().length > 0 ) {
					sb.append(getBlanks(4)).append("rangelength: [").append(validate.rangelength()[0]).append(",").append(validate.rangelength()[1]).append("]").append(BR);
					message.append(getBlanks(4)).append("rangelength: ").append("jQuery.validator.format(\"请输入一个长度介于 {0} 和 {1} 之间的字符串\")").append(BR);
				}
				if (validate.unique()) {
					sb.append(getBlanks(4)).append("remote: {").append(BR);
					sb.append(getBlanks(5)).append("url: baseurl + \"/").append(clazz.getSimpleName().toLowerCase()).append("/exist").append(firstLetterUpper(field.getName())).append(".do?d=\" + Math.random(),     //后台处理程序").append(BR);
					sb.append(getBlanks(5)).append("type: \"post\", //数据发送方式").append(BR);
	            	sb.append(getBlanks(5)).append("dataType: \"json\",           //接受数据格式").append(BR);  
	            	sb.append(getBlanks(5)).append("data: {").append(BR);
	            	sb.append(getBlanks(6)).append(field.getName()).append(": function(){ return jQuery(\"#").append(field.getName()).append("\").val();}, ").append(BR);
	            	sb.append(getBlanks(6)).append("id: function(){ return jQuery(\"#id\").val();}").append(BR);
	            	sb.append(getBlanks(5)).append("}").append(BR);
	            	sb.append(getBlanks(4)).append("},").append(BR);
	            	message.append(getBlanks(4)).append("remote: \"此值已经存在\",").append(BR);
				}
				
				if (sb.toString().endsWith(",".concat(BR))) {
					sb = sb.deleteCharAt(sb.length() - 1);
					message = message.deleteCharAt(message.length() - 1);
					sb = sb.deleteCharAt(sb.length() - 1);
					message = message.deleteCharAt(message.length() - 1);
					sb.append(BR);
					message.append(BR);
				}

				sb.append(getBlanks(3)).append("},").append(BR);
				message.append(getBlanks(3)).append("},").append(BR);
			}
		}
		
		if (sb.toString().endsWith(",".concat(BR))) {
			sb = sb.deleteCharAt(sb.length() - 1);
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(BR);
			
			message = message.deleteCharAt(message.length() - 1);
			message = message.deleteCharAt(message.length() - 1);
			message.append(BR);
		}
		//message.append(getBlanks(2)).append("},  ").append(BR);
		
		sb.append(getBlanks(2)).append("},  ").append(BR);
		sb.append(getBlanks(2)).append("messages: {       //自定义验证消息").append(BR);
		sb.append(message);
		
		
		sb.append(getBlanks(2)).append("},").append(BR);
		sb.append(getBlanks(2)).append("errorPlacement: function(error, element) {  //验证消息放置的地方").append(BR);
		sb.append(getBlanks(3)).append("error.appendTo( element.parent().next());").append(BR);
		sb.append(getBlanks(2)).append("},   ").append(BR);
		sb.append(getBlanks(2)).append("highlight: function(element, errorClass) {  //针对验证的表单设置高亮").append(BR);
		sb.append(getBlanks(3)).append("jQuery(element).addClass(errorClass);").append(BR);
        sb.append(getBlanks(2)).append("},   ").append(BR);
        sb.append(getBlanks(2)).append("success: function(label) {").append(BR); 
        sb.append(getBlanks(3)).append("label.addClass(\"valid\").text(\"\");").append(BR);
        sb.append(getBlanks(2)).append("}     ").append(BR);
        sb.append(getBlanks(1)).append("}); ").append(BR);
        
        sb.append(getBlanks(1)).append("jQuery(\"#submitButton\").click(function(){").append(BR);
        sb.append(getBlanks(2)).append("validator.resetForm();").append(BR);
        sb.append(getBlanks(1)).append("});").append(BR);
        sb.append(getBlanks(0)).append("});").append(BR);
		return sb;
	}

	@Override
	protected String getOutputFileName(Class<?> clazz) {
		return getFilePath().concat(firstLetterLower(clazz.getSimpleName())).concat(".js");
	}

}
