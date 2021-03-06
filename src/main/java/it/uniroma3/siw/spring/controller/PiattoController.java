package it.uniroma3.siw.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.spring.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private BuffetService bs;
	
	@Autowired
	private PiattoService pis;

	@GetMapping("buffet/piatto/{idBuffet}")
	public String getBuffet(@PathVariable("idBuffet") Long id, Model model)	{
		model.addAttribute("piatto", new Piatto());
		Buffet buffet = bs.findById(id);
		model.addAttribute("buffet", buffet);
		return "piattoForm.html";
	}
	
	@PostMapping("buffet/piatto/{idBuffet}")
	public String addPiatto(@ModelAttribute("piatto") Piatto piatto, @PathVariable("idBuffet") Long id, Model model)	{
		Buffet buffet1 = bs.findById(id);
		buffet1.addPiatto(piatto);
		bs.save(buffet1);
		model.addAttribute("buffet", buffet1);
		model.addAttribute("piatti", buffet1.getPiatti());
		return "buffet.html";
	}
	
	
	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model)	{
		Piatto piatto = pis.findById(id);
		model.addAttribute("piatto",piatto);
		model.addAttribute("ingredienti", piatto.getIngredienti());
		return "piatto.html";
	}
	
	@GetMapping("/userPiatto/{id}")
	public String getPiattoUser(@PathVariable("id") Long id, Model model)	{
		Piatto piatto = pis.findById(id);
		model.addAttribute("piatto",piatto);
		model.addAttribute("ingredienti", piatto.getIngredienti());
		return "userPiatto.html";
	}
	

}
