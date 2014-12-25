package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import business.WorldService;

// one specific controller
// @EnableAutoConfiguration

@Controller
@RequestMapping("{namespace}")
public class WorldController {

    @Autowired
    private WorldService worldService;

    @RequestMapping("/world")
    @ResponseBody
    String world() {
        return worldService.world();
    }
    
}
