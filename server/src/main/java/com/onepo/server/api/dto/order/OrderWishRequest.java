package com.onepo.server.api.dto.order;

import com.onepo.server.domain.delivery.Address;
import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.delivery.DeliveryStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderWishRequest {

    @NotBlank(message = "시 를 입력해주세요")
    private String city;

    @NotBlank(message ="구 를 입력해주세요")
    private String street;

    @NotBlank(message = "동 을 입력해주세요")
    private String zipcode;

    public Delivery to_Entity() {
        Delivery delivery=new Delivery();
        Address address=new Address(city,street,zipcode);
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);

        return delivery;
    }
}
