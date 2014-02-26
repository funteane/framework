package com.dreammore.framework.common.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dreammore.framework.common.model.SearchParameter;
import com.dreammore.framework.common.utils.Constants;
import com.dreammore.framework.common.utils.Tools;
import com.dreammore.framework.user.model.User;

public abstract class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	protected User getUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (attr == null)
			throw new IllegalArgumentException("miss session, need to login again");

		HttpSession session = attr.getRequest().getSession(false);
		if (session == null) {
			throw new IllegalArgumentException("用户Session还没有建立");
		}
		return (User) session.getAttribute(Constants.USER_PRINCIPAL);
	}
	
	

	@SuppressWarnings({ "unchecked" })
	protected <T> T getAttr(String key, Class<T> clas) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (attr == null)
			throw new IllegalArgumentException("miss session, need to login again");

		HttpSession session = attr.getRequest().getSession(false);
		if (session == null) {
			throw new IllegalArgumentException("用户Session还没有建立");
		}
		if (session.getAttribute(key) != null)
			return (T) session.getAttribute(key);
		else return null;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("exception/common");
		logger.error(ex.getMessage(), ex);

		Map<String, Object> data = new HashMap<String, Object>();
		int pos = ex.getMessage().indexOf(":");
		//if (pos > 0)
		//	data.put(NCSConstant.ERROR_TIPS, ex.getMessage().substring(pos + 1));
		//else data.put(NCSConstant.ERROR_TIPS, ex.getMessage());
		//mv.addObject(NCSConstant.MV_RESULT_KEY, data);
		if (ex.getMessage().indexOf("用户Session还没有建立") > -1) {
			response.setStatus(510);
		}
		else {
			response.setStatus(500);
		}
		return mv;
	}

	/**
	 * 
	 * @param file
	 *            物理文件名(包含目录)
	 * @param response
	 * @param showFileName
	 *            下载文件名
	 */
	protected void writeRespForDownFile(String file, HttpServletResponse response, HttpServletRequest request, String showFileName) {
		response.setContentType("text/html;charset=UTF-8");
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		try {
			File downFile = new File(file);
			if (!downFile.exists()) {
				logger.info("request file not exists,file:" + file);
				return;
			}
			long fileLength = downFile.length();
			response.setContentType("application/force-download");
			String encodeFileName = encodeFileName(showFileName, request);
			response.setHeader("Content-disposition", "attachment; filename=\"" + encodeFileName + "\"");
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downFile));
			sos = response.getOutputStream();
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				sos.write(buff, 0, bytesRead);
			}
			sos.flush();
		}
		catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		finally {
			try {
				if (bis != null)
					bis.close();
				if (sos != null)
					sos.close();
			}
			catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 下载文件名编码
	 * 
	 * @param fileName
	 * @param request
	 * @return
	 */
	protected String encodeFileName(String fileName, HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT");
		try {
			if (null != agent && -1 != agent.indexOf("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
				fileName = fileName.replaceAll("\\+", "%20");
			}
			else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		}
		catch (UnsupportedEncodingException e) {
			try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			catch (UnsupportedEncodingException e1) {
				logger.error(e1.getMessage(),e1);
			}
			logger.error(e.getMessage(),e);
		}
		return fileName;
	}

	
	/**
	 * 构建查询地址参数
	 * @param searchParameters
	 * @return
	 */
	protected String buildSearchUrl(List<SearchParameter> searchParameters){
		StringBuffer paramUrl = new StringBuffer();
		for(SearchParameter searchParameter : searchParameters){
			if (!Tools.empty(searchParameter.getValue()) ) {
				paramUrl.append("&").append(searchParameter.getName()).append("=").append(searchParameter.getValue());
			}
		}
		return paramUrl.toString();
	}
	
	/**
	 * 创建有状态的ModelAndView
	 * @param redirectUrl 跳转地址
	 */
	protected ModelAndView createStateModelAndView(String redirectUrl){
	    Integer pageNo = getAttribute(Constants.CURRENT_PAGE_NO, Integer.class);
	    String backUrl = "redirect:/".concat(redirectUrl).concat("?pageNo=").concat(String.valueOf(pageNo == null ? 1 : pageNo));
		@SuppressWarnings("unchecked")
		List<SearchParameter> searchParameters = getAttribute(Constants.SEARCH_PARAMS_LIST, List.class);
//		backUrl = backUrl.concat(buildSearchUrl(searchParameters));
		ModelAndView modelAndView = new ModelAndView(backUrl);
		for(SearchParameter searchParameter : searchParameters){
			modelAndView.addObject(searchParameter.getName(), searchParameter.getValue());
		}
		return modelAndView;
   }
	
	@SuppressWarnings({ "unchecked" })
	protected <T> T getAttribute(String key, Class<T> clas) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (attr == null)
			throw new IllegalArgumentException("miss session, need to login again");

		HttpSession session = attr.getRequest().getSession(false);
		if (session == null) {
			throw new IllegalArgumentException("用户Session还没有建立");
		}
		if (session.getAttribute(key) != null)
			return (T) session.getAttribute(key);
		else return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	protected <T> T getFlashAttribute(String key, Class<T> clas) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (attr == null)
			throw new IllegalArgumentException("miss session, need to login again");

		HttpSession session = attr.getRequest().getSession(false);
		if (session == null) {
			throw new IllegalArgumentException("用户Session还没有建立");
		}
		if (session.getAttribute(key) != null){
			T result = (T) session.getAttribute(key);
			session.removeAttribute(key);
			return result;
		}
		else return null;
	}
	
	protected <T> void setAttribute(String key, T value){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (attr == null)
			throw new IllegalArgumentException("miss session, need to login again");

		HttpSession session = attr.getRequest().getSession(false);
		if (session == null) {
			throw new IllegalArgumentException("用户Session还没有建立");
		}
		session.setAttribute(key, value);
		
	}
	
	protected void clearSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if (attr == null)
			throw new IllegalArgumentException("miss session, need to login again");

		HttpSession session = attr.getRequest().getSession(false);
		if (session == null) {
			throw new IllegalArgumentException("用户Session还没有建立");
		}
		
		 Enumeration<String> names =  session.getAttributeNames();
		 while(names.hasMoreElements()){
			 String name = names.nextElement();
			 session.removeAttribute(name);
		 }
	}
}
