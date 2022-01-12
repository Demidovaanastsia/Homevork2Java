package com.example.demo.controllers;

import com.example.demo.entity.Address;
import com.example.demo.entity.Child;
import com.example.demo.entity.Parent;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ChildRepository;
import com.example.demo.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ParentController {

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/parentsList")
    public String getParentsList(@RequestParam(required = false) String filter, Model model) {

        Iterable<Parent> parents = parentRepository.findAll();
        Iterable<Address> addresses = addressRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            parents = parentRepository.findByFirstName(filter);
        }

        model.addAttribute("parents", parents);
        model.addAttribute("addresses", addresses);
        model.addAttribute("filter", filter);

        return "parentsList";
    }

    @PostMapping("/parentsList")
    public String addNewParent(@RequestParam String firstName,
                      @RequestParam String lastName,
                      @RequestParam String patronymicName,
                      @RequestParam("addressId") Address address,
                      Model model) {
        Parent parent = new Parent(firstName, lastName, patronymicName, address);

        parentRepository.save(parent);

        Iterable<Parent> parents = parentRepository.findAll();
        Iterable<Address> addresses = addressRepository.findAll();

        model.addAttribute("parents", parents);
        model.addAttribute("addresses", addresses);

        return "parentsList";
    }

    @GetMapping("/parentChange/{parent}")
    public String parentEditForm(@PathVariable Parent parent, Model model) {

        Boolean hasChild = true;
        List<Child> children = parent.getChildren();
        if (children == null) {
            hasChild = false;
        }

        Iterable<Child> allChildren = childRepository.findAll();
        Iterable<Address> addresses = addressRepository.findAll();

        model.addAttribute("parent", parent);
        model.addAttribute("allChildren", allChildren);
        model.addAttribute("children", children);
        model.addAttribute("hasChild", hasChild);
        model.addAttribute("addresses", addresses);

        return "parentEdit";
    }

    @PostMapping("/parentChange")
    public String saveChangeParent(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String patronymicName,
            @RequestParam("parentId") Parent parent,
            @RequestParam("addressId") Address address){

        parent.setFirstName(firstName);
        parent.setLastName(lastName);
        parent.setPatronymicName(patronymicName);
        parent.setAddressParent(address);

        parentRepository.save(parent);

        return "redirect:/parentsList";
    }

    @PostMapping("/addChild")
    public String addChild(

            @RequestParam("childId") Child child,
            @RequestParam("parentId") Parent parent,
            Model model){

        Long parentId = parent.getId();
        parent.addChild(child);
        parentRepository.save(parent);

        return "redirect:/parentChange/"+parentId;
    }
}
