package com.usst.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

public class TestController {
    @RequestMapping("/index")
    public String index(){
        return "hello";
    }
    @RequestMapping("/person")
    public String person(){
        return "personalInfo";
    }

    private ContactRepository contactRepository;
    @Autowired
    public TestController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String home(Map<String,Object> model){
        List<Contact> contacts = contactRepository.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Contact contact){
        contactRepository.save(contact);
        return "redirect:/";
    }
}
