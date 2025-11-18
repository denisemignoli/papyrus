package com.example.papyrus.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.papyrus.model.Livro;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {

}
