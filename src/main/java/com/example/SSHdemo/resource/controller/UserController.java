package com.example.SSHdemo.resource.controller;

import com.example.SSHdemo.common.Pagination;
import com.example.SSHdemo.resource.entity.User;
import com.example.SSHdemo.resource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")  //  127.0.0.1:8080/user/getAll
    @ResponseBody
    public List<User> getAllUsers(){
        return  userService.getAllUsers();
    }

    @GetMapping("/getAllById")  // 127.0.0.1:8080/user/getAllById
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')") //权限格式为ROLE_XXX，是Spring Security规定的，不要乱起名字
    public List<User> getUserPage(@RequestParam(value = "userId",required = false,defaultValue = "0") Integer userId,
                                  @RequestParam(value = "currentPage", required = false,defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "2") Integer pageSize){
        System.out.println("user/getAllById "+"userId:"+userId+" currentPage:"+currentPage+" pageSize:"+pageSize);
        return userService.getAllUsersByStartId(userId,currentPage,pageSize).getContent();
    }

    @GetMapping("/getAllById2")  // 127.0.0.1:8080/user/getAllById2
    public ModelAndView getUserPage2(@RequestParam(value = "userId",required = false,defaultValue = "0") Integer userId,
                                     @RequestParam(value = "currentPage", required = false,defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "2") Integer pageSize){
        System.out.println("user/getAllById "+"userId:"+userId+" currentPage:"+currentPage+" pageSize:"+pageSize);
        ModelAndView modelAndView = new ModelAndView("main");
        Page<User> users = userService.getAllUsersByStartId(userId,currentPage,pageSize);
        modelAndView.addObject("users", users.getContent());
        modelAndView.addObject("pagination", new Pagination(
                "main.html", currentPage,
                Math.max(currentPage - pageSize, 1),
                Math.min(currentPage + pageSize, users.getTotalPages() + 1),
                pageSize,
                users.getTotalPages() + 1, users.isFirst(), users.isLast())
        );
        return modelAndView;
    }
}
