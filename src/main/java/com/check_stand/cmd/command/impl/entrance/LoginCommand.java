package com.check_stand.cmd.command.impl.entrance;

import com.check_stand.cmd.command.Command;
import com.check_stand.cmd.command.Subject;
import com.check_stand.cmd.command.annotation.CommandMeta;
import com.check_stand.cmd.command.annotation.EntranceCommand;
import com.check_stand.cmd.command.impl.AbstractCommand;
import com.check_stand.common.AccountStatus;
import com.check_stand.entity.Account;


@CommandMeta(
        name = {"DL","LOGIN"},
        desc = "登录",
        group = "入口命令"
)
@EntranceCommand
public class LoginCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("登录");
        Account account = subject.getAccount();
        if(account != null) {
            System.out.println("已经登录过了");
            return;
        }
        System.out.println("请输入用户名：");
        String username = Command.scanner.nextLine();
        System.out.println("请输入密码：");
        String password = Command.scanner.nextLine();

        account = this.accountService.login(username,password);

        if(account != null && account.getAccountStatus() == AccountStatus.UNLOCK) {
            System.out.println(account.getAccountType().getDesc()+"登录成功");
            subject.setAccount(account);
        }else {
            System.out.println("登录失败，密码或者用户名错误");
        }
    }
}
