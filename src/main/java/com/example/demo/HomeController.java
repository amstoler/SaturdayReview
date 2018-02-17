package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PersonRepo personRepo;

    @Autowired
    EducationRepo educationRepo;

    @GetMapping("/")
    public String showHome(){
        return "home";
    }


    @GetMapping("/addPerson")
    public String personForm(Model model){

        Person person = new Person();

        model.addAttribute("person", person);

        return "personForm";
    }

    @PostMapping("/addPerson")
    public String addPerson(@Valid @ModelAttribute("person") Person person, Model model, BindingResult result){
        if(result.hasErrors()){
            return "personForm";
        }
        personRepo.save(person);
      /*  model.addAttribute("personlist",personRepo.findAll());*/

        return "home";
    }

    @GetMapping("/addEducation")
    public String educationForm(Model model){

        Education education = new Education();

        model.addAttribute("education", education);

        return "educationForm";
    }

    @PostMapping

    public String addEducation(@Valid @ModelAttribute("education") Education education, Model model, BindingResult result){
        if(result.hasErrors()){
            return "personForm";
        }
        educationRepo.save(education);
        model.addAttribute("education", education);

        return "home";
    }

    @GetMapping("/compResume")
    public String completeresume(Model model){
        model.addAttribute("persons",personRepo.findAll());
        model.addAttribute("education",educationRepo.findAll());

        return "compResume";
    }

    @RequestMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personRepo.findOne(id));
        return "personForm";
    }

    @RequestMapping("/updateEducation/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model){
        model.addAttribute("education", educationRepo.findOne(id));
        return "educationForm";
    }



}
