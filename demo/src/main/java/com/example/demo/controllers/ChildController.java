package com.example.demo.controllers;

import com.example.demo.entity.*;
import com.example.demo.repository.ChildRepository;
import com.example.demo.repository.EducationalInstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChildController {

    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private EducationalInstitutionRepository educationalInstitutionRepository;

    @GetMapping("/childrenList")
    public String getChildrenList(@RequestParam(required = false) String filter, Model model) {

        Iterable<Child> children = childRepository.findAll();
        Iterable<EducationalInstitution> educationalInstitutions = educationalInstitutionRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            children = childRepository.findByFirstName(filter);
        }

        model.addAttribute("children", children);
        model.addAttribute("edInsts", educationalInstitutions);
        model.addAttribute("filter", filter);

        return "childrenList";
    }

    @PostMapping("/childrenList")
    public String addNewChild(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String patronymicName,
                              @RequestParam Integer age,
                               Model model) {

        Child child = new Child(firstName, lastName, patronymicName, age);
        childRepository.save(child);

        Iterable<Child> children = childRepository.findAll();
        Iterable<EducationalInstitution> educationalInstitutions = educationalInstitutionRepository.findAll();

        model.addAttribute("children", children);
        model.addAttribute("edInsts", educationalInstitutions);

        return "childrenList";
    }

    @GetMapping("/childChange/{child}")
    public String childEditForm(@PathVariable Child child, Model model) {

        // Подбор школ только в районах проживания родителей
        List<Address> allAddressesByDistrict = new ArrayList<>();
        List<EducationalInstitution> allEducationalInstitutionsByDistrict = new ArrayList<>();

        List<Parent> parents = child.getParents();
        for(Parent p: parents){
            allAddressesByDistrict.addAll(
                    p
                            .getAddressParent() //Текущий адрес родителя
                            .getDistrict()      //Текущий район проживания родителя
                            .getAddresses()     //Все адреса по району
            );
        }

        for(Address a: allAddressesByDistrict){
            allEducationalInstitutionsByDistrict.addAll(a.getEducationalInstitutions());
        }
        //

        Boolean hasEducInst = true;

        if(child.getEducationalInstitution().getNumber() == " "){
            hasEducInst = false;
        }

        model.addAttribute("child", child);
        model.addAttribute("edInsts", allEducationalInstitutionsByDistrict);
        model.addAttribute("hasEducInst",hasEducInst);

        return "childEdit";
    }

    @PostMapping("/childChange")
    public String saveChangeChild(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String patronymicName,
            @RequestParam Integer age,
            @RequestParam("childId") Child child,
            @RequestParam(name = "edInstId", required = false) EducationalInstitution educationalInstitution){

        child.setFirstName(firstName);
        child.setLastName(lastName);
        child.setPatronymicName(patronymicName);
        child.setAge(age);

        if(educationalInstitution != null){
            child.setEducationalInstitution(educationalInstitution);
        }

        childRepository.save(child);

        return "redirect:/childrenList";
    }
}