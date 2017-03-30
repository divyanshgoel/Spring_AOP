package com.demo;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    User user;


    public String getValue() {
        return "Spring AOP Session";
    }

    public void sumInt(int a, int b) {
        int c = a + b;
        System.out.println("Sum of integer numbers are :- " + c);
    }

    public void sumFloat(float a, float b) {
        float c = a + b;
        System.out.println("Sum of float numbers are :- " + c);
    }

    public void afterThrowingException() throws IOException {
        throw new IOException();
    }

    public void addUser() {
        System.out.println("User is Added");
    }

    @Deprecated
    public void deprecatedMethod() {
        System.out.println("This Method is Deprecated");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void argsDemo(long l, int i) {

        long c = l + i;
        System.out.println(c);

    }
}
