package com.usst.demo.web.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/{uid}/group")
public class GroupController {
    @RequestMapping(method = RequestMethod.GET)
    public String myGroup(@PathVariable("uid")Integer id){

        return "/group/mygroup.html";
    }
}
