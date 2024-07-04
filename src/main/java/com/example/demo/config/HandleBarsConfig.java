package com.example.demo.config;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandleBarsConfig {
    @Bean
    public Handlebars handlebars() {
        TemplateLoader loader = new ClassPathTemplateLoader();

        loader.setPrefix("/templates");
        loader.setSuffix(".hbs");

        return new Handlebars(loader).registerHelper("ifEq", (context, options) -> {
            Object param = options.param(0, null);
            if (context != null && context.equals(param)) {
                return options.fn(options.context);
            } else {
                return options.inverse(options.context);
            }
        });
    }

    public static Handlebars hbs(){
        return new HandleBarsConfig().handlebars();
    }
}
