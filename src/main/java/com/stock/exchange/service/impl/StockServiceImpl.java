package com.stock.exchange.service.impl;

import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;
import com.stock.exchange.entity.StockExchangeStockMap;
import com.stock.exchange.repository.StockRepository;
import com.stock.exchange.repository.StockExchangeRepository;
import com.stock.exchange.repository.StockExchangeStockMapRepository;
import com.stock.exchange.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private  final StockRepository stockRepository;
    private  final StockExchangeRepository stockExchangeRepository;
    private  final StockExchangeStockMapRepository stockExchangeStockMapRepository;
    @Override
    public Boolean create(Stock stock) {

        stock.setLastUpdate(OffsetDateTime.now());
        stockRepository.save(stock);
        return true;
    }

    @Override
    public Boolean updatePrice(Stock stock) {

      Optional<Stock> currentStockOptional= stockRepository.findById(stock.getId());
      if (currentStockOptional.isEmpty()){
          return  false;
      }

      Stock currentStock= currentStockOptional.get();
      currentStock.setCurrentPrice(stock.getCurrentPrice());
      currentStock.setLastUpdate(OffsetDateTime.now());
      stockRepository.save(currentStock);

      return true;
    }

    @Override
    public Boolean delete(Stock stock){

        Optional<Stock> currentStockOptional= stockRepository.findById(stock.getId());
        if (currentStockOptional.isEmpty()){
            return  false;
        }

        List<StockExchangeStockMap> stockExchangeStockMapList = stockExchangeStockMapRepository.findByStockId(stock.getId());
        stockRepository.delete(currentStockOptional.get());

        for (StockExchangeStockMap item:stockExchangeStockMapList) {
            Long stockExchangeId = item.getStockExchange().getId();
            Long stockCount = stockExchangeStockMapRepository.countByStockExchangeId(stockExchangeId);
            Optional<StockExchange> stockExchange =  stockExchangeRepository.findById(stockExchangeId);
            if(!stockExchange.isEmpty()){
                stockExchange.get().setLiveInMarket(true);
                if(stockCount < 5){
                    stockExchange.get().setLiveInMarket(false);
                }
                stockExchangeRepository.save(stockExchange.get());
            }
        }
        return true;
    }
}
