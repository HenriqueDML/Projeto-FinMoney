package br.com.fiap.fin_money_api.controller;

import br.com.fiap.fin_money_api.model.Category;
import br.com.fiap.fin_money_api.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired // Injeção de dependência
    private CategoryRepository repository;

    //Toda anotação possui um atributo padrão, neste caso, quando for passar apenas o atributo padrão, não é necessario identificar o atributo
    @GetMapping
    // Quando devolvemos um objeto, automaticamente ele irá retornar um JSON
    public List<Category> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category create(@RequestBody Category category) {
        log.info("Nome da categoria: " + category.getName());
        repository.save(category);
        return category;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id) {
        // 1L -> o L representa um Long
        log.info("buscando categoria: " + id);
        return ResponseEntity.ok(getCategory(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("Apagando categoria: " + id);
        repository.delete(getCategory(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        log.info("Atualizando categoria: " + id + " " + category.getName());
        getCategory(id);
        category.setId(id);
        repository.save(category);

        return ResponseEntity.ok(category);
    }

    private Category getCategory(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria não encontrada"
                )
        );
    }
}
