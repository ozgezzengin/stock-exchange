package com.stock.exchange.entity;

import lombok.Data;

import javax.persistence.*;

@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"stockId", "stockExchangeId"})
})
@Entity
@Data
public class StockExchangeStockMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="stockId" )
    private Stock stock;

    @ManyToOne
    @JoinColumn(name ="stockExchangeId" )
    private StockExchange stockExchange;

}
