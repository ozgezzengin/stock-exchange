package com.stock.exchange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
public class StockExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique=true)
    private String name;

    private String description;

    private Boolean liveInMarket;

    @OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "stockExchange")
    @JsonIgnore
    private List<StockExchangeStockMap> stockExchangeStocks;
}
