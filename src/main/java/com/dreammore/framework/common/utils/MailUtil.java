package com.dreammore.framework.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.dreammore.framework.common.ApplicationProperties;

/**
 * 邮件工具类
 * @author huhai
 * @since 2012-9-6
 *
 */
public class MailUtil {
	private static Logger logger = Logger.getLogger(MailUtil.class);

	/**
	 * 发送vm模板邮件（邮件会逐发送给收件人、抄送、密送列表的收件人）
	 * @param config 邮件配置
	 * @param templatePath 模板路径（相对于classpath）
	 * @param encoding 邮件编码
	 * @param varMap 传给模板的变量Map， 可选
	 * @param velocityEngine
	 * @return Map<String, Set<String>> {success:发送成功的收件人列表, failure:发送失败的收件人列表}
	 * @author huhai
	 * @since 2012-9-6
	 */
	public static Map<String, Set<String>> sendTemplateMail(MailConfig config, String templatePath, String encoding, Map<String, Object> varMap, VelocityEngine velocityEngine){
		Map<String, Set<String>> result = null;
		if(null != config){
			String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, (encoding == null ? "UTF-8" : encoding), varMap);
			
			String hostName = ApplicationProperties.getProproperty("mail.smtp.server");
			String userName = ApplicationProperties.getProproperty("mail.user.name");
			String password = ApplicationProperties.getProproperty("mail.user.pass");
			
			if(null == config.getHost()){
				config.setHost(hostName);
			}
			
			if(null == config.getMailServerUserName()){
				config.setMailServerUserName(userName);
			}
			
			if(null == config.getMailServerPassword()){
				config.setMailServerPassword(password);
			}
			
			if(null == config.getFrom()){
				config.setFrom(userName);
			}
			config.setHtml(message);
			result = sendHtmlMail(config);				
		}
		
		return result;
	}
	
	/**
	 * 发送vm模板邮件（邮件会逐发送给收件人、抄送、密送列表的收件人）
	 * @param subject 主题
	 * @param textMessage 文本信息
	 * @param emailAddress 收件人地址
	 * @param templatePath 模板路径（相对于classpath）
	 * @param encoding 邮件编码
	 * @param varMap 传给模板的变量Map，可选
	 * @param velocityEngine
	 * @return Map<String, Set<String>> {success:发送成功的收件人列表, failure:发送失败的收件人列表}
	 * @author huhai
	 * @since 2012-9-6
	 */
	public static Map<String, Set<String>> sendTemplateMail(String subject, String textMessage, String[] emailAddress, String templatePath, String encoding, Map<String, Object> varMap, VelocityEngine velocityEngine){
		MailConfig config = new MailConfig();
		config.setSubject(subject);
		config.setTextMessage(textMessage);
		config.setEmailAddress(emailAddress);
		return sendTemplateMail(config, templatePath, encoding, varMap, velocityEngine);
	}
	
	/**
	 * 发送vm模板邮件（邮件会逐发送给收件人、抄送、密送列表的收件人）
	 * @param subject 主题
	 * @param textMessage 文本消息
	 * @param emailAddress 邮件地址
	 * @param templatePath 邮件模板路径（相对于classpath）
	 * @param varMap 传给模板的变量Map，可选
	 * @return Map<String, Set<String>> {success:发送成功的收件人列表, failure:发送失败的收件人列表}
	 * @author huhai
	 * @since 2012-9-6
	 */
	public static Map<String, Set<String>> sendTemplateMail(String subject, String textMessage, String[] emailAddress, String templatePath, Map<String, Object> varMap){
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		return sendTemplateMail(subject, textMessage, emailAddress, templatePath, null, varMap, velocityEngine);
	}
	
	/**
	 * 发送HTML邮件 （邮件会逐发送给收件人、抄送、密送列表的收件人）
	 * @param config 邮件配置
	 * @return Map<String, Set<String>> {success:发送成功的收件人列表, failure:发送失败的收件人列表}
	 * @author huhai
	 * @since 2012-9-6
	 */
	public static Map<String, Set<String>> sendHtmlMail(MailConfig config){
		Map<String, Set<String>> result = null;
		if(null != config){
			result = new HashMap<String, Set<String>>();
			
			Set<String> success = new HashSet<String>();
			Set<String> failure = new HashSet<String>();
			
			List<String> addressList = new ArrayList<String>();
			
			if(null != config.getEmailAddress() && config.getEmailAddress().length > 0){
				for(String address : config.getEmailAddress()){
					if(StringUtils.isNotBlank(address)){
						addressList.add(address);
					}
				}	
			}
			
			if(null != config.getCcAddress() && config.getCcAddress().length > 0){
				for(String cc : config.getCcAddress()){
					if(StringUtils.isNotBlank(cc)){
						addressList.add(cc.trim());
					}
				}
			}
			
			if(null != config.getBccAddress() && config.getBccAddress().length > 0){
				for(String bcc : config.getBccAddress()){
					if(StringUtils.isNotBlank(bcc)){
						addressList.add(bcc.trim());
					}
				}
			}
			
			if(addressList.size() > 0){
				try{
					for(String address : addressList){
						HtmlEmail email = new HtmlEmail();
						
						email.setHostName(config.getHost());
						email.setAuthentication(config.getMailServerUserName(), config.getMailServerPassword());
						email.setFrom(config.getFrom());
						email.setSubject(config.getSubject());
						email.setCharset(config.getCharset());
						email.setHtmlMsg(config.getHtml());
						email.setTextMsg((StringUtils.isBlank(config.getTextMessage()) ? "您的邮件客户端不支持HTML内容" : config.getTextMessage()));
						
						email.addTo(address);
						
						try {
							email.send();
							success.add(address);
							logger.info("send email to " + address + " success.");
                        }
                        catch (Exception e) {
                        	failure.add(address);
                        	logger.error("send mail to " + address +" failure.", e);
                        }
					}
				}catch (Exception e) {
					logger.error("send mail failure ", e);
				}
			}
			
			result.put("success", success);
			result.put("failure", failure);
        }
		
		return result;
	}
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-base.xml");
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		
		Calendar today = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		
		String subject = formatter.format(today.getTime()) + "博雅立方公司" + "推广账户余额提醒";
		
		Map contex = new HashMap<String, Object>();
		contex.put("year", today.get(Calendar.YEAR));
		contex.put("month", today.get(Calendar.MONTH) + 1);
		contex.put("date", today.get(Calendar.DATE));
		sendTemplateMail(subject, "",new String[]{"hai.hu@cubead.com"}, "mailtemplate/tools/balanceRemindingMail.vm", "UTF-8", contex, velocityEngine);
    }
}
