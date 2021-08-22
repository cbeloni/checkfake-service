package br.com.checkfakeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="nome")
    private String nome;

    @Lob
    @Column(name="mensagem")
    private String mensagem;

    @Column(name="is_fake", columnDefinition = "boolean default false")
    private Boolean isFake;

    @Column (name="data")
    private Date date;
}
