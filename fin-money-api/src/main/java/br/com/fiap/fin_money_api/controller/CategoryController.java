package br.com.fiap.fin_money_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.fin_money_api.model.Category;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CategoryController {

    private List<Category> repository = new ArrayList<>();

    @GetMapping("/categories")
    public List<Category> index() {
        return repository;
    }

    @PostMapping("/categories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category create(@RequestBody Category category) {
        System.out.println("Cadastrando categoria " + category.getName());
        repository.add(category);
        return category;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id) {
        System.out.println("Buscando categoria " + id);
        return ResponseEntity.ok(getCategory(id));
    }

    //apagar
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        System.out.println("Apagando categoria" + id);
        var category = getCategory(id);
        repository.remove(getCategory(id));
        return ResponseEntity.noContent().build();//para endpoints do tipo delete 204
    }

    //editar
    //PUT sempre mais viável(json), mas podemos usar o PATCH
    @PutMapping ("/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        System.out.println("Atualizando categoria" + id + " " + category);
        repository.remove(getCategory(id));
        category.setId(id);
        repository.add(category);
        return ResponseEntity.ok(category);
    }

    private Category getCategory(Long id) {
        return repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        ()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Categoria não encontrada"));
    }
}
