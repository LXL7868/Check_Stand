package com.check_stand.cmd.command.impl.common;

import com.check_stand.cmd.command.Subject;
import com.check_stand.cmd.command.annotation.AdminCommand;
import com.check_stand.cmd.command.annotation.CommandMeta;
import com.check_stand.cmd.command.annotation.CustomerCommand;
import com.check_stand.cmd.command.annotation.EntranceCommand;
import com.check_stand.cmd.command.impl.AbstractCommand;


@CommandMeta(
        name = {"TC"},
        desc = "退出系统",
        group = "公共命令"
)
@EntranceCommand
@AdminCommand
@CustomerCommand
public class QuitCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("退出系统,欢迎下次使用");
        this.scanner.close();
        System.exit(0);//正常命令退出
    }
}
