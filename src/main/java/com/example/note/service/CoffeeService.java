package com.example.note.service;

import com.example.note.dto.CoffeeDto;
import com.example.note.enumernation.ResponseEnum;
import com.example.note.error.ServiceException;
import com.example.note.po.CoffeePo;
import com.example.note.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.example.note.constant.CacheConst.COFFEE_CACHE_NAME;

@Service
@CacheConfig(cacheNames = COFFEE_CACHE_NAME)
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    /**
     * 新增一筆Coffee 資料
     * @param coffeeDto
     * @return
     */
    @Transactional
    @CachePut(key = "#coffeeDto.name")
    public CoffeeDto addCoffee(CoffeeDto coffeeDto) {
        // 檢查名稱是否重複
        if (coffeeRepository.findByName(coffeeDto.getName()).isPresent())
            throw new ServiceException(ResponseEnum.DATA_EXISTS);

        // 新增資料
        CoffeePo coffeePo = coffeeRepository.save(coffeeDto.getPo());
        return CoffeeDto.createByPo(coffeePo);
    }

    /**
     * 取得所有Coffee 資料
     * @return
     */
    @Cacheable(key = "#root.methodName")
    public List<CoffeeDto> getAllCoffee() {
        return coffeeRepository.findAll().stream()
                .map(CoffeeDto::createByPo)
                .collect(Collectors.toList());
    }

    /**
     * 根據指定名稱取得一筆Coffee 資料
     * @param name
     * @return
     */
    @Cacheable(key = "#name")
    public CoffeeDto getCoffeeByName(String name) {
        return coffeeRepository.findByName(name)
                .map(CoffeeDto::createByPo)
                .orElseThrow(() -> new ServiceException(ResponseEnum.DATA_NOT_FOUND));
    }

    /**
     * 編輯一筆Coffee 資料
     * @param coffeeDto
     * @return
     */
    @Transactional
    @CachePut(key = "#result.name")
    public CoffeeDto editCoffee(CoffeeDto coffeeDto) {
        // 根據指定名稱取得一筆Coffee 資料
        CoffeePo coffeePo = coffeeRepository.findByName(coffeeDto.getName())
                .orElseThrow(() -> new ServiceException(ResponseEnum.DATA_NOT_FOUND));

        // 更新一筆Coffee 資料
        return updateCoffee(coffeePo, po -> {
            if (StringUtils.hasText(coffeeDto.getProductionPlace()))
                coffeePo.setProductionPlace(coffeeDto.getProductionPlace());
            if (StringUtils.hasText(coffeeDto.getDescription()))
                coffeePo.setDescription(coffeeDto.getDescription());
        });
    }

    /**
     * 更新一筆Coffee 資料
     * @param coffeePo
     * @param setter
     * @return
     */
    private CoffeeDto updateCoffee(CoffeePo coffeePo, Consumer<CoffeePo> setter) {
        // 使用Consumer 替換CoffeePo 中需要更新資料
        setter.accept(coffeePo);

        // 更新資料
        coffeeRepository.save(coffeePo);
        return CoffeeDto.createByPo(coffeePo);
    }

    /**
     * 根據指定名稱移除一筆Coffee 資料
     * @param name
     */
    @Transactional
    @CacheEvict(key = "#name")
    public void removeCoffee(String name) {
        // 根據指定名稱取得一筆Coffee 資料
        Optional<CoffeePo> optional = coffeeRepository.findByName(name);

        // 檢查資料是否存在
        if (!optional.isPresent())
            throw new ServiceException(ResponseEnum.DATA_NOT_FOUND);

        // 刪除資料
        coffeeRepository.deleteByCoffeeSeq(optional.get().getCoffeeSeq());
    }

}
