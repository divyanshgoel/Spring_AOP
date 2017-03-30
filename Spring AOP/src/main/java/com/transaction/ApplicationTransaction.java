package com.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTransaction {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        UserAccountService userAccount = context.getBean(UserAccountService.class);

        /*
                    Question 2 :- Create CRUD operation in Account Service
                                  (Add/GEt/Delete/Update amount in User Account table)

        CRUD Operations in userAccount.
        There are 4 methods in UserAccountService class :-

        insertUser : -Inserting a new User with balance in the table.
        getAccountDetails :- fetching the details of the user from the table by passing its name.
        transferAmount :- transferring amount from one account to another.
        deleteUser :- deleting user from table.
         */
//        userAccount.insertUser("Ajay", 1000);
//        userAccount.getAccountDetails("Divyansh");
        userAccount.transferAmount("Ajay", "Divyansh", 2000);
//        userAccount.deleteUser("Divyansh Goel");

    }


}
