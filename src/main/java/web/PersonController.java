package web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import business.PersonService;
import domain.Person;


// one specific controller
// @EnableAutoConfiguration

@Controller
@RequestMapping("{namespace}/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "v1/csv", produces = "text/csv")
    @ResponseBody
    public void exportCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String filename = String.format("%s-%s.csv", "persons", new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        response.setContentType("text/csv");

        BufferedWriter writer = new BufferedWriter(response.getWriter());
        // header
        writer.append("\"FIRST NAME\"");
        writer.append(",");
        writer.append("\"LAST NAME\"");
        writer.append("\n");
        // body
        Person person = personService.exportCsv();
        writer.append(String.format("\"%s\"", person.getFirstName()));
        writer.append(",");
        writer.append(String.format("\"%s\"", person.getLastName()));
        writer.append("\n");
        // close
        writer.flush();
        writer.close();

    }

}
