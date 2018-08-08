package com.example.SistemaDeLivraria.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaDeLivraria.model.Livro;

public interface Livros extends JpaRepository<Livro, Serializable> {

}
