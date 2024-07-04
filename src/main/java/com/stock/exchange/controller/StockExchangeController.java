package com.stock.exchange.controller;


import com.stock.exchange.dto.StockExchangeDetailDto;
import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;
import com.stock.exchange.service.StockExchangeService;
import com.stock.exchange.service.StockExchangeStockMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/stock-exchange")
@RequiredArgsConstructor
public class StockExchangeController {

    private final StockExchangeStockMapService stockExchangeStockMapService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "{name}")
    ResponseEntity<Boolean> add(@RequestBody Stock stock, @PathVariable String name) {

        var result = stockExchangeStockMapService.add(stock,name);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "{name}")
    ResponseEntity<StockExchangeDetailDto> list(@PathVariable String name) {

        var result = stockExchangeStockMapService.list(name);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "{name}")
    ResponseEntity<Boolean> delete(@RequestBody Stock stock, @PathVariable String name) {

        stockExchangeStockMapService.delete(stock, name);
        return ResponseEntity.ok(true);
    }
}
