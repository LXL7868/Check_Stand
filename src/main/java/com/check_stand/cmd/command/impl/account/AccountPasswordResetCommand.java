package com.check_stand.cmd.command.impl.account;

import com.check_stand.cmd.command.Subject;
import com.check_stand.cmd.command.annotation.AdminCommand;
import com.check_stand.cmd.command.annotation.CommandMeta;
import com.check_stand.cmd.command.impl.AbstractCommand;
import com.check_stand.entity.Account;

import java.sql.SQLException;


@CommandMeta(
        name = {"CZMM"},
        desc = "重置密码",
        group = "账号信息"
)
@AdminCommand
public class AccountPasswordResetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) throws SQLException {
        System.out.println("密码重置");
        System.out.println("请输入您要重置密码的账户名：");
        String username = scanner.nextLine();
        System.out.println("请输入与账户名相匹配的账户Id:");
        String id = scanner.nextLine();
        Account account = this.accountService.query(id,username);
        if(account ==null){
            System.out.println("您输入的账户不存在，请重新输入！");
            execute(subject);
        }
        System.out.println("请输入您要重置的密码：");
        String password = scanner.nextLine();
        if (password.trim() == null){
            System.out.println("密码不能为空，请输入密码：");
            password = scanner.nextLine().trim();
        }
        System.out.println("请再次输入确认密码：");
        String password1 = scanner.nextLine();
        if (password.equals(password1)){
            this.accountService.resetPassword(username,id,password);
        }else {
            System.out.println("两次输入的密码不一致，请重新输入！");
            execute(subject);
        }
    }
}
