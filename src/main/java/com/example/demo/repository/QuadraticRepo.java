package com.example.demo.repository;

import com.example.demo.model.Quadratic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadraticRepo extends CrudRepository<Quadratic, Quadratic.QuadraticKey> {
}
