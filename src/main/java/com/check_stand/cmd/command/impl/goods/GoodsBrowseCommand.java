package com.check_stand.cmd.command.impl.goods;

import com.check_stand.cmd.command.Subject;
import com.check_stand.cmd.command.annotation.AdminCommand;
import com.check_stand.cmd.command.annotation.CommandMeta;
import com.check_stand.cmd.command.annotation.CustomerCommand;

import com.check_stand.cmd.command.impl.AbstractCommand;
import com.check_stand.entity.Goods;


import java.util.List;


@CommandMeta(
        name = {"LLSP"},
        desc = "浏览商品",
        group = "商品信息"
)

@AdminCommand
@CustomerCommand
public class GoodsBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("商品浏览");
        List<Goods> goodsList = this.goodsService.queryAllGoods();

        if(goodsList.isEmpty()) {
            System.out.println("Sorry，商品为空");
        }else {
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }
    }
}
