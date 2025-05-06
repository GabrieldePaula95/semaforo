package br.com.semaforos.semaforos_inteligentes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semaforo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Semaforo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semaforo_seq")
    @SequenceGenerator(name = "semaforo_seq", sequenceName = "seq_semaforo", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String identificador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cruzamento_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cruzamento cruzamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StatusSemaforo status;
}
