package com.onepo.server.repository;

import com.onepo.server.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId()==null) {
            em.persist(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class,id);
    }
}
