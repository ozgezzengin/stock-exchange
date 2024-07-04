package com.stock.exchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Table
@Entity
@Data
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

     String name;

     String description;

     BigDecimal currentPrice;

     OffsetDateTime lastUpdate;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "stock")
    @JsonIgnore
     List<StockExchangeStockMap> stockExchangeStocks;
}
