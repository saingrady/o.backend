package web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import business.TestService;

// one specific controller
// @EnableAutoConfiguration

@Controller
@RequestMapping("{namespace}")
// if backoffice deployed in different server from backend, also different namespace
// how to backoffice get baseBackendUrl: server/api/namespace ???
// APIRequest.prototype.baseBackendUrl = getEndPointEnvironments(requestUrl = "/api/default/endpoints").apiUrl + "/";
public class TestController {

    // CRUD
    // POST, GET, PUT, DELETE

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    @ResponseBody
    String test() {
        return testService.test();
    }

    @RequestMapping("accessToken")
    @ResponseBody
    String token() {
        return testService.accessToken();
    }
    
    // ???????
    @RequestMapping("unicode")
    @ResponseBody
    String unicode(@RequestParam("khmer") String khmer) {
        return khmer;
    }

}
