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

import com.mycom.entity.Skill;
import com.mycom.entity.User;
import com.mycom.entity.Vacancy;
import com.mycom.jdbc.JdbcSkillDao;
import com.mycom.jdbc.JdbcUserDao;
import com.mycom.jdbc.JdbcVacancyDao;

@Controller
public class VacancyController {

    @Autowired
    private JdbcVacancyDao jdbcVacancyDao;

    @Autowired
    private JdbcSkillDao jdbcSkillDao;

    @Autowired
    private JdbcUserDao jdbcUserDao;

    private static List<Vacancy> list;

    private String createViewForm(Model model) {
        model.addAttribute("list", list);
        Map<Long, String> developers = new HashMap<Long, String>();
        for (User i : jdbcUserDao.findByRole("developer")) {
            developers.put(i.getId(), i.getName() + " " + i.getSurname());
        }
        model.addAttribute("developers", developers);
        return "VacancyView";
    }

    private String createVacancyForm(Model model, Vacancy vacancy) {
        Map<Long, String> developers = new HashMap<Long, String>();
        for (User i : jdbcUserDao.findByRole("developer")) {
            developers.put(i.getId(), i.getName() + " " + i.getSurname());
        }
        Map<String, String> skills = new HashMap<String, String>();
        for (Skill i : jdbcSkillDao.findAll()) {
            skills.put(i.getName(), i.getName());
        }
        model.addAttribute("skills", skills);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("developers", developers);
        return "VacancyForm";
    }

    @RequestMapping(value = "/ViewVacancyForm", method = RequestMethod.GET)
    public String viewVacancyFrom(Model model) {
        list = jdbcVacancyDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/VacancyCreate", method = RequestMethod.GET)
    public String createVacancy(Model model) {
        return createVacancyForm(model, new Vacancy());
    }

    @RequestMapping(value = "/SaveVacancy", method = RequestMethod.POST)
    public String saveVacancy(@Valid Vacancy vacancy, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return createVacancyForm(model, vacancy);
        }
        if (vacancy.getId() != null) {
            jdbcVacancyDao.update(vacancy);
        } else {
            jdbcVacancyDao.insert(vacancy);
        }
        list = jdbcVacancyDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/VacancyEdit", method = RequestMethod.GET)
    public String vacancyEdit(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        return createVacancyForm(model, jdbcVacancyDao.findById(id));
    }

    @RequestMapping(value = "/VacancyDelete", method = RequestMethod.GET)
    public String vacancyDelete(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        jdbcVacancyDao.delete(id);
        list = jdbcVacancyDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortPosition", method = RequestMethod.GET)
    public String sortPosition(Model model) {
        list = jdbcVacancyDao.sortForPosition();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortSalaryTo", method = RequestMethod.GET)
    public String sortSalaryTo(Model model) {
        list = jdbcVacancyDao.sortForSalaryTo();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortSalaryFrom", method = RequestMethod.GET)
    public String sortSalaryFrom(Model model) {
        list = jdbcVacancyDao.sortForSalaryFrom();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortExperience", method = RequestMethod.GET)
    public String sortExperience(Model model) {
        list = jdbcVacancyDao.sortForExperience();
        return createViewForm(model);
    }

}
