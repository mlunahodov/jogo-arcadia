package br.com.fiap.arcadia.repository;

import br.com.fiap.arcadia.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<List<Item>> findByRaridade(String raridade);
    Optional<List<Item>> findByTipo(String tipo);
    Optional<List<Item>> findByNomeContainingIgnoreCase(String nome);
    Optional<Item> findFirstByOrderByPrecoAsc();
    Optional<Item> findFirstByOrderByPrecoDesc();


}
