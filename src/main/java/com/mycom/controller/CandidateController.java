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

import com.mycom.entity.Candidate;
import com.mycom.entity.CandidateState;
import com.mycom.entity.Skill;
import com.mycom.jdbc.JdbcCandidateDao;
import com.mycom.jdbc.JdbcCandidateStateDao;
import com.mycom.jdbc.JdbcSkillDao;

@Controller
public class CandidateController {

    @Autowired
    private JdbcCandidateDao jdbcCandidateDao;

    @Autowired
    private JdbcCandidateStateDao jdbcCandidateStateDao;

    @Autowired
    private JdbcSkillDao jdbcSkillDao;

    private List<Candidate> list;

    private String createViewForm(Model model) {
        model.addAttribute("list", list);
        return "CandidateView";
    }

    private String createCandidateForm(Model model, Candidate candidate) {
        Map<String, String> states = new HashMap<String, String>();
        for (CandidateState i : jdbcCandidateStateDao.findAll()) {
            states.put(i.getName(), i.getName());
        }
        Map<String, String> skills = new HashMap<String, String>();
        for (Skill i : jdbcSkillDao.findAll()) {
            skills.put(i.getName(), i.getName());
        }
        model.addAttribute("skills", skills);
        model.addAttribute("candidate", candidate);
        model.addAttribute("candidatestate", states);
        return "CandidateForm";
    }

    @RequestMapping(value = "/ViewCandidateForm", method = RequestMethod.GET)
    public String viewCandidateForm(Model model) {
        list = jdbcCandidateDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/CandidateCreate", method = RequestMethod.GET)
    public String createCandidate(Model model) {
        return createCandidateForm(model, new Candidate());
    }

    @RequestMapping(value = "/SaveCandidate", method = RequestMethod.POST)
    public String saveCandidate(@Valid Candidate candidate,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return createCandidateForm(model, candidate);
        }
        if (candidate.getId() != null) {
            jdbcCandidateDao.update(candidate);
        } else {
            jdbcCandidateDao.insert(candidate);
        }
        list = jdbcCandidateDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "CandidateEdit", method = RequestMethod.GET)
    public String candidateEdit(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        return createCandidateForm(model, jdbcCandidateDao.findById(id));
    }

    @RequestMapping(value = "CandidateDelete", method = RequestMethod.GET)
    public String candidateDelete(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        jdbcCandidateDao.delete(id);
        list = jdbcCandidateDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/CandidateFilter", method = RequestMethod.GET)
    public String candidateFilter(Model model, HttpServletRequest request) {
        String state = request.getParameter("type");
        list = jdbcCandidateDao.findByState(state);
        return createViewForm(model);
    }

    @RequestMapping(value = "/CandidateSortName", method = RequestMethod.GET)
    public String candidateSortName(Model model) {
        list = jdbcCandidateDao.sortNameCandidate();
        return createViewForm(model);
    }

    @RequestMapping(value = "/CandidateSortSalary", method = RequestMethod.GET)
    public String candidateSortSalary(Model model) {
        list = jdbcCandidateDao.sortSalaryCandidate();
        return createViewForm(model);
    }
}