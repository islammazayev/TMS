package se.TMS.controller;


import se.TMS.data_access.TimeLogDao;
import se.TMS.model.TimeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import Project.LI.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TimeLogController {
    @Autowired
    private TimeLogDao timeLogDao;


    @GetMapping("/startTime")
    public String startTime(Model model, String user)
    {
        model.addAttribute("user", user);
        return "startTime";
    }

    @PostMapping("/startTime")
    public String startTime(@ModelAttribute("startTime") TimeLog timeLog, Model model, String user) {

        timeLog.setStartTime(LocalDateTime.now());
        timeLog.setUserId((long) Integer.parseInt(user));
        timeLog = timeLogDao.save(timeLog);
        model.addAttribute("timeLog", timeLog );
        model.addAttribute("user", user);
        return "stopTime";
    }

    @PostMapping("/stopTime")
    public String stopTime(@ModelAttribute("stopTime")TimeLog timeLog, Model model, String user) {

        Iterable<TimeLog> timeLogList = timeLogDao.findAll(); // read all DB
        int i = ((List<TimeLog>) timeLogList).size() - 1; // find last index
        TimeLog tl = ((List<TimeLog>) timeLogList).get(i); // get Object
        tl.setStopTime(LocalDateTime.now());
        timeLogDao.save(tl);
        model.addAttribute("user", user);

        return "startTime";
    }
}
