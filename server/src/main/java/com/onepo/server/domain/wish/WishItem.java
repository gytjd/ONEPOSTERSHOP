package com.onepo.server.domain.wish;


import com.onepo.server.domain.item.Item;
import lombok.*;
import org.yaml.snakeyaml.tokens.FlowEntryToken;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class WishItem {

    @Id
    @GeneratedValue
    @Column(name="WISH_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="WISH_ID")
    private Wish wish;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;
    private int wishCount;

    public WishItem(Wish wish, Item item,int wishCount) {
        this.wish = wish;
        this.item = item;
        this.wishCount = wishCount;
    }

    public static WishItem cartItem(Wish wish, Item item,int wishCount) {
        WishItem wishItem=new WishItem(wish,item,wishCount);

        return wishItem;
    }

    public void addCount(int count) {
        this.wishCount+=count;
    }
    
    public void subCount(int count) {
        this.wishCount-=count;
    }
}
