package com.example.SistemaDeLivraria.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaDeLivraria.model.CategoriaLivro;

import antlr.collections.List;

public interface CategoriaLivros extends JpaRepository<CategoriaLivro, Integer> {

}
