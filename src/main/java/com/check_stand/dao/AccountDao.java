package com.check_stand.dao;

import com.check_stand.common.AccountStatus;
import com.check_stand.common.AccountType;
import com.check_stand.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AccountDao extends BaseDao {
    private Account account;

    //登录

    public Account login(String username, String password) {
        //连接数据库
        Connection connection = null;
        //先将Sql语句进行预编译存储到PreparedStatement对象  可以使用此对象多次高效地执行改语句
        // 适合用于多次执行的SQL语句，可以防止SQL注入  预编译好之后，不用再次编译，提高了效率
        // 最终会生成一个ResultSet对象  支持批处理  适合批量操作
        // 该对象将生成具有给定类型和并发性的 ResultSet 对象；
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Account account = null;
        try {
            connection = this.getConnection(true);
            String sql = "select id, username, password, name,account_type, account_status from account where username=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, DigestUtils.md5Hex(password));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = this.extractAccount(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return account;
    }


     //注册

    public boolean register(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "insert into account (username, password, name, account_type, account_status) values (?,?,?,?,?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getUsername());
            statement.setString(2, DigestUtils.md5Hex(account.getPassword()));
            statement.setString(3, account.getName());
            statement.setInt(4, account.getAccountType().getFlag());
            statement.setInt(5, account.getAccountStatus().getFlag());
            effect = (statement.executeUpdate() == 1);
            //获取自增主键到集合resultSet
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                account.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return effect;
    }


     // 检查重名

    public boolean checkUserName(String userName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection(true);
            String sql = "select * from account where username=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return false;
    }

    public List<Account> queryAccount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Account> accountList = new ArrayList<>();
        try {
            connection = this.getConnection(true);
            String sql = "select id, username, password, name, account_type, account_status from account";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountList.add(this.extractAccount(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return accountList;
    }

    private Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setName(resultSet.getString("name"));
        account.setAccountType(AccountType.valueOf(resultSet.getInt("account_type")));
        account.setAccountStatus(AccountStatus.valueOf(resultSet.getInt("account_status")));
        return account;
    }

    public Account query(String id, String username) throws SQLException {
        Account account = new Account();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection = this.getConnection(true);
        String sql = "select * from account where id =? and username =?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(2,username);
        preparedStatement.setString(1,id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            account = extractAccount(resultSet);
        }
        return account;
    }

    //重置密码

    public Account resetPassword(String username, String id, String password) throws SQLException {
        //Account account = new Account();
        int flg = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = this.getConnection(true);
        String sql = "update account set password=? where id=? and username=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,DigestUtils.md5Hex(password));
        preparedStatement.setString(2,id);
        preparedStatement.setString(3,username);
        flg = preparedStatement.executeUpdate();
        if (flg != 0){
            account = login(username,password);
            System.out.println("重置密码成功！");
        }else {
            System.out.println("重置密码失败！");
        }
        return account;
    }

    //账户启停

    public Account updateStatus(String username, String id) throws SQLException {
        int flg = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        connection = this.getConnection(true);
        String sql = "update account set account_status = 2 where id =? and username =?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,username);
        flg = preparedStatement.executeUpdate();
        if (flg != 0){ ;
            System.out.println("账户启停成功！");
        }else {
            System.out.println("账户启停失败！");
        }
        return account;
    }
}
