package br.com.fiap.fin_money_api.controller;

import br.com.fiap.fin_money_api.controller.model.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategotyController {

    @RequestMapping(produces = "application/json", path ="/categorias", method = {RequestMethod.GET})
    public Category index(){
        return new Category(1L,"Educação","book");
    }
}
