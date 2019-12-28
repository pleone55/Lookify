package com.codingdojo.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.lookify.models.Lookify;
import com.codingdojo.lookify.services.LookifyService;

@Controller
public class LookifyController {
	private final LookifyService lookifyService;
	
	public LookifyController(LookifyService lookifyService) {
		this.lookifyService = lookifyService;
	}
	
	@RequestMapping("/")
	public String index() {
		return "/lookify/index.jsp";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model, @ModelAttribute("look") Lookify look) {
		List<Lookify> song = lookifyService.allSongs();
		model.addAttribute("song", song);
		return "/lookify/dashboard.jsp";
	}
	
	@RequestMapping("/songs/new")
	public String newSong(@ModelAttribute("song") Lookify song) {
		return "/lookify/new.jsp";
	}
	
	@RequestMapping(value="/songs/new", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("song") Lookify song, BindingResult result) {
		if(result.hasErrors()) {
			return "/lookify/new.jsp";
		}else {
			lookifyService.createSong(song);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping(value="/songs/{id}", method=RequestMethod.GET)
	public String showSongs(@PathVariable("id") Long id, Model model) {
		Lookify songs = lookifyService.findSong(id);
		model.addAttribute("songs", songs);
		return "/lookify/show.jsp";
	}
	
	@RequestMapping(value="/songs/delete/{id}")
	public String destroy(@PathVariable("id") Long id) {
		lookifyService.destroyLanguage(id);
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("artist") String artist, Model model) {
		List<Lookify> songs = lookifyService.songsContainingArtist(artist);
		model.addAttribute("artist", artist);
		model.addAttribute("songs", songs);
		return "/lookify/search.jsp";
	}
	
	@RequestMapping(value="/search/topTen", method=RequestMethod.GET)
	public String topTen(Model model) {
		List<Lookify> songs = lookifyService.topTenSongs();
		model.addAttribute("songs", songs);
		return "/lookify/topTen.jsp";
	}
}
