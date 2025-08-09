package com.wangfl.droolstest;

import com.wangfl.droolstest.fact.Order;
import com.wangfl.droolstest.fact.User;
import lombok.SneakyThrows;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieContainerSessionsPool;
import org.kie.api.runtime.KieSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangfl
 * @date 2025/5/27
 */
public class TestMain {
    public static void main(String[] args) {
        // 使用规则引擎
        long step1 = System.currentTimeMillis();
        KieServices ks = KieServices.get();//单例

        long step2 = System.currentTimeMillis();
        System.out.println("step2:" + (step2 - step1));
        KieContainer kContainer = ks.getKieClasspathContainer();//单例

        // 开启会话
        long step3 = System.currentTimeMillis();
        System.out.println("step3:" + (step3 - step2));
        KieSession kieSession = kContainer.newKieSession("parameterRules");

        long step4 = System.currentTimeMillis();
        System.out.println("step4:" + (step4 - step3));
        List<Order> orderList = getInitData();
        for (Order order: orderList) {
            // 设置处理对象
            long step5 = System.currentTimeMillis();
            System.out.println("step5:" + (step5 - step4));
            kieSession.insert(order);
            // 应用全部规则
            long step6 = System.currentTimeMillis();
            System.out.println("step6:" + (step6 - step5));
            int fireCount = kieSession.fireAllRules();
            // 执行完规则后, 执行相关的逻辑
            long step7 = System.currentTimeMillis();
            System.out.println("step7:" + (step7 - step6));
            addScore(order);
        }
        // 终止会话
        kieSession.dispose();
    }

    private static void addScore(Order o){
        System.out.println("用户" + o.getUser().getName() + "享受额外增加积分: " + o.getScore());
    }

    @SneakyThrows
    private static List<Order> getInitData() {
        List<Order> orderList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        {
            Order order = new Order();
            order.setAmount(80);
            order.setBookingDate(df.parse("2015-07-01"));
            User user = new User();
            user.setLevel(1);
            user.setName("Name1");
            order.setUser(user);
            order.setScore(111);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmount(200);
            order.setBookingDate(df.parse("2015-07-02"));
            User user = new User();
            user.setLevel(2);
            user.setName("Name2");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmount(800);
            order.setBookingDate(df.parse("2015-07-03"));
            User user = new User();
            user.setLevel(3);
            user.setName("Name3");
            order.setUser(user);
            orderList.add(order);
        }
        {
            Order order = new Order();
            order.setAmount(1500);
            order.setBookingDate(df.parse("2015-07-04"));
            User user = new User();
            user.setLevel(4);
            user.setName("Name4");
            order.setUser(user);
            orderList.add(order);
        }
        return orderList;
    }
}
