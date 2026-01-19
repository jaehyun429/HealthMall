package jpahealth.jpashop.service;

import jpahealth.jpashop.domain.*;
import jpahealth.jpashop.domain.item.Item;
import jpahealth.jpashop.repository.ItemRepository;
import jpahealth.jpashop.repository.MemberRepository;
import jpahealth.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다. ID: " + memberId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 상품입니다. ID: " + itemId));

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 주문입니다. ID: " + orderId));
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.search(orderSearch);
    }
}