package com.codingdojo.lookify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.lookify.models.Lookify;
import com.codingdojo.lookify.respositories.LookifyRepository;

@Service
public class LookifyService {
	//adding the language repository as a dependency
	private final LookifyRepository lookifyRepository;
	
	public LookifyService(LookifyRepository lookifyRepository) {
		this.lookifyRepository = lookifyRepository;
	}
	
	//return all languages
	public List<Lookify> allSongs(){
		return lookifyRepository.findAll();
	}
	
	//create a language
	public Lookify createSong(Lookify look) {
		return lookifyRepository.save(look);
	}
	
	//find a language
	public Lookify findSong(Long id) {
		Optional<Lookify> lang = lookifyRepository.findById(id);
		if(lang.isPresent()) {
			return lang.get();
		}else {
			return null;
		}
	}
	
	//delete a language
	public void destroyLanguage(Long id) {
		Optional<Lookify> lang = lookifyRepository.findById(id);
		if(lang.isPresent()) {
			lookifyRepository.deleteById(id);
		}
	}

	public List<Lookify> songsContainingArtist(String artist) {
		return lookifyRepository.findByArtistContaining(artist);
	}
	
	public List<Lookify> topTenSongs(){
		return lookifyRepository.findTop10ByOrderByRatingDesc();
	}
}
