package microteam.controllers;

import microteam.generic.HelloBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController //Step 1
public class Hello {

    // Step 28.2
    private MessageSource messageSource;

    public Hello(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //@RequestMapping(path="/hello", method = RequestMethod.GET) //Step 2
    @GetMapping(path="/hello") // Step 3
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping(path="/hello-bean") // Step 4
    public HelloBean sayHelloBean() {
        return new HelloBean("Hello Bean");
    }

    // HelloBean: Step 5, 6

    @GetMapping(path="/hello-bean/path-variable/{name}") // Step 7
    public HelloBean sayHelloPath(@PathVariable String name) {
        return new HelloBean(String.format("Hello Beanie, %s", name));
    }

    // User: Step 8

    @GetMapping(path="/hello-i18n") // Step 28.1
    public String sayHelloI18n() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }

    // VersioningPerson: Step 29
}
