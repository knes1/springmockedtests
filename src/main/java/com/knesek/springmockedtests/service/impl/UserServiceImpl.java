package com.knesek.springmockedtests.service.impl;

import com.knesek.springmockedtests.model.User;
import com.knesek.springmockedtests.repository.UserRepository;
import com.knesek.springmockedtests.service.EmailService;
import com.knesek.springmockedtests.service.PasswordEncoder;
import com.knesek.springmockedtests.service.StatisticsService;
import com.knesek.springmockedtests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Demo UserService. Created as an example that we'll test using the UserServiceImplIntegrationTest to
 * show integration testing that mixes actual Spring beans and mocked objects.
 *
 * @author knesek
 * Created on: 17/08/14
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	StatisticsService statisticsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User registerNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		emailService.sendEmail(user.getEmail(), "Registration Successful!",
				MessageFormat.format("You have successfully registered and your username is {0}!", user.getUserName()));
		statisticsService.recalculateStatisticReports();
		return user;
	}
}
