package com.knesek.springmockedtests.service.impl;

import com.knesek.springmockedtests.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Dummy implementation for example purposes.
 *
 * @author knesek
 * Created on: 17/08/14
 */
@Service
public class EmailServiceImpl implements EmailService {

	private final static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public void sendEmail(String toAddress, String subject, String body) {
		log.info("Sending Email to '{}' with subject '{}' and body: {}", toAddress, subject, body);
	}
}
