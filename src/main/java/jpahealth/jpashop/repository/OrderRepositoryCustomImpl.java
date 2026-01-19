package jpahealth.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jpahealth.jpashop.domain.Order;
import jpahealth.jpashop.domain.OrderSearch;
import jpahealth.jpashop.domain.OrderStatus;
import org.springframework.util.StringUtils;

import java.util.List;

import static jpahealth.jpashop.domain.QMember.member;
import static jpahealth.jpashop.domain.QOrder.order;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {
        return queryFactory
                .selectFrom(order)
                .join(order.member, member)
                .where(
                        statusEq(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getMemberName())
                )
                .limit(1000)
                .fetch();
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        return statusCond != null ? order.status.eq(statusCond) : null;
    }

    private BooleanExpression nameLike(String nameCond) {
        return StringUtils.hasText(nameCond) ? member.name.like("%" + nameCond + "%") : null;
    }
}