package com.usst.demo.web.user;

import com.usst.demo.repo.UserRepository;
import com.usst.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@Controller
@RequestMapping({"/login.do","/login"})
public class UserLoginController {

    private UserRepository userRepository;
    @Autowired
    public UserLoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpServletRequest request, @Valid @ModelAttribute User user, Errors errors){
        if(errors.hasErrors()){
            System.out.println("出错！");
                return "redirect:login.do";
        }
        System.out.println("登录中，用户名:"+user.getUsername()+"\n密码:"+user.getPassword());
        if(userRepository.doLogin(user)){
            request.getSession().setAttribute("user",user);
            return "redirect:user/"+user.getUid();
        }else{
            System.out.println("登录失败");
            return "redirect:login.do";
        }
    }
    @RequestMapping(method = RequestMethod.GET)
    public String login(){
        return "/user/login.html";
    }
}
