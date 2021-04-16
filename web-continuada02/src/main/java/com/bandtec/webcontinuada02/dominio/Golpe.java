package com.bandtec.webcontinuada02.dominio;

import javax.validation.constraints.Positive;

public class Golpe {

    @Positive
    private Integer idLutadorBate;

    @Positive
    private  Integer idLutadorApanha;

    public Golpe(@Positive Integer idLutadorBate, @Positive Integer idLutadorApanha) {
        this.idLutadorBate = idLutadorBate;
        this.idLutadorApanha = idLutadorApanha;
    }

    public Integer getIdLutadorBate() {
        return idLutadorBate;
    }

    public Integer getIdLutadorApanha() {
        return idLutadorApanha;
    }
}
