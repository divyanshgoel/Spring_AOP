package com.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;


@Service
public class AccountTransactionService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /*
            Question 6 :- There are tro cases in this Part.

            Case 1 :- When  " propagation = Propagation.REQUIRES_NEW " . In this new Transaction is created without
                      using calling transaction. So if Exception occurs previous transaction will not be rolled Back.

            Case 2 :- When  " propagation = Propagation.REQUIRED " . In this it will use previous transaction of calling
                      method so if any Exception occurs then previous transaction will be rolled back .
     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(propagation = Propagation.REQUIRED)
    public void accountTransactionCreation(String sender, String receiver, int balance_transfered) {
        int a = 1 / 0;

        String sql = "insert into account_transaction(sender,receiver,balance_transfered) " + "values (?,?,?)";
        int status = jdbcTemplate.update(sql, new Object[]{sender, receiver, balance_transfered});
        if (status > 0) {
            System.out.println("Entry in Account Transaction table successfully created.");
        } else System.out.println("Sorry ! Data Entry is not completed. Please try again later");
    }
}
