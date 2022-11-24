package com.onepo.server.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class OriginalSeries extends Item {


    public OriginalSeries(String itemName, Integer price, Integer stockQuantity, String description, String artist, List<String> filePath) {
        super(itemName, price, stockQuantity, description, artist, filePath);
    }
}
