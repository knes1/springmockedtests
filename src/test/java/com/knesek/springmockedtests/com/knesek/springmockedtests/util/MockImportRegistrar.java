package com.knesek.springmockedtests.com.knesek.springmockedtests.util;

import org.mockito.Mockito;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * MockImportRegistrar works on configuration objects annotated with MockedBeans annotation.
 * It picks up classes that need to be mocked from the MockedBeans annotation and mocks them
 * using Mockito.
 *
 * @author knesek
 * Created on: 4/4/14
 */
public class MockImportRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		if (importingClassMetadata.isAnnotated(MockedBeans.class.getName())) {
			Object mockedBeanTypesValue = importingClassMetadata.getAnnotationAttributes(MockedBeans.class.getName()).get("value");
			if (mockedBeanTypesValue instanceof Class<?>[]) {
				Class<?>[] mockedBeanTypes = (Class<?>[]) mockedBeanTypesValue;
				if (mockedBeanTypes != null && mockedBeanTypes.length > 0) {
					mockSpecifiedBeanTypes(registry, mockedBeanTypes);
				}
			}
		}
	}

	private void mockSpecifiedBeanTypes(BeanDefinitionRegistry registry, Class<?>[] mockedBeanTypes) {
		for (Class<?> mockedType : mockedBeanTypes) {
			registry.registerBeanDefinition("mock" + mockedType.getSimpleName(),
			BeanDefinitionBuilder
					.rootBeanDefinition(Mockito.class)
					.setFactoryMethod("mock")
					.addConstructorArgValue(mockedType.getName())
					.getBeanDefinition()
			);
		}
	}
}
