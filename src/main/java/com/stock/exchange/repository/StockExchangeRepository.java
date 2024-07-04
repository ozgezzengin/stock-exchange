package com.stock.exchange.repository;

import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange,Long> {
    Optional<StockExchange> findByName(String stockExchangeName);
}
