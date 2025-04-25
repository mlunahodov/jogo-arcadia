package br.com.fiap.arcadia.controller;

import br.com.fiap.arcadia.model.Item;
import br.com.fiap.arcadia.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    public List<Item> index() {
        return repository.findAll();
    }

    @GetMapping("{raridade}")
    public List<Item> getRaridade(@PathVariable String raridade) {
        return getItemPorRaridade(raridade);
    }

    @GetMapping("{tipo}")
    public List<Item> getTipo(@PathVariable String tipo) {
        return getItemPorTipo(tipo);
    }

    @GetMapping("/minPreco")
    public Item getMinPreco() {
        return getItemPorMinPreco();
    }

    @GetMapping("/maxPreco")
    public Item getMaxPreco() {
        return
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Item> create(@RequestBody Item item) {
        repository.save(item);
        return ResponseEntity.status(201).body(item);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        repository.delete(getItem(id));
    }

    @PutMapping("{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);

        return repository.save(item);
    }

    private List<Item> getItemPorRaridade(String raridade) {
        return repository.findByRaridade(raridade)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum item encontrado com a raridade " + raridade)
                );
    }

    private List<Item> getItemPorTipo(String tipo) {
        return repository.findByTipo(tipo)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum item encontrado com o tipo " + tipo)
                );
    }

    private Item getItemPorMinPreco() {
        return repository.findByMinPreco()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum item")
                );
    }

    private Item getItemPorMaxPreco() {
        return repository.findByMaxPreco();
    }

    private Item getItem(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
