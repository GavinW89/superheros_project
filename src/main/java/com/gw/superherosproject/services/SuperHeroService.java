package com.gw.superherosproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gw.superherosproject.models.SuperHero;
import com.gw.superherosproject.repositories.SuperHeroRepository;

@Service
public class SuperHeroService {
	
	private final SuperHeroRepository superHeroRepo;

	public SuperHeroService(SuperHeroRepository superHeroRepo) {
		super();
		this.superHeroRepo = superHeroRepo;
	}
	
	// Find All SuperHero from Repo
	public List<SuperHero> allSuperHero(){
		return superHeroRepo.findAll();
	}
	
	// Create a SuperHero from Repo
	public SuperHero createSuperHero(SuperHero s) {
		return superHeroRepo.save(s);
	}
	
	// Find One SuperHero from Repo
	public SuperHero findSuperHero(Long id) {
		Optional<SuperHero> optionalSuperHero = superHeroRepo.findById(id);
		
		if(optionalSuperHero.isPresent()) {
			return optionalSuperHero.get();
		}else {
			return null;
		}
	}
	
	// Delete SuperHero from Repo
	public void delete(Long id) {
		superHeroRepo.deleteById(id);
	}
	
	// Update SuperHero From Repo
	public SuperHero updateSuperHero(SuperHero s) {
		return superHeroRepo.save(s);
	}
}
