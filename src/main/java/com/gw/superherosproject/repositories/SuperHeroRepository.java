package com.gw.superherosproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gw.superherosproject.models.SuperHero;

@Repository
public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {
	List<SuperHero> findAll();
}
