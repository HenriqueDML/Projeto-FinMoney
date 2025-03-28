package br.com.fiap.Bank_api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String index () {
        return "Projeto: Bank-api || Nome: Vinícius Saes || RM: 554456 || SALA: 2TDSPK";
    }
}
