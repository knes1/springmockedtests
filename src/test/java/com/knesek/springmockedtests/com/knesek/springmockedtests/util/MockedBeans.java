package com.knesek.springmockedtests.com.knesek.springmockedtests.util;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate @Configuration object to specify which classes need to be mocked.
 * This annotation will trigger MockImportRegistrar which will register mocked bean
 * definitions to the context.
 *
 * @author knesek
 * Created on: 4/4/14
 */
@Import(MockImportRegistrar.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MockedBeans {

	/**
	 * Types (classes / interfaces) that need to be mocked.
	 */
	Class<?>[] value() default {};

}
