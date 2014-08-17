package com.knesek.springmockedtests.service.impl;

import com.knesek.springmockedtests.service.PasswordEncoder;
import com.lambdaworks.crypto.SCryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Used for example purposes. Parameters for SCrypt were chosen as recommended in
 * http://stackoverflow.com/questions/11126315/what-are-optimal-scrypt-work-factors
 *
 * @author knesek
 * Created on: 17/08/14
 */
@Service
public class SCryptPasswordEncoder implements PasswordEncoder {

	private final static Logger log = LoggerFactory.getLogger(SCryptPasswordEncoder.class);

	// http://stackoverflow.com/questions/11126315/what-are-optimal-scrypt-work-factors
	private final int complexity = (int) Math.pow(2, 16);

	@Override
	public String encode(String password) {
		long time = System.currentTimeMillis();
		String result = SCryptUtil.scrypt(password, complexity, 8, 1);
		log.info("It took {} ms to encode the password.", System.currentTimeMillis() - time);
		return result;
	}
}
