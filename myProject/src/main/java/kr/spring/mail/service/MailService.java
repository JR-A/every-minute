package kr.spring.mail.service;

import javax.servlet.http.HttpServletRequest;

public interface MailService {
	public String getKey(int size);
	public String getAuthCode();
	public String sendAuthMail(String email);
	public void sendIdPasswd(String email,String id,String passwd);
}
