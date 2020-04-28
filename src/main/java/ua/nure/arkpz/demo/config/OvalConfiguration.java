package ua.nure.arkpz.demo.config;

import net.sf.oval.integration.spring.SpringInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.nure.arkpz.demo.validator.OvalValidator;
import ua.nure.arkpz.demo.validator.OvalValidatorImpl;


import java.lang.reflect.Constructor;

@Configuration
public class OvalConfiguration {
    @Bean
    SpringInjector springInjector() {
        try {
            Class clazz = Class.forName(SpringInjector.class.getName());
            Constructor[] constructors = clazz.getDeclaredConstructors();
            constructors[0].setAccessible(true);
            return (SpringInjector) constructors[0].newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    OvalValidator ovalValidator() {
        return new OvalValidatorImpl();
    }
}
