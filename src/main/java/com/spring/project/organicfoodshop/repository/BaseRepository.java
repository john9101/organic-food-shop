package com.spring.project.organicfoodshop.repository;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.reflect.Typed;
import org.springframework.data.repository.CrudRepository;

public interface BaseRepository <T, ID> extends CrudRepository<T, ID> {

    default T findByIdOrThrow(ID id){
        return findById(id).orElseThrow(()
                -> new EntityNotFoundException("Entity not found with id: " + id));
    }
}
