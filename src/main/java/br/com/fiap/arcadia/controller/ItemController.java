package br.com.fiap.arcadia.controller;

import br.com.fiap.arcadia.model.Item;
import br.com.fiap.arcadia.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        return getItemPorMaxPreco();
    }

    @GetMapping
    public List<Item> getParcial(@RequestParam(required = false) String nome) {
        return getItemPorNomeParcial(nome);
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
        Item itemExistente = getItem(id);

        itemExistente.setNome(item.getNome());
        itemExistente.setDono(item.getDono());
        itemExistente.setTipo(item.getTipo());
        itemExistente.setPreco(item.getPreco());
        itemExistente.setRaridade(item.getRaridade());

        return repository.save(itemExistente);
    }

    private List<Item> getItemPorNomeParcial(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum item")
                );
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
        return repository.findFirstByOrderByPrecoAsc()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum item")
                );
    }

    private Item getItemPorMaxPreco() {
        return repository.findFirstByOrderByPrecoDesc()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum item")
                );
    }

    private Item getItem(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
