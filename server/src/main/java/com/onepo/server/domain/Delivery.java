package com.onepo.server.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name="DELIVERY_ID")
    private Long id;

    private String address;


}
