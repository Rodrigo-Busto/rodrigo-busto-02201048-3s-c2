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

    private Boolean vivo;

    public Lutador() {
        this.vida = 100.0;
        this.concentracoesRealizadas = 0;
        this.vivo = true;
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
        return vivo;
    }

    public void setVivo(Boolean vivo) {
        this.vivo = vivo;
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
            this.vivo = false;
        }else {
            this.vida -= forcaGolpe;
        }
    }
}
