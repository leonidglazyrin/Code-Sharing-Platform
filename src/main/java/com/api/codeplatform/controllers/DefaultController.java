package com.api.codeplatform.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController implements ErrorController {

    @RequestMapping("/")
    public String defaultMapping(HttpServletResponse httpResponse){
        return "default";
    }

    @RequestMapping("/error")
    public RedirectView handleError() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://github.com/Student42069/Code-Sharing-Platform#readme");
        return redirectView;
    }
}
