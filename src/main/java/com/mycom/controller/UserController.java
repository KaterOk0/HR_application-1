package com.mycom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.entity.User;
import com.mycom.jdbc.JdbcUserDao;

@Controller
public class UserController {

    @Autowired
    private JdbcUserDao jdbcUserDao;

    private static List<User> list;

    private String createViewForm(Model model) {
        //List<User> list = jdbcUserDao.findAll();
        model.addAttribute("list", list);
        return "UserView";
    }

    private String createUserForm(Model model, User user) {
        Map<String, String> role = new HashMap<String, String>();
        role.put("manager", "manager");
        role.put("developer", "developer");
        model.addAttribute("user", user);
        model.addAttribute("map", role);
        return "UserForm";
    }

    @RequestMapping(value = "/ViewUserForm", method = RequestMethod.GET)
    public String viewUserForm(Model model) {
        list = jdbcUserDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/UserCreate", method = RequestMethod.GET)
    public String createUser(Model model) {
        return createUserForm(model, new User());
    }

    @RequestMapping(value = "/SaveUser", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return createUserForm(model, user);
        }
        if (user.getId() != null) {
            jdbcUserDao.update(user);
        } else {
            jdbcUserDao.insert(user);
        }
        list = jdbcUserDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/UserEdit", method = RequestMethod.GET)
    public String userEdit(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        return createUserForm(model, jdbcUserDao.findById(id));
    }

    @RequestMapping(value = "/UserDelete", method = RequestMethod.GET)
    public String userDelete(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        jdbcUserDao.delete(id);
        list = jdbcUserDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/UserFilter", method = RequestMethod.GET)
    public String filterUser(Model model, HttpServletRequest request) {
        String type = request.getParameter("type");
        list = jdbcUserDao.findByRole(type);
        return createViewForm(model);
    }

    @RequestMapping(value = "/UserSortName", method = RequestMethod.GET)
    public String userSortName(Model model, HttpServletRequest request) {
        list = jdbcUserDao.findAllSortName();
        return createViewForm(model);
    }

}
