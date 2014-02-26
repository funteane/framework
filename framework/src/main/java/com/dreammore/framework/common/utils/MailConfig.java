package com.dreammore.framework.common.utils;

/**
 * 邮件配置
 * @author huhai
 * @since 2012-9-5
 *
 */
public class MailConfig {
	private String[] emailAddress;
	private String[] ccAddress;
	private String[] bccAddress;
	private String from;
	private String charset = "UTF-8";
	private String textMessage;
	private String html;
	private String subject;
	private String mailServerUserName;
	private String mailServerPassword;
	private String host;
	
    public String[] getEmailAddress() {
    	return emailAddress;
    }
	
    public void setEmailAddress(String[] emailAddress) {
    	this.emailAddress = emailAddress;
    }
	
    public String[] getCcAddress() {
    	return ccAddress;
    }
	
    public void setCcAddress(String[] ccAddress) {
    	this.ccAddress = ccAddress;
    }
	
    public String[] getBccAddress() {
    	return bccAddress;
    }
	
    public void setBccAddress(String[] bccAddress) {
    	this.bccAddress = bccAddress;
    }
	
    public String getFrom() {
    	return from;
    }
	
    public void setFrom(String from) {
    	this.from = from;
    }
	
    public String getCharset() {
    	return charset;
    }
	
    public void setCharset(String charset) {
    	this.charset = charset;
    }
	
    public String getTextMessage() {
    	return textMessage;
    }
	
    public void setTextMessage(String textMessage) {
    	this.textMessage = textMessage;
    }
	
    public String getHtml() {
    	return html;
    }
	
    public void setHtml(String html) {
    	this.html = html;
    }
	
    public String getSubject() {
    	return subject;
    }
	
    public void setSubject(String subject) {
    	this.subject = subject;
    }
	
    public String getMailServerUserName() {
    	return mailServerUserName;
    }
	
    public void setMailServerUserName(String mailServerUserName) {
    	this.mailServerUserName = mailServerUserName;
    }
	
    public String getMailServerPassword() {
    	return mailServerPassword;
    }
	
    public void setMailServerPassword(String mailServerPassword) {
    	this.mailServerPassword = mailServerPassword;
    }
	
    public String getHost() {
    	return host;
    }
	
    public void setHost(String host) {
    	this.host = host;
    }
	
	
}
