package com.example.storagemanagerapp.repository;

import com.example.storagemanagerapp.models.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, String> {
    Section findById(long id);
}
