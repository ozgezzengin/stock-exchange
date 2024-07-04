package com.stock.exchange.service.impl;

import com.stock.exchange.entity.StockExchange;
import com.stock.exchange.repository.StockExchangeRepository;
import com.stock.exchange.service.StockExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockExchangeServiceImpl implements StockExchangeService {

   private final StockExchangeRepository stockExchangeRepository;

     /**
     * Creates StockExchange
     * @param  stockExchange class as parameter
     * @return Boolean- true if create operation succeed.
     */
    @Override
    public Boolean create(StockExchange stockExchange) {
        stockExchangeRepository.save(stockExchange);
        return true;
    }
}
