package com.example.SistemaDeLivraria.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaDeLivraria.model.Editora;

public interface Editoras extends JpaRepository<Editora, Integer> {

}
