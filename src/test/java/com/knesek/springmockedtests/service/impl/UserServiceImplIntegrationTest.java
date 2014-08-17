package com.knesek.springmockedtests.service.impl;

import com.knesek.springmockedtests.TestAppConfig;
import com.knesek.springmockedtests.com.knesek.springmockedtests.util.MockedBeans;
import com.knesek.springmockedtests.model.User;
import com.knesek.springmockedtests.repository.UserRepository;
import com.knesek.springmockedtests.service.EmailService;
import com.knesek.springmockedtests.service.PasswordEncoder;
import com.knesek.springmockedtests.service.StatisticsService;
import com.knesek.springmockedtests.service.UserService;
import com.lambdaworks.crypto.SCryptUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Demo of an integration test that uses both, mocked objects and actual spring objects.
 * Mocked objects are specified concisely using custom @MockedBeans annotation.
 *
 * @author knesek
 * Created on: 17/08/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class UserServiceImplIntegrationTest {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	/**
	 * Note that we can autowire mocked beans and then use them in Mockito's verify assertions.
	 */
	@Autowired
	StatisticsService statisticsService;

	/**
	 * Integration test that stores User into the database, then reads it from the database
	 * and verifies if password was correctly hashed.
	 */
	@Transactional
	@Test
    public void testRegisterNewUser() {
		final String userName = "someone";
		final String password = "somepass";
		userService.registerNewUser(new User("someone@example.com", userName, password));
		User user = userRepository.findByUserName(userName);
		Assert.assertNotNull(user);
		Assert.assertTrue(SCryptUtil.check(password, user.getPassword()));
		//Verifying interaction with a mocked bean
		verify(statisticsService, times(1)).recalculateStatisticReports();
	}

	/**
	 * Inner class configuration object. Spring will read it thanks to
	 * @ContextConfiguration(loader=AnnotationConfigContextLoader.class)
	 * annotation on the test class.
	 */
	@Configuration
	@Import(TestAppConfig.class) //TestAppConfig contains common integration testing configuration
	@MockedBeans({EmailService.class, StatisticsService.class}) //Beans to be mocked
	static class ContextConfiguration {

		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new SCryptPasswordEncoder();
		}
	}
}