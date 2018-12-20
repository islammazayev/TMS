package se.TMS.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTimeReport {
    @GetMapping("/UserTimeReport")
    public String timeReport(Model model, String user)
    {

        model.addAttribute("user", user);
        return "UserTimeReport";
    }
}
