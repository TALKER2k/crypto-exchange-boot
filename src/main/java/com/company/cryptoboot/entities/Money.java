package com.company.cryptoboot.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_money")
public class Money {
    @Id
    private Long id;
    private String name;
    private double rate;

    public Money() {
    }

    public Money(Long id, String name, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}


