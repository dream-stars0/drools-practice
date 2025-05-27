package com.wangfl.droolstest.fact;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author wangfl
 * @date 2025/5/27
 */
@Getter
@Setter
@ToString
public class Order {

    /**
     * 订单原价金额
     */
    private int price;

    /**
     *下单人
     */
    private User user;

    /**
     *积分
     */
    private int score;

    /**
     * 下单日期
     */
    private Date bookingDate;

    /**
     * 数量
     */
    private Integer amount;

    public int getScore() {
        return score;
    }
}
