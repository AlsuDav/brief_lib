package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.SignUpDto;
import ru.itis.springbootdemo.service.SignUpService;
import ru.itis.springbootdemo.service.SignUpServiceImpl;
import ru.itis.springbootdemo.service.SmsAeroServiceImpl;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService service;

    @Autowired
    private SmsAeroServiceImpl smsAeroService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up";
    }
//    @PostMapping("/signUp")
//    public String signUp(SignUpDto form) {
//        System.out.println(form.getName());
//        System.out.println(form.getEmail());
//        System.out.println(form.getPassword());
//        service.SignUp(form);
//        return "redirect:/signUp";
//        //redirect-новый запрос, т.е. сервер отпр киберзаголовок с новым урлом
//        //и браузер посылает новый запрос по этому урлу
//    }
    @ResponseBody
    @RequestMapping(path = "/signUp", produces = "application/text; charset=UTF-8")
    public String signUp(SignUpDto form) {

        if (form.getCode().equals(form.getConfirmCode())) {
           service.SignUp(form);
            return "ok";
        }
        else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping(path = "/signUp/sendConfirmCode", produces = "application/text; charset=UTF-8")
    public String sendCode(@RequestParam("phone") String phone) {
        return smsAeroService.sendConfirmCode(phone);
    }
}
