package com.gruntik.microservices.service;

import com.gruntik.microservices.dao.RequestDao;
import com.gruntik.microservices.model.Request;
import com.gruntik.microservices.model.StockInfo;
import com.gruntik.microservices.model.command.CreateRequest;
import com.gruntik.microservices.model.event.AccountChanged;
import com.gruntik.microservices.model.event.AccountChecked;
import com.gruntik.microservices.model.event.StocksBought;
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

    //calculate price and quantity
    //return price and quantity
    //check available limit
    //available limit
    public void createRequest(CreateRequest command) {
        requestDao.insert(Request.from(command));
        StockInfo stock = stockService.getStockInfo(command.getStockCode());
        commandSender.checkAccount(command.getId(), command.getPersonId(),
                stock.getPrice().multiply(BigDecimal.valueOf(command.getStockCount())));
    }

    //buy stock
    public void listenAccountChecked(AccountChecked event) {
        Request req = requestDao.getRequest(event.getRequestId());
        commandSender.buyStock(event.getRequestId(), req.getStockCode(), req.getStockCount(), event.getSum());
    }

    //write off money
    public void listenStocksBought(StocksBought event) {
        Request req = requestDao.getRequest(event.getRequestId());
        commandSender.changeAccount(event.getRequestId(), req.getPersonId(), event.getSum());
    }

    //delete request
    public void listenAccountChanged(AccountChanged event) {
        requestDao.delete(event.getRequestId());
    }
}
