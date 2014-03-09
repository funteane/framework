package com.dreammore.framework.codegeneration.service;

import java.lang.reflect.Field;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;

public class ServiceImplGeneration extends AbstractGeneration {

	public ServiceImplGeneration(String filePath) {
		super(filePath);
	}

	@Override
	public StringBuffer generate(Class<?> clazz) {
			StringBuffer sb = new StringBuffer();
			sb.append(getBlanks(0)).append("@Service").append(BR);
			sb.append(getBlanks(0)).append("public class ").append(clazz.getSimpleName()).append("ServiceImpl implements I");
			sb.append(getBlanks(0)).append(clazz.getSimpleName()).append("Service{").append(BR);
			sb.append(BR);
			sb.append(getBlanks(1)).append("@Autowired").append(BR);
			sb.append(getBlanks(1)).append("private ICommonDAO commonDAO;").append(BR);
			sb.append(BR);
			sb.append(getBlanks(1)).append("@Override").append(BR);
			sb.append(getBlanks(1)).append("public PageBean<").append(clazz.getSimpleName()).append("> getAll").append(clazz.getSimpleName());
			sb.append(getBlanks(0)).append("s(PageBean<").append(clazz.getSimpleName()).append("> pageBean, ").append(clazz.getSimpleName()).append(" ");
			sb.append(getBlanks(0)).append(firstLetterLower(clazz.getSimpleName())).append(", List<Long> ids) {").append(BR);
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("List<String> paramNames = new ArrayList<String>();").append(BR);
			sb.append(getBlanks(2)).append("List<Object> paramValues = new ArrayList<Object>();").append(BR);
			sb.append(getBlanks(2)).append("StringBuffer hql = new StringBuffer(\"FROM ").append(clazz.getSimpleName()).append(" WHERE 1 = 1 \");").append(BR);
			
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields){
				Comment comment = field.getAnnotation(Comment.class);
				if (comment != null && comment.searchable()) {
					sb.append(BR);
					sb.append(getBlanks(2)).append("if(!Tools.empty(").append(firstLetterLower(clazz.getSimpleName()));
					sb.append(".get").append(firstLetterUpper(field.getName())).append("())){").append(BR);
					sb.append(getBlanks(3)).append("hql.append(\"AND ").append(field.getName()).append(" ");
					sb.append(getBlanks(0)).append(" = :").append(field.getName()).append(" \");").append(BR);
					sb.append(getBlanks(3)).append("paramNames.add(\"").append(field.getName()).append("\");").append(BR);
					sb.append(getBlanks(3)).append("paramValues.add(").append(firstLetterLower(clazz.getSimpleName()));
					sb.append(".get").append(firstLetterUpper(field.getName())).append("());").append(BR);
					sb.append(getBlanks(2)).append("}").append(BR);	
				}
			}
			
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("if(!Tools.empty(ids)){").append(BR);
			sb.append(getBlanks(3)).append("hql.append(\"AND id IN (:ids) \");").append(BR);
			sb.append(getBlanks(3)).append("paramNames.add(\"ids\");").append(BR);
			sb.append(getBlanks(3)).append("paramValues.add(ids);").append(BR);
			sb.append(getBlanks(2)).append("}").append(BR);	
			
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("hql.append(\"ORDER BY id DESC\");").append(BR);
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("String[] names = new String[paramNames.size()];").append(BR);
			sb.append(getBlanks(2)).append("Object[] values = new Object[paramValues.size()];").append(BR);
			sb.append(getBlanks(2)).append("Tools.list2Array(paramNames, names);").append(BR);
			sb.append(getBlanks(2)).append("Tools.list2Array(paramValues, values);").append(BR);
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("commonDAO.find(hql.toString(), pageBean, names, values);").append(BR);
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("return pageBean;	").append(BR);
			sb.append(getBlanks(1)).append("}").append(BR);
			sb.append(BR);
			
			for (Field field : fields) {
				Validate validate = field.getAnnotation(Validate.class);
				if (validate != null && validate.unique()) {
					sb.append(getBlanks(1)).append("public ").append(clazz.getSimpleName()).append(" get").append(clazz.getSimpleName());
					sb.append(getBlanks(0)).append("By").append(firstLetterUpper(field.getName())).append("(String ").append(field.getName()).append("){").append(BR);
					//String hql = "FROM InStoragePlan isp WHERE planCode = ? ";
					//List<InStoragePlan> inStoragePlans = commonDAO.find(hql, new Object[]{});
					//return Tools.empty(inStoragePlans) ? null : inStoragePlans.get(0);
					sb.append(getBlanks(2)).append("String hql = \"FROM ").append(clazz.getSimpleName()).append(" WHERE ").append(field.getName()).append(" = ? \"; ").append(BR);
					sb.append(getBlanks(2)).append("List<").append(clazz.getSimpleName()).append("> ").append(firstLetterLower(clazz.getSimpleName())).append("s = commonDAO.find(hql, new Object[]{");
					sb.append(getBlanks(0)).append(field.getName()).append("});").append(BR);
					sb.append(getBlanks(2)).append("return Tools.empty(").append(firstLetterLower(clazz.getSimpleName())).append("s) ? null : ").append(firstLetterLower(clazz.getSimpleName())).append("s.get(0);").append(BR) ;
					sb.append(getBlanks(1)).append("}");
					sb.append(BR);
				}
			}
			sb.append(BR);
			sb.append(getBlanks(0)).append("}");
			
			return sb;
	}

	@Override
	protected String getOutputFileName(Class<?> clazz) {
		return getFilePath().concat(clazz.getSimpleName()).concat("ServiceImpl.java");
	}

}
