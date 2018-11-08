package com.mycom.controller;

import com.mycom.entity.FeedBack;
import com.mycom.entity.FeedBackState;
import com.mycom.entity.Interview;
import com.mycom.entity.User;
import com.mycom.jdbc.JdbcFeedBackDao;
import com.mycom.jdbc.JdbcFeedBackStateDao;
import com.mycom.jdbc.JdbcInterviewDao;
import com.mycom.jdbc.JdbcUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FeedbackController {

    @Autowired
    private JdbcFeedBackDao jdbcFeedBackDao;
    @Autowired
    private JdbcFeedBackStateDao jdbcFeedBackStateDao;
    @Autowired
    private JdbcInterviewDao jdbcInterviewDao;
    @Autowired
    private JdbcUserDao jdbcUserDao;

    private List<FeedBack> list;

    private String createViewForm(Model model) {
        model.addAttribute("list", list);
        return "FeedBackView";
    }

    private String createFeedBackForm(Model model, FeedBack feedback) {
        Map<String, String> feedbackstates = new HashMap<String, String>();
        for (FeedBackState i : jdbcFeedBackStateDao.findAll()) {
            feedbackstates.put(i.getName(), i.getName());
        }
        Map<Long, String> interviews = new HashMap<Long, String>();
        for (Interview i : jdbcInterviewDao.findAll()) {
            interviews.put(i.getId(), i.getName());
        }
        Map<Long, String> developers = new HashMap<Long, String>();
        for (User i : jdbcUserDao.findByRole("developer")) {
            developers.put(i.getId(), i.getName() + " " + i.getSurname());
        }
        model.addAttribute("feedback", feedback);
        model.addAttribute("interviews", interviews);
        model.addAttribute("feedbackstates", feedbackstates);
        model.addAttribute("developers", developers);
        return "FeedBackForm";
    }

    @RequestMapping(value = "/ViewFeedbackForm", method = RequestMethod.GET)
    public String viewFeedBackForm(Model model) {
        list = jdbcFeedBackDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/FeedbackCreate", method = RequestMethod.GET)
    public String createFeedback(Model model) {
        return createFeedBackForm(model, new FeedBack());
    }

    @RequestMapping(value = "/SaveFeedback", method = RequestMethod.POST)
    public String saveFeedBack(@Valid FeedBack feedback, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return createFeedBackForm(model, feedback);
        }
        if (feedback.getId() != null) {
            jdbcFeedBackDao.update(feedback);
        } else {
            jdbcFeedBackDao.insert(feedback);
        }
        list = jdbcFeedBackDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/FeedBackEdit", method = RequestMethod.GET)
    public String interviewEdit(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        return createFeedBackForm(model, jdbcFeedBackDao.findById(id));
    }

    @RequestMapping(value = "/FeedBackDelete", method = RequestMethod.GET)
    public String interviewDelete(Model model, HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("id"));
        jdbcFeedBackDao.delete(id);
        list = jdbcFeedBackDao.findAll();
        return createViewForm(model);
    }

    @RequestMapping(value = "/FeedBackFilter", method = RequestMethod.GET)
    public String filterFeedBack(Model model, HttpServletRequest request) {
        String type = request.getParameter("type");
        list = jdbcFeedBackDao.findByState(type);
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortReason", method = RequestMethod.GET)
    public String sortReason(Model model) {
        list = jdbcFeedBackDao.sortByReason();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortInterviewer", method = RequestMethod.GET)
    public String sortInterviewer(Model model) {
        list = jdbcFeedBackDao.sortByInterviewer();
        return createViewForm(model);
    }

    @RequestMapping(value = "/SortInterview", method = RequestMethod.GET)
    public String sortInterview(Model model) {
        list = jdbcFeedBackDao.sortByInterview();
        return createViewForm(model);
    }

}
