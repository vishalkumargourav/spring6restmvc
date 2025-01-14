package com.nathuncorp.spring6restmvc.model;

import lombok.Data;

public enum BeerStyle {
    LAGER("Lager"),
    PILSNER("Pilsner"),
    STOUT("Stout"),
    ALE("Ale"),
    WHEAT("Wheat"),
    PALE_ALE("Pale Ale");

    private final String beerType;

    BeerStyle(String beerType) {
        this.beerType = beerType;
    }

    public String getBeerType() {
        return beerType;
    }
}
