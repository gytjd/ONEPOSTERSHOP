package com.onepo.server.api.dto.order;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {

    private String city;
    private String street;
    private String zipcode;
}
