package com.stock.exchange.repository;


import com.stock.exchange.entity.Stock;
import com.stock.exchange.entity.StockExchangeStockMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockExchangeStockMapRepository extends JpaRepository<StockExchangeStockMap,Long> {

    List<StockExchangeStockMap> findByStockId(Long stockId);

    Long countByStockExchangeId(Long stockExchangeId);

    List<StockExchangeStockMap> findByStockExchangeId(Long stockExchangeId);

    List<StockExchangeStockMap> findByStockIdAndStockExchangeId(Long stockId, Long stockExchangeId);
}
