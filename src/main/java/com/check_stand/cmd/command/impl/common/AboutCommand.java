package com.check_stand.cmd.command.impl.common;

import com.check_stand.cmd.command.Subject;
import com.check_stand.cmd.command.annotation.AdminCommand;
import com.check_stand.cmd.command.annotation.CommandMeta;
import com.check_stand.cmd.command.annotation.CustomerCommand;
import com.check_stand.cmd.command.annotation.EntranceCommand;
import com.check_stand.cmd.command.impl.AbstractCommand;


@CommandMeta(
        name = {"GY","ABOUT"},
        desc = "关于系统",
        group = "公共命令"
)
@EntranceCommand
@AdminCommand
@CustomerCommand
public class AboutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("------------------关于系统------------------");
        System.out.println("-----------------作者：lxl------------------");
        System.out.println("-------------------------------------------");
    }
}
