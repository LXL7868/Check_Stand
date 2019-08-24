package com.check_stand.service;

import com.check_stand.dao.AccountDao;
import com.check_stand.entity.Account;

import java.sql.SQLException;
import java.util.List;


public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public boolean checkDuplicateUserName(String username) {
        return this.accountDao.checkUserName(username);
    }

    public boolean register(Account account) {
        return this.accountDao.register(account);
    }

    public Account login(String username, String password ) {
        return this.accountDao.login(username,password);
    }

    public List<Account> queryAllAccount(){
        return this.accountDao.queryAccount();
    }

    public Account query(String id, String username) throws SQLException {
        return this.accountDao.query(id,username);
    }

    public Account resetPassword(String username, String id, String password) throws SQLException {
        return this.accountDao.resetPassword(username,id,password);
    }

    public Account updateStatus(String username, String id) throws SQLException {
        return this.accountDao.updateStatus(username,id);
    }
}