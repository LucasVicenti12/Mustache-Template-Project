package com.example.demo.helpdesk.infra.webservice;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketService {

    @GetMapping
    public String teste() {
        return "home";
    }

    @GetMapping("/teste")
    @ResponseBody
    public String opa() throws Exception {
        System.out.println("AQUI");
        Handlebars handlebars = new Handlebars();

        Template template = handlebars.compileInline("<div>Ola {{this}}</div>");
        String teste = template.apply("Lucas");
        System.out.println(teste);
        return teste;
    }
}
