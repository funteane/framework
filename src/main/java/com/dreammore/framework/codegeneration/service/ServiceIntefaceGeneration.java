package com.dreammore.framework.codegeneration.service;

import java.lang.reflect.Field;

import com.dreammore.framework.codegeneration.model.Validate;


public class ServiceIntefaceGeneration extends AbstractGeneration{

	public ServiceIntefaceGeneration(String filePath) {
		super(filePath);
	}

	@Override
	public StringBuffer generate(Class<?> clazz) {
		StringBuffer sb = new StringBuffer();
		sb.append(getBlanks(0)).append("public interface I").append(clazz.getSimpleName()).append("Service{").append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("public PageBean<").append(clazz.getSimpleName()).append("> getAll").append(clazz.getSimpleName());
		sb.append(getBlanks(0)).append("s(PageBean<").append(clazz.getSimpleName()).append("> pageBean, ").append(clazz.getSimpleName()).append(" ");
		sb.append(getBlanks(0)).append(firstLetterLower(clazz.getSimpleName())).append(", List<Long> ids ) ;").append(BR);
		sb.append(BR);
		// public InStoragePlan getInStoragePlanByPlanCode(String planCode);
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Validate validate = field.getAnnotation(Validate.class);
			if (validate != null && validate.unique()) {
				sb.append(getBlanks(1)).append("public ").append(clazz.getSimpleName()).append(" get").append(clazz.getSimpleName()).append("By");
				sb.append(getBlanks(0)).append(firstLetterUpper(field.getName())).append("(String ").append(field.getName()).append(");").append(BR);
				sb.append(BR);
			}
		}
		sb.append(getBlanks(0)).append("}");
		
		
		return sb;
	}

	@Override
	protected String getOutputFileName(Class<?> clazz) {
		return getFilePath().concat("I").concat(clazz.getSimpleName()).concat("Service.java");
	}

}
