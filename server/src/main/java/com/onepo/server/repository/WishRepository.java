package com.onepo.server.repository;

<<<<<<< HEAD
import com.onepo.server.domain.wish.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
=======
import com.onepo.server.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
>>>>>>> origin/main

public interface WishRepository extends JpaRepository<Wish,Integer> {

    Wish findByMemberId(Long id);
    Wish findWishById(Long id);
    Wish findWishByMemberId(Long id);
}
