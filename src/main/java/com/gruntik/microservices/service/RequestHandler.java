package com.gruntik.microservices.service;

import com.gruntik.microservices.dao.RequestDao;
import com.gruntik.microservices.model.Request;
import com.gruntik.microservices.model.StockInfo;
import com.gruntik.microservices.model.command.CreateRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequestHandler {

    private final RequestDao requestDao;
    private final CommandSender commandSender;
    private final StockService stockService;

    public RequestHandler(RequestDao requestDao, CommandSender commandSender, StockService stockService) {
        this.requestDao = requestDao;
        this.commandSender = commandSender;
        this.stockService = stockService;
    }

    public void createRequest(CreateRequest command) {
        requestDao.insert(Request.from(command));
        StockInfo stock = stockService.getStockInfo(command.getStockCode());
        commandSender.checkAccount(command.getId(), command.getPersonId(),
                stock.getPrice().multiply(BigDecimal.valueOf(command.getStockCount())));
    }
}
