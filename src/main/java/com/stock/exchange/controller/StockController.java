package com.stock.exchange.controller;


import com.stock.exchange.entity.Stock;
import com.stock.exchange.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> create( @RequestBody Stock stock) {

        stockService.create(stock);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> delete( @RequestBody Stock stock) {

        stockService.delete(stock);
        return ResponseEntity.ok(true);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> updatePrice( @RequestBody Stock stock) {

        stockService.updatePrice(stock);
        return ResponseEntity.ok(true);
    }
}
