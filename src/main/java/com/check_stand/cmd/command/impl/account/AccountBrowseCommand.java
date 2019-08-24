package com.check_stand.cmd.command.impl.account;

import com.check_stand.cmd.command.Subject;
import com.check_stand.cmd.command.annotation.AdminCommand;
import com.check_stand.cmd.command.annotation.CommandMeta;
import com.check_stand.cmd.command.impl.AbstractCommand;
import com.check_stand.entity.Account;

import java.sql.SQLException;
import java.util.List;


@CommandMeta(
        name = {"CKZH"},
        desc = "查看账号",
        group = "账号信息"
)
@AdminCommand
public class AccountBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) throws SQLException {
        System.out.println("账户浏览");
        System.out.println("请选择：1、查看全部账号信息；2、查看单个账户信息");
        String confirm = scanner.next();
        if ("1".equalsIgnoreCase(confirm)) {
            List<Account> accountList = this.accountService.queryAllAccount();
            if (accountList.isEmpty()) {
                System.out.println("暂且没有账号存在");
            } else {
                System.out.println("------------------账号信息列表---------------------");
                System.out.println("| 编号 | 姓名 | 账号 |  密码  |  类型  | 状态 |");
                for (Account account : accountList) {

                    String str = new StringBuilder().append("| ").append(account.getId()).append("  ")
                            .append("| ").append(account.getName()).append(" ")
                            .append("| ").append(account.getUsername()).append(" ")
                            .append("| ").append("******").append(" ")
                            .append("| ").append(account.getAccountType().getDesc()).append(" ")
                            .append("| ").append(account.getAccountStatus().getDesc()).append(" ")
                            .append("| ").toString();
                    System.out.println(str);
                }
                System.out.println("--------------------------------------------");
            }
        }
        if ("2".equalsIgnoreCase(confirm)) {
            System.out.println("查看账户");
            System.out.println("请输入您要查看的账户名：");
            String username = scanner.next();
            System.out.println("请输入您要查看的账户Id：");
            String id = scanner.next();
            Account account = this.accountService.query(id,username);
            if (account != null){
                System.out.println("-==============================================-");
                System.out.println("用户Id:"+account.getId());
                System.out.println("用户姓名:"+account.getName());
                System.out.println("用户名:"+account.getUsername());
                System.out.println("用户密码:"+account.getPassword());
                System.out.println("用户账户状态:"+account.getAccountStatus());
                System.out.println("用户类型:"+account.getAccountType());
                System.out.println("-==============================================-");
            }else {
                System.out.println("-=============对不起，此账户不存在！===============-");
            }
        }
    }
}
