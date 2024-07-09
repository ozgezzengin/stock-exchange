package com.stock.exchange.service.impl;

import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;
import com.stock.exchange.entity.StockExchangeStockMap;
import com.stock.exchange.repository.StockExchangeRepository;
import com.stock.exchange.repository.StockExchangeStockMapRepository;
import com.stock.exchange.repository.StockRepository;
import com.stock.exchange.service.StockExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockExchangeServiceImpl implements StockExchangeService {

   private final StockExchangeRepository stockExchangeRepository;
   private final StockRepository stockRepository;
   private final StockExchangeStockMapRepository stockExchangeStockMapRepository;

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

    /**
     * Deletes StockExchange
     * @param  stockExchangeName string as parameter
     * @return Boolean- true if delete operation succeed.
     */
    @Override
    public Boolean delete(String stockExchangeName){

        Optional<StockExchange> currentStockExchangeOptional= stockExchangeRepository.findByName(stockExchangeName);
        if (currentStockExchangeOptional.isEmpty()){
            return  false;
        }

        List<StockExchangeStockMap> stockExchangeStockMapList = stockExchangeStockMapRepository.findByStockExchangeId(currentStockExchangeOptional.get().getId());
        stockExchangeRepository.delete(currentStockExchangeOptional.get());

        for (StockExchangeStockMap item:stockExchangeStockMapList) {
            Long stockExchangeId = item.getStockExchange().getId();
            Long stockId = item.getStock().getId();
            Optional<Stock> stockOptional=  stockRepository.findById(stockId);
            if(!stockOptional.isEmpty()){
                var result = stockExchangeStockMapRepository.findByStockIdAndStockExchangeId(stockOptional.get().getId(), stockExchangeId);
                stockExchangeStockMapRepository.deleteAll(result);
            }
        }
        return true;
    }
}
