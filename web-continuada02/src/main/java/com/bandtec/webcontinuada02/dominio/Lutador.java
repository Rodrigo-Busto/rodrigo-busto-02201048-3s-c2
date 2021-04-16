package com.bandtec.webcontinuada02.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Lutador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 12)
    private String nome;

    @PositiveOrZero
    private Double forcaGolpe;

    private Double vida;

    private Integer concentracoesRealizadas;

    public Lutador(Integer id, @NotBlank @Size(min = 3, max = 12) String nome, @PositiveOrZero Double forcaGolpe) {
        this.id = id;
        this.nome = nome;
        this.forcaGolpe = forcaGolpe;
        this.vida = 100.0;
        this.concentracoesRealizadas = 0;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getForcaGolpe() {
        return forcaGolpe;
    }

    public Double getVida() {
        return vida;
    }

    public Integer getConcentracoesRealizadas() {
        return concentracoesRealizadas;
    }

    public Boolean isVivo(){
        return vida > 0 ? true : false;
    }

    public void concentrar(){
        if (this.concentracoesRealizadas == 3){
            throw new RuntimeException("Lutador já se concentrou 3 vezes");
        }
        this.vida *= 1.15;
        this.concentracoesRealizadas++;
    }

    public void apanha(Double forcaGolpe){
        if (forcaGolpe >= this.vida){
            this.vida = 0.0;
        }else {
            this.vida -= forcaGolpe;
        }
    }
}
