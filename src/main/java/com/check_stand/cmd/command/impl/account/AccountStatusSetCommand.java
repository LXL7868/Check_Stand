package com.check_stand.cmd.command.impl.account;

import com.check_stand.cmd.command.Subject;
import com.check_stand.cmd.command.annotation.AdminCommand;
import com.check_stand.cmd.command.annotation.CommandMeta;
import com.check_stand.cmd.command.impl.AbstractCommand;
import com.check_stand.common.AccountStatus;
import com.check_stand.entity.Account;

import java.sql.SQLException;


@CommandMeta(
        name = {"QTZH"},
        desc = "启停账号",
        group = "账号信息"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) throws SQLException {
        System.out.println("启停账号");
        System.out.println("请输入您要启停的账户：");
        String username = scanner.nextLine();
        System.out.println("请输入您要启停的账户Id");
        String id = scanner.nextLine();
        Account account = this.accountService.query(id,username);
        if (account == null){
            System.out.println("您输入的账户不存在，请核实后重新输入");
            execute(subject);
        }else {
            if (account.getAccountStatus() == AccountStatus.LOCK){
                System.out.println("此账户已经启停");
            }
            this.accountService.updateStatus(username,id);
        }
    }
}
