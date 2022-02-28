package com.gruntik.microservices.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class CommandSender {

    public void checkAccount(UUID requestId, UUID personId, BigDecimal sum) {
        //хватает ли денег
        //получить человека
        //получить сумму через requestId
        //получить доступную сумму у человека
    }

    public void buyStock(UUID requestId, String stockCode, int stockCount, BigDecimal price) {
        //покупаем акции
        //уменьшаем количество акций
        //добавляем акцию человеку
    }

    public void changeAccount(UUID requestId, UUID personId, BigDecimal sum) {
        //списываем деньги
    }
}
