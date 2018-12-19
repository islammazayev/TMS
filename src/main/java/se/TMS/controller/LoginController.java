package se.TMS.controller;

import se.TMS.data_access.TimeLogDao;
import se.TMS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import Project.LI.service.UserService;

import javax.validation.Valid;

@Controller
    public class LoginController {

        @Autowired
        private UserService userService;

        @Autowired
        private TimeLogDao timeLogDao;


        @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
        public ModelAndView login(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
        }


        @RequestMapping(value="/admin/registration", method = RequestMethod.GET)
        public ModelAndView registration(){
            ModelAndView modelAndView = new ModelAndView();
            User user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/admin/registration");
            return modelAndView;
        }

        @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
        public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
            ModelAndView modelAndView = new ModelAndView();
            User userExists = userService.findUserByEmail(user.getEmail());
            if (userExists != null) {
                bindingResult
                        .rejectValue("email", "error.user",
                                "There is already a user registered with the email provided");
            }
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("/admin/registration");
            } else {
                userService.saveUser(user);
                modelAndView.addObject("successMessage", "User has been registered successfully");
                modelAndView.addObject("user", new User());
                modelAndView.setViewName("/admin/registration");

            }
            return modelAndView;
        }


        @RequestMapping(value="/home", method = RequestMethod.GET)
        public ModelAndView home(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("user", user.getId());
            modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
            modelAndView.setViewName("home");
            return modelAndView;
        }


//    @RequestMapping(value="/starTime", method = RequestMethod.GET)
//    public ModelAndView startTime(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("starTime");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/starTime", method = RequestMethod.POST)
//    public ModelAndView StartTime(@Valid TimeLog timeLog, BindingResult bindingResult ){
//        ModelAndView modelAndView = new ModelAndView();
//        timeLog.setStartTime(LocalDateTime.now());
//        timeLog = timeLogRepository.save(timeLog);
//        modelAndView.addObject("timeLog", timeLog);
//
//
//        return "stopTime";
//    }
//
//
//    @RequestMapping(value="/stopTime", method = RequestMethod.GET)
//    public ModelAndView stopTime(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("stopTime");
//        return "startTime";
//    }
}
