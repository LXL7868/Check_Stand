package com.check_stand.cmd.command.impl;

import com.check_stand.cmd.command.Command;
import com.check_stand.service.AccountService;
import com.check_stand.service.GoodsService;
import com.check_stand.service.OrderService;

public abstract class AbstractCommand implements Command {

    public AccountService accountService;
    public GoodsService goodsService;
    public OrderService orderService;
    public AbstractCommand() {
        this.accountService = new AccountService();
        this.goodsService = new GoodsService();
        this.orderService = new OrderService();
    }

    protected final void warningPrintln(String message) {
        System.out.println("【警告】："+ message);
    }
    protected final void errorPrintln(String message) {
        System.out.println("【错误】："+ message);
    }
    protected final void hitPrintln(String message) {
        System.out.println(">> "+ message);
    }
}
