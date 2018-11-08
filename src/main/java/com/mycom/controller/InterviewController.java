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

import com.mycom.jdbc.JdbcCandidateDao;
import com.mycom.jdbc.JdbcInterviewDao;
import com.mycom.jdbc.JdbcVacancyDao;
import com.mycom.entity.Candidate;
import com.mycom.entity.Interview;
import com.mycom.entity.Vacancy;

@Controller
public class InterviewController {

    @Autowired
    private JdbcInterviewDao jdbcInterviewDao;

    @Autowired
    private JdbcCandidateDao jdbcCandidateDao;

    @Autowired
    private JdbcVacancyDao jdbcVacancyDao;

    private List<Interview> list;

    private String createViewForm(Model model) {
        model.addAttribute("list", list);
        return "InterviewView";
    }

    private String createInterviewForm(Model model, Interview interview) {
        Map<Long, String> candidates = new HashMap<Long, String>();
        for (Candidate i : jdbcCandidateDao.findAll()) {
            candidates.put(i.getId(), i.getName() + " " + i.getSurname());
        }
        Map<Long, String> vacancys = new HashMap<Long, String>();
        for (Vacancy i : jdbcVacancyDao.findAll()) {
            vacancys.put(i.getId(), i.getPosition());
        }
        model.addAttribute("candidates", candidates);
        model.addAttribute("vacancys", vacancys);
        model.addAttribute("interview", interview);
        return "InterviewForm";
    }

    @RequestMapping(value = "/InterviewView", method = RequestMethod.GET)
    public String interviewView(Model model) {
        list = jdbcInterviewDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/InterviewCreate", method = RequestMethod.GET)
    public String createInterview(Model model) {
        return createInterviewForm(model, new Interview());
    }

    @RequestMapping(value = "/SaveInterview", method = RequestMethod.POST)
    public String saveInterview(@Valid Interview interview, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return createInterviewForm(model, interview);
        }
        if (interview.getId() != null) {
            jdbcInterviewDao.update(interview);
        } else {
            jdbcInterviewDao.insert(interview);
        }
        list = jdbcInterviewDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/InterviewEdit", method = RequestMethod.GET)
    public String interviewEdit(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        return createInterviewForm(model, jdbcInterviewDao.findById(id));
    }

    @RequestMapping(value = "/InterviewDelete", method = RequestMethod.GET)
    public String interviewDelete(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        jdbcInterviewDao.delete(id);
        list = jdbcInterviewDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortPlanDate", method = RequestMethod.GET)
    public String sortPlanDate(Model model) {
        list = jdbcInterviewDao.sortByDatePlan();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortFactDate", method = RequestMethod.GET)
    public String sortFactDate(Model model) {
        list = jdbcInterviewDao.sortByDateFact();
        return createViewForm(model);
    }

}
