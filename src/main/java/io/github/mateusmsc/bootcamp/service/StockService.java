package io.github.mateusmsc.bootcamp.service;

import io.github.mateusmsc.bootcamp.exception.BusinessException;
import io.github.mateusmsc.bootcamp.exception.NotFoundException;
import io.github.mateusmsc.bootcamp.mapper.StockMapper;
import io.github.mateusmsc.bootcamp.model.Stock;
import io.github.mateusmsc.bootcamp.model.dto.StockDTO;
import io.github.mateusmsc.bootcamp.repository.StockRepository;
import io.github.mateusmsc.bootcamp.util.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    // Acesso ao repositório para o objeto
    // na base
    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {

        // verificação se já existe (validação)
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessagesUtil.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findById(dto.getId());
        if(optionalStock.isPresent()){
            Stock stock = mapper.toEntity(dto);
            repository.save(stock);
            return mapper.toDto(stock);
        }else {
            throw new BusinessException(MessagesUtil.STOCK_NOT_EXISTS);
        }
    }

    @Transactional
    public StockDTO delete(Long id) {
        // verifica se existe esse registro na
        // base de dados utilizando a função de
        // busca pelo id que foi reescrita
        StockDTO dto = this.findById(id);
        repository.deleteById(dto.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        //List<Stock> list = repository.findAll();
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }
/*
    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday();
    }*/
}
