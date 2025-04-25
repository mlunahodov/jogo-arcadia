package br.com.fiap.arcadia.repository;

import br.com.fiap.arcadia.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<List<Item>> findByRaridade(String raridade);
    Optional<List<Item>> findByTipo(String tipo);

    @Query("SELECT I FROM ITEM I ORDER BY I.PRECO ASC")
    Optional<Item> findByMinPreco();

    @Query("SELECT I FROM ITEM I ORDER BY I.PRECO DESC")
    Optional<Item> findByMaxPreco();
}
