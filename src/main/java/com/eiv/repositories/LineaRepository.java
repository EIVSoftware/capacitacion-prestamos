package com.eiv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.eiv.entities.LineaEntity;

public interface LineaRepository extends 
        JpaRepository<LineaEntity, Long>, QuerydslPredicateExecutor<LineaEntity> {

}