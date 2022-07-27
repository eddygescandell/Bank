package com.escandell.bank2.controller;

import com.escandell.bank2.service.CoustumerService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.escandell.bank2.persistence.entity.Coustumer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CoustumerController {

	private final CoustumerService coustumerService;

	public CoustumerController(CoustumerService coustumerService) {
		this.coustumerService = coustumerService;
	}

	@GetMapping(path = {"/", ""})
	public ModelAndView Inicio(){
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	@GetMapping(path = {"/coustumerlist"})
	public ModelAndView CoustumerList(){

		return new ModelAndView("coustumer/coustumerlist").addObject("coustumer", coustumerService.ListAllCoustumer());
	}

	@GetMapping(path = "/coustumer")
	public ModelAndView Coustumer(){
		return new ModelAndView("coustumer/coustumerform").addObject("coustumer", new Coustumer());
	}

	@PostMapping(path = "/coustumer")
	public String AddCoustumer(@Validated Coustumer coustumer,BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		if(bindingResult.hasErrors()){
           model.addAttribute("coustumer", coustumer);
		   return "coustumer/coustumerform";
		}
		coustumerService.CreatedCoustumer(coustumer);
        redirectAttributes.addFlashAttribute( "msgSucces", "The Coustumer has been created successfully");
		return "redirect:/coustumerlist";
	}

	@GetMapping(path = "/{id}/editcoustumer")
	public ModelAndView GetCoustumerById(@PathVariable Integer id){
		Coustumer coustumer= coustumerService.GetCoustumerById(id);
		return new ModelAndView("coustumer/coustumerform").addObject("coustumer", coustumer);
	}

	@PostMapping(path = "/{id}/editcoustumer")
	public String EditCoustumerById(@PathVariable Integer id,@Validated Coustumer coustumer,BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
		Coustumer coustumerDB = coustumerService.GetCoustumerById(id);
		if(bindingResult.hasErrors()){
			model.addAttribute("coustumer", coustumer);
			return "coustumer/coustumerform";
		}
		coustumer.setIdentification(coustumer.getIdentification());
		coustumer.setAddress(coustumer.getAddress());
		coustumerDB.setName(coustumer.getName());
		coustumerDB.setLast_name(coustumer.getLast_name());

		coustumerService.CreatedCoustumer(coustumerDB);
		redirectAttributes.addFlashAttribute( "msgSucces", "The Coustumer has been update successfully");
		return "redirect:/coustumerlist ";
	}

//	@PostMapping(path = "/{id}/deletcoustumer")
//	public String DeleteCoustumer(@PathVariable Integer id, RedirectAttributes redirectAttributes){
//		coustumerService.DeleteCoustumerByID(id);
//		redirectAttributes.addFlashAttribute( "msgSucces", "The Coustumer has been delete successfully");
//		return "redirect:/coustumerlist ";
//	}
	@GetMapping(path = "/{id}/deletcoustumer")
	public ModelAndView DeleteCoustumer (@PathVariable(name = "id") Integer id, ModelAndView view){
		coustumerService.DeleteCoustumerByID(id);
		view.setViewName("coustumer/coustumerlist");
		view.addObject("coustumer", coustumerService.ListAllCoustumer());
		return view;

	}

}
