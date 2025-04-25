package br.com.fiap.arcadia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o nome do item é obrigatório")
    @Size(min = 3, max = 255, message = "deve ter pelo menos 3 letras")
    private String nome;

    @NotBlank
    private TipoItem tipo;

    @NotBlank
    private TipoRaridade raridade;

    @Positive(message = "deve ser positivo")
    private int preco;

    @ManyToOne
    private Personagem dono;

}
