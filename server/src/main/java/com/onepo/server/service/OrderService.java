package com.onepo.server.service;


import com.onepo.server.domain.Address;
import com.onepo.server.domain.Member;
import com.onepo.server.domain.Order;
import com.onepo.server.domain.OrderItem;
import com.onepo.server.domain.item.Item;
import com.onepo.server.repository.ItemRepository;
import com.onepo.server.repository.MemberRepository;
import com.onepo.server.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId,Long itemId,int count) {
        Member findMember = memberRepository.findOne(memberId);
        Item findItem = itemRepository.findOne(itemId);

        Address address=new Address();

        OrderItem orderItem=OrderItem.createOrderItem(findItem,findItem.getPrice(),count);

        Order order = Order.createOrder(findMember,address,orderItem);

        orderRepository.save(order);

        return order.getId();
    }
}
