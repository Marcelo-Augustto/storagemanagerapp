package com.example.storagemanagerapp.repository;

import com.example.storagemanagerapp.models.Product;
import com.example.storagemanagerapp.models.Section;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    Iterable<Product> findBySection(Section section);
    Product findBySerial(String serial);
}
