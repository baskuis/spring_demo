package com.example.demo.store;

import org.springframework.data.repository.CrudRepository;

public interface ProductEntityCrudRepository extends CrudRepository<ProductEntity, Long> {
}
