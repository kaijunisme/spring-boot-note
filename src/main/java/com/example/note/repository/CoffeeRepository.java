package com.example.note.repository;

import com.example.note.po.CoffeePo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeRepository extends CrudRepository<CoffeePo, Integer> {

    public List<CoffeePo> findAll();
    public Optional<CoffeePo> findByName(String name);
    public void deleteByCoffeeSeq(int seq);

}
