package com.nutrimeal.nutrimeal.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == 403) {
                return "error/403"; // return the name of the Thymeleaf template for 403 error
            } else if (statusCode == 404) {
                return "error/404"; // return the name of the Thymeleaf template for 404 error
            } else if (statusCode == 500) {
                return "error/500"; // return the name of the Thymeleaf template for 500 error
            }
        }

        return "error/error"; // return a generic error template if status code is not 403, 404, or 500
    }
}
