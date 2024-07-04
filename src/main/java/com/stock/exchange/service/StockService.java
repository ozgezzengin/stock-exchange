package com.stock.exchange.service;

import com.stock.exchange.entity.Stock;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StyledEditorKit;

public interface StockService {

     Boolean create(Stock stock);

     Boolean updatePrice(Stock stock);

     Boolean delete(Stock stock);
}
