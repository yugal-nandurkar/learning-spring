package microteam.controllers;

import microteam.services.Currency;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Step 5
public class CurrencyController {

    private Currency currency_configuration;

    //Debug application.properties non-returning currency_configurations
    @RequestMapping("/currency-service")
    public Currency retrieveCurrency() {
        return currency_configuration;
    }
}
