package com.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class SpringAOPMain {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-AOP.xml");
        UserService userService = context.getBean(UserService.class);
//        userService.addUser();
        /*
            Question 1 :- method calling for pointCut
         */
        userService.sumInt(2,5);
//        userService.deprecatedMethod();
//        userService.getValue();
//        userService.afterThrowingException();
    }
}
