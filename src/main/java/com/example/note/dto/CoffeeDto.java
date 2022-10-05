package com.example.note.dto;

import com.example.note.po.CoffeePo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CoffeeDto {

    private String name;
    private String productionPlace;
    private String description;

    /**
     * 將po 類轉為dto 類
     * @param articlePo
     * @return
     */
    public static CoffeeDto createByPo(CoffeePo articlePo) {
        CoffeeDto coffeeDto = new CoffeeDto();
        BeanUtils.copyProperties(articlePo, coffeeDto);

        return coffeeDto;
    }

    /**
     * 將dto 類轉為po 類
     *
     * @return
     */
    @JsonIgnore
    public CoffeePo getPo() {
        CoffeePo coffeePo = new CoffeePo();
        BeanUtils.copyProperties(this, coffeePo);

        return coffeePo;
    }

}
