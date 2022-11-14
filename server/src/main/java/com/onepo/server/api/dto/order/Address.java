package com.onepo.server.api.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipCode;

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
