package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping()
    public String aa(){
        System.out.println("컨트롤러");
        return "";
    }

}
