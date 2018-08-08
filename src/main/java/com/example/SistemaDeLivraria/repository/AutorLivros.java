package com.example.SistemaDeLivraria.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaDeLivraria.model.AutorLivro;

public interface AutorLivros extends JpaRepository<AutorLivro, Integer> {

}
