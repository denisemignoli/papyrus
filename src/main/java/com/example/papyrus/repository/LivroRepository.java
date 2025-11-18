package com.example.papyrus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.papyrus.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
