package com.codingdojo.lookify.respositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.lookify.models.Lookify;

@Repository
public interface LookifyRepository extends CrudRepository<Lookify, Long> {
	// this method retrieves all the languages from the database
	List<Lookify> findAll();
	
	// find artist based on certain criteria
	List<Lookify> findByArtistContaining(String search); 
	
	// query top 10 by rating
	List<Lookify> findTop10ByOrderByRatingDesc(); 
}
