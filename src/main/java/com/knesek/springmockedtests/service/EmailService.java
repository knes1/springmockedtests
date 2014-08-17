package com.knesek.springmockedtests.service;

/**
 * @author knesek
 * Created on: 17/08/14
 */
public interface EmailService {

	void sendEmail(String toAddress, String subject, String body);

}
