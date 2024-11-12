package project.Lexware_Task.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Lexware_Task.service.IbanService;


@RestController
@RequestMapping("/api/iban")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(IbanService.class);

    private final IbanService ibanService;
    @Autowired
    public Controller(IbanService ibanService) {
        this.ibanService = ibanService;
    }

    @GetMapping()
    public void test() {
        log.info("api läuft");
    }
    @GetMapping("/{iban}")
    public String getBankByIban(@PathVariable String iban) {
        log.info(iban);
        return this.ibanService.getBankByIban(iban);
    }
}
