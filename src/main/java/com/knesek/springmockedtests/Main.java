package com.knesek.springmockedtests;

import com.knesek.springmockedtests.model.User;
import com.knesek.springmockedtests.repository.UserRepository;
import com.knesek.springmockedtests.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Main class of the demo application that was created to demonstrate how to write concise
 * integration tests that include a mix of Spring beans and mocked Mockito objects.
 *
 * The real action actually happens in UserServiceImplIntegrationTest.class.
 *
 * @author knesek
 * Created on: 17/08/14
 */
@Component
public class Main implements ApplicationListener<ContextRefreshedEvent>  {

	private final static Logger log = LoggerFactory.getLogger(Main.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	public void start() {
		userService.registerNewUser(new User("john.doe@example.com", "john.doe", "secret1"));
		userService.registerNewUser(new User("jane.doe@example.com", "jane.doe", "hidden2"));
		List<User> users = userRepository.findAll();
		for (User u : users) {
			log.info(u.toString());
		}
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		start();
	}
}
