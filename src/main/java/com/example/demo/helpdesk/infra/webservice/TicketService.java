package com.example.demo.helpdesk.infra.webservice;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TicketService {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/teste")
    @ResponseBody
    public String teste() throws Exception {
        System.out.println("AQUI");
        Handlebars handlebars = new Handlebars();

        Template template = handlebars.compileInline("<div>Ola {{this}}</div>");
        String teste = template.apply("Lucas");
        System.out.println(teste);
        return teste;
    }

    @GetMapping("/getModal")
    @ResponseBody
    public String getModal() throws Exception {
        Handlebars handlebars = new Handlebars();

        Map<String, String> test = new HashMap<>();
        test.put("title", "LUCAO");
        test.put("description", "TESTE UM");

        Template template = handlebars.compile("ticket_modal");
        return template.apply(test);
    }
}
