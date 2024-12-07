package com.spring.project.organicfoodshop.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T, ID> extends CrudRepository<T, ID> {

    default T findByIdOrThrow(ID id){
        return findById(id).orElseThrow(()
                -> new EntityNotFoundException("Entity not found with id: " + id));
    }
}
