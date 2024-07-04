package com.stock.exchange.controller;


import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;
import com.stock.exchange.service.StockExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/stock-exchange-create")
@RequiredArgsConstructor
public class StockExchangeCreateController {

    private final StockExchangeService stockExchangeService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> create( @RequestBody StockExchange stockExchange) {

        stockExchangeService.create(stockExchange);
        return ResponseEntity.ok(true);
    }
}
