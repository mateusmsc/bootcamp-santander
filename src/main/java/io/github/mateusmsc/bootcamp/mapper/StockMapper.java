package io.github.mateusmsc.bootcamp.mapper;

import io.github.mateusmsc.bootcamp.model.Stock;
import io.github.mateusmsc.bootcamp.model.dto.StockDTO;
import io.github.mateusmsc.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


// Anotação genérica para o Spring gerenciar
@Component
public class StockMapper {
    public Stock toEntity(StockDTO dto) {

        // Receber o stock preenchido de um DTO para
        // transformar em uma entidade.
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());
        stock.setDate(dto.getDate());

        return stock;
    }

    // Sobrecarga
    public StockDTO toDto(Stock stock) {

        // Convertendo a entidade para dto
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        dto.setDate(stock.getDate());

        return dto;
    }


    public List<StockDTO> toDto (List<Stock> listStock){
        // percorre a lista item a item e vai convertendo para um Dto
        // e transforma em uma lista de retorno
        return listStock.stream().map(this::toDto).collect(Collectors.toList());
    }
}


