package com.stock.exchange.dto;

import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class StockExchangeDetailDto {

    StockExchange stockExchange;
    List<Stock> stockList;
}
