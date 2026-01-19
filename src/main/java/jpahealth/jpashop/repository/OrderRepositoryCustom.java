package jpahealth.jpashop.repository;

import jpahealth.jpashop.domain.Order;
import jpahealth.jpashop.domain.OrderSearch;
import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> search(OrderSearch orderSearch);
}