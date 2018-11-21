package com.usst.demo.web.admin;

import com.usst.demo.repo.FieldTagRepository;
import com.usst.demo.repo.PersonalityTagRepository;
import com.usst.demo.vo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    FieldTagRepository fieldTagRepository;
    @Autowired
    PersonalityTagRepository personalityTagRepository;

    @RequestMapping("/showpersonalitytag")
    public String personalityTag(Model model){
        List<Tag> personalitytag = personalityTagRepository.getAllTags();
        model.addAttribute("personalitytaglist",personalitytag);
        return "/admin/personalitytag.html";
    }

    @RequestMapping("/showfieldtag")
    public String fieldTag(Model model){
        List<Tag> fieldtag = fieldTagRepository.getAllTags();
        model.addAttribute("fieldtaglist",fieldtag);
        return "/admin/fieldtag.html";
    }

    @RequestMapping(value = "/addfieldtag", method = RequestMethod.POST)
    public String addNewTag(@ModelAttribute Tag tag){
        fieldTagRepository.saveNewTag(tag);
        return "redirect:/admin/showfieldtag";
    }

    @RequestMapping(value = "/addpersonalitytag", method = RequestMethod.POST)
    public String addNewPersonTag(@ModelAttribute Tag tag){
        personalityTagRepository.saveNewTag(tag);
        return "redirect:/admin/showpersonalitytag";
    }
}
