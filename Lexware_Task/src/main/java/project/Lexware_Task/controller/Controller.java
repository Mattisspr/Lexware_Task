package project.Lexware_Task.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Lexware_Task.service.IbanService;

@Slf4j
@RestController
@RequestMapping("/api/iban")
public class Controller {

    private final IbanService ibanService;
    @Autowired
    public Controller(IbanService ibanService) {
        this.ibanService = ibanService;
    }

    @GetMapping()
    public void test() {
        log.info("api funktioniert");
    }
    @GetMapping("/{iban}")
    public String getBankByIban(@PathVariable String iban) {
        log.info(iban);
        return this.ibanService.getBankByIban(iban);
    }
}
