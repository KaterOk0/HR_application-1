package com.mycom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.entity.Skill;
import com.mycom.jdbc.JdbcSkillDao;

@Controller
public class SkillController {

    @Autowired
    private JdbcSkillDao jdbcSkillDao;

    private List<Skill> list;

    private String createViewForm(Model model) {
        list = jdbcSkillDao.findAll();
        model.addAttribute("list", list);
        return "SkillView";
    }

    private String createSkillForm(Model model, Skill skill) {
        model.addAttribute("skill", skill);
        return "SkillForm";
    }

    @RequestMapping(value = "/ViewSkillForm", method = RequestMethod.GET)
    public String viewSkillForm(Model model) {
        list = jdbcSkillDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SkillCreate", method = RequestMethod.GET)
    public String createSkill(Model model) {
        return createSkillForm(model, new Skill());
    }

    @RequestMapping(value = "/SaveSkill", method = RequestMethod.POST)
    public String saveSkill(@Valid Skill skill, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return createSkillForm(model, skill);
        }
        jdbcSkillDao.insert(skill);
        return createViewForm(model);
    }

    @RequestMapping(value = "/SkillDelete", method = RequestMethod.GET)
    public String skillDelete(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        jdbcSkillDao.delete(name);
        list = jdbcSkillDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SkillSort", method = RequestMethod.GET)
    public String skillSort(Model model) {
        list = jdbcSkillDao.sortSkill();
        return createViewForm(model);
    }
}
