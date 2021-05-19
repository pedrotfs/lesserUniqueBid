package br.com.pedrotfs.lesseruniquebid.lesserUniqueBid.dto;

import java.math.BigDecimal;

public class Bid {

    private String name;

    private BigDecimal bid;

    public Bid(String name, BigDecimal bid) {
        this.name = name;
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

}
