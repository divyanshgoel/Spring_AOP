package com.transaction;

import com.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserAccountService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    AccountTransactionService accountTransactionService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /*
            Question 4 :- Transfer amount from "user1" to "user2".
                          Using Declarative(Annotation Based) Transaction
     */
    @Transactional
//    @Transactional(readOnly = true)
    /*  Here Read-only specifies that it can only be Read cannot be written means no updates are possible.

            Question 5 :- Provide Appropriate Readonly attribute
                          as per Method Behaviour.
     */
    public void transferAmount(String receiver, String sender, int amount) {

        //                  Question 3 :- Transfer amount from "user1" to "user2".
        //                                Using Programmatic Transaction

       /* TransactionDefinition transactionDefinition = new DefaultTransactionAttribute();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);*/

        String checkBalance = "select balance from userAccount where userName = ?";
        int rs = jdbcTemplate.queryForInt(checkBalance, new Object[]{sender});
        if (rs > amount) {
            String sqlReceiver = "Update userAccount set balance = balance + ? where userName = ?";
            jdbcTemplate.update(sqlReceiver, new Object[]{amount, receiver});

            String sqlSender = "Update userAccount set balance = balance - ? where userName = ?";
            jdbcTemplate.update(sqlSender, new Object[]{amount, sender});
            System.out.println(sender + " has successfully transfered " + amount + " rupees to " + receiver);
            try {
                accountTransactionService.accountTransactionCreation(sender, receiver, amount);
            } catch (Exception e) {

            }

//            transactionManager.commit(transactionStatus);
        } else {
            System.out.println("InSufficient Balance");
        }
//            System.out.println("Exception Occured " + e + "Transaction is now Rolled Back");
//            transactionManager.rollback(transactionStatus);
    }

    @Transactional
    public void getAccountDetails(String userName) {

        String sql = "select * from userAccount where userName = ?";
        UserAccount userAccount = jdbcTemplate.queryForObject(sql, new Object[]{userName}, new RowMapper<UserAccount>() {
            @Override
            public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

                UserAccount userAccount1 = new UserAccount();
                userAccount1.setName(rs.getString("userName"));
                userAccount1.setBalance(rs.getInt("balance"));
                return userAccount1;
            }
        });
        System.out.println(userAccount.getName());
        System.out.println(userAccount.getBalance());
    }

    @Transactional
    public void deleteUser(String userName) {
        int status = 3;

        String sql = "delete from userAccount where userName = ?";
        status = jdbcTemplate.update(sql, new Object[]{userName});
        System.out.println(status);
        if (status > 0) {
            System.out.println("User Successfully Deleted");
        } else System.out.println("User name does not exist");
    }

    @Transactional
    public void insertUser(String userName, int balance) {

        String sql = "insert into userAccount(userName, balance) values (?, ?)";
        int status = jdbcTemplate.update(sql, userName, balance);
        if (status > 0) {
            System.out.println("User Successfully Created");
        } else System.out.println("Unable to add User in the Database . Please try again after some time");
    }

    /*
            Question 6 :- Create account_transaction table with sender,receiver, balance_transferred fields
                          Save values in this table on transfer amount.
                          Even If any error occurs while saving values in transaction table,
                          account table should get updated.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void accountTransactionCreation(String sender, String receiver, int balance_transfered) {
        int a = 1 / 0;
      /*  String sql = "insert into account_transaction(sender,receiver,balance_transfered) " +
                "values (?,?,?)";
        int status = jdbcTemplate.update(sql, new Object[]{sender, receiver, balance_transfered});
        if (status > 0) {
            System.out.println("Entry in Account Transaction table successfully created.");
        } else System.out.println("Sorry ! Data Entry is not completed. Please try again later");*/
    }
}