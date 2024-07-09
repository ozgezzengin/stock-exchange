package com.stock.exchange.service;

import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;


public interface StockExchangeService {

    Boolean create(StockExchange stockExchange);

    Boolean delete(String stockExchangeName);

}
