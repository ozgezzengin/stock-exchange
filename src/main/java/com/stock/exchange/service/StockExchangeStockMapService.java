package com.stock.exchange.service;

import com.stock.exchange.dto.StockExchangeDetailDto;
import com.stock.exchange.entity.Stock;

import java.util.List;

public interface StockExchangeStockMapService {

    Boolean add(Stock stock,String stockExchangeName);

    Boolean delete(Stock stock, String stockExchangeName);

    StockExchangeDetailDto list(String stockExchangeName);
}
