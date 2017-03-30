package com.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;

@Aspect
@Component
public class LoggingAspect {

    /*
            Question 1 :- Create a logging aspect.
                          Create a pointcut to log all methods Of services
     */
    @Before("pointCut()")
    public void useOfPointCutAnnotation() {
        System.out.println("This is use of PointCut Annotation. This can be used multiple times.");
    }

    /*
             Question 2 :- Add a logging statement before the method starts.
         */
    @Before("execution(* add*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("This method will run Before Execution of method :- " + joinPoint.getSignature());
    }

    /*
            Question 2:- Add a logging statement after the method ends
     */
    @After("execution(* add*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("This method will run After Execution of method :- " + joinPoint.getSignature());
    }

    /*
            Question 3 :- Log all the methods which throw IOException
     */
    @AfterThrowing(value = "execution(* after*(..))", throwing = "ioException")
    public void afterThrowingException(IOException ioException) {
        System.out.println("After Throwing Exception " + ioException);
    }

    @Around("execution(* delete*(..))")
    public void logAround(ProceedingJoinPoint joinPoint) {
        System.out.println("This method will run Before Execution of method :- " + joinPoint.getSignature());
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @After("@annotation(Deprecated)")
    public void logAnnotateMethod() {
        System.out.println("logging Annotated Method");
    }

    @AfterReturning(value = "execution(* getValue*(..))", returning = "returnedValue")
    public void afterReturning(String returnedValue) {
        System.out.println("After Returning Method " + returnedValue);
    }

    @Pointcut("execution(* sum*(..)))")
    public void pointCut() {
    }
}
