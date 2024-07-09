package com.stock.exchange.service.impl;

import com.stock.exchange.dto.StockExchangeDetailDto;
import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;
import com.stock.exchange.entity.StockExchangeStockMap;
import com.stock.exchange.repository.StockRepository;
import com.stock.exchange.repository.StockExchangeRepository;
import com.stock.exchange.repository.StockExchangeStockMapRepository;
import com.stock.exchange.service.StockExchangeStockMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class StockExchangeStockMapServiceImpl implements  StockExchangeStockMapService {

    private final StockRepository stockRepository;
    private final StockExchangeRepository stockExchangeRepository;
    private final StockExchangeStockMapRepository stockExchangeStockMapRepository;

    /**
     * Adds the stock to the stock exchange with stock name
     * @param  stock class to be added to StockChange, stockExchangeName
     * @return Boolean- true if update operation succeed.
     */
    @Override
    public Boolean add(Stock stock, String stockExchangeName) {

        Optional<StockExchange> currentStockExchangeOptional= stockExchangeRepository.findByName(stockExchangeName);
        if (currentStockExchangeOptional.isEmpty()){
            return  false;
        }

        Optional<Stock> currentStockOptional= stockRepository.findById(stock.getId());
        if (currentStockOptional.isEmpty()){
            return  false;
        }

        StockExchangeStockMap stockExchangeStockMap = new StockExchangeStockMap();

        StockExchange currentStockExchange = currentStockExchangeOptional.get();
        Stock currentStock = currentStockOptional.get();
        stockExchangeStockMap.setStockExchange(currentStockExchange);
        stockExchangeStockMap.setStock(currentStock);

        stockExchangeStockMapRepository.save(stockExchangeStockMap);

        //Set LiveInMarket true if current count > 5 after insert operation
        changeLiveInMarketStatus(currentStockExchange);

        return true;
    }

    /**
     * delete the stock from the stock exchange with stock name
     * @param  stock class to be deleted from StockChange, stockExchangeName
     * @return Boolean- true if update operation succeed.
     */
    @Override
    public Boolean delete(Stock stock, String stockExchangeName) {

        Optional<StockExchange> currentStockExchangeOptional= stockExchangeRepository.findByName(stockExchangeName);
        if (currentStockExchangeOptional.isEmpty()){
            return  false;
        }

        Optional<Stock> currentStockOptional= stockRepository.findById(stock.getId());
        if (currentStockOptional.isEmpty()){
            return  false;
        }

        StockExchange currentStockExchange= currentStockExchangeOptional.get();
        Stock currentStock= currentStockOptional.get();


        var result = stockExchangeStockMapRepository.findByStockIdAndStockExchangeId(currentStock.getId(), currentStockExchange.getId());

        stockExchangeStockMapRepository.deleteAll(result);

        //Set LiveInMarket false if current count < 5 after delete operation
        changeLiveInMarketStatus(currentStockExchange);
        return  true;
    }
    /**
     * List the related stocks from the stock exchange with stock name
     * @param  stockExchangeName
     * @return StockExchange data with Stock details
     */
    @Override
    public StockExchangeDetailDto list(String stockExchangeName) {

        Optional<StockExchange> currentStockExchangeOptional= stockExchangeRepository.findByName(stockExchangeName);
        if (currentStockExchangeOptional.isEmpty()){
            return  null;
        }
        StockExchange currentStockExchange= currentStockExchangeOptional.get();
        var result = stockExchangeStockMapRepository.findByStockExchangeId(currentStockExchange.getId()).stream().map(StockExchangeStockMap::getStock).collect(Collectors.toList());

        StockExchangeDetailDto stockExchangeDetailDto = new StockExchangeDetailDto();
        stockExchangeDetailDto.setStockList(result);
        stockExchangeDetailDto.setStockExchange(currentStockExchange);

        return stockExchangeDetailDto;

    }

    private void changeLiveInMarketStatus(StockExchange stockExchange){

        Long stockCount = stockExchangeStockMapRepository.countByStockExchangeId(stockExchange.getId());
        if(stockCount < 5){
            stockExchange.setLiveInMarket(false);
        }

        else{
            stockExchange.setLiveInMarket(true);
        }
        stockExchangeRepository.save((stockExchange));
    }
}
