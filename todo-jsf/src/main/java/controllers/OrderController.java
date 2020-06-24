package controllers;


import persist.Order;
import persist.OrderRepository;
import persist.Product;
import persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class OrderController implements Serializable {

    @Inject
    private OrderRepository orderRepository;

    private Order order;

    private List<Order> orders;

    public void preLoadOrders(ComponentSystemEvent componentSystemEvent){
        this.orders=orderRepository.findAll();
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository= orderRepository;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String createOrder(){
        this.order=new Order();
        return "order.xhtml?faces-redirect=true";
    }

    public List<Order> getAllOrder() throws SQLException {
        return orders;
    }

    public String editOrder(Order order){
        this.order=new Order();
        return "order.xhtml?faces-redirect=true";
    }

    public void deleteOrder(Order order) throws SQLException {
        orderRepository.delete(order.getId());
    }

    public String saveOrder(Order order) throws SQLException {
        if(order.getId()==null) orderRepository.insert(order);
        else orderRepository.update(order);
        return "order.xhtml?faces-redirect=true";
    }

}
