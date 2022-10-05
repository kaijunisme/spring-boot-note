package com.example.note.controller;

import com.example.note.dto.CoffeeDto;
import com.example.note.dto.ResponseDto;
import com.example.note.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    /**
     * 新增一筆Coffee 資料
     * @param coffeeDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ResponseDto<CoffeeDto>> addCoffee(@RequestBody CoffeeDto coffeeDto) {
        return ResponseEntity.ok(ResponseDto.ok(coffeeService.addCoffee(coffeeDto)));
    }

    /**
     * 取得所有Coffee 資料
     * @return
     */
    @GetMapping
    public ResponseEntity<ResponseDto<List<CoffeeDto>>> getAllCoffee() {
        return ResponseEntity.ok(ResponseDto.ok(coffeeService.getAllCoffee()));
    }

    /**
     * 根據指定名稱取得一筆Coffee 資料
     * @param name
     * @return
     */
    @GetMapping(value = "/{name}")
    public ResponseEntity<ResponseDto<CoffeeDto>> getCoffeeByName(@PathVariable String name) {
        return ResponseEntity.ok(ResponseDto.ok(coffeeService.getCoffeeByName(name)));
    }

    /**
     * 編輯一筆Coffee 資料
     * @param coffeeDto
     * @return
     */
    @PutMapping
    public ResponseEntity<ResponseDto<CoffeeDto>> editCoffee(@RequestBody CoffeeDto coffeeDto) {
        return ResponseEntity.ok(ResponseDto.ok(coffeeService.editCoffee(coffeeDto)));
    }

    /**
     * 根據指定名稱移除一筆Coffee 資料
     * @param name
     * @return
     */
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<ResponseDto> removeCoffee(@PathVariable String name) {
        coffeeService.removeCoffee(name);
        return ResponseEntity.ok(ResponseDto.ok());
    }

}
