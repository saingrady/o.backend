package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import business.HelloService;

// one specific controller
// @EnableAutoConfiguration

@Controller
@RequestMapping("{namespace}")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return helloService.home();
    }

    @RequestMapping("/hello")
    @ResponseBody
    String hello() {
        return helloService.hello();
    }

}
