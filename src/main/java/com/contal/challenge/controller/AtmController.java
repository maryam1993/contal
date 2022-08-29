package com.contal.challenge.controller;

import com.contal.challenge.service.AtmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/atm")
public class AtmController {

    private AtmService atmService;

    @GetMapping("/options")
    public List<Integer> getAtmDispenseMoneyOptions() {
        return atmService.getAtmDispenseMoneyOptions();
    }

    @GetMapping("/holding")
    public Map<Integer, Integer> getCurrentCashHolding() {
        return atmService.getCurrentCashHolding();
    }

    @PutMapping("/dispense/{amount}")
    public void dispenseMoney(@PathVariable("amount") Integer amount) {
        atmService.dispenseMoney(amount);
    }
}
