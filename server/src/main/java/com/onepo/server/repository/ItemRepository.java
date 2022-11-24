package com.onepo.server.repository;

import com.onepo.server.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    public Long save(Item item) {
        em.persist(item);

        return item.getId();
    }

    public void delete(Item item) {
        em.remove(item);
    }

    public List<Item> findAll() {
        return em.createQuery("select m from Member As m", Item.class)
                .getResultList();
    }

    public Item findItemById(Long id) {
        return em.find(Item.class,id);
    }

    public List<Item> findOriginal() {
        List resultList = em.createNativeQuery("select * from item where dtype ='O'", Item.class)
                .getResultList();

        return resultList;
    }

    public List<Item> findCollaborate() {
        List resultList = em.createNativeQuery("select * from item where dtype ='C'", Item.class)
                .getResultList();

        return resultList;
    }
}
