package com.usst.demo.web.user;

import com.usst.demo.repo.UserRepository;
import com.usst.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
@Controller
@RequestMapping({"/register","/register.do"})
public class UserRegisterController {
    private UserRepository userRepository;

    @Autowired
    public UserRegisterController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String register(){
        return "/user/register.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute User user, Errors errors){
        System.out.println("用户名："+user.getUsername());
        System.out.println("密码："+user.getPassword());
        if(errors.hasErrors() || !userRepository.doRegister(user)){
            //失败
            System.out.println("注册失败");
            return "/user/register.html";
        }else{
            System.out.println("用户id:"+user.getUid());
            return "redirect:/";
        }
    }
}
