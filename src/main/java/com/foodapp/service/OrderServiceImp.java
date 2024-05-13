package com.foodapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.model.Address;
import com.foodapp.model.Cart;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.Orderitem;
import com.foodapp.model.Resturant;
import com.foodapp.model.User;
import com.foodapp.repository.AddressRespository;
import com.foodapp.repository.OrderItemRepository;
import com.foodapp.repository.OrderRespository;
import com.foodapp.repository.UserRepository;
import com.request.OrderRequest;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRespository orderRespository;

    @Autowired 
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRespository addressRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResturantService resturantService;

    @Autowired CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddress=order.getDeliveryAddress();

        Address savedAddress=addressRespository.save(shippingAddress);
        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Resturant resturant=resturantService.findResturantById(order.getResturantId());

        Order createdOrder=new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreateAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setResturant(resturant);

        Cart cart=cartService.findCartByUserId(user.getId());

        List<Orderitem> orderItems=new ArrayList<>();

        for(CartItem cartItem:cart.getItem()){
            Orderitem orderItem=new Orderitem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            
            Orderitem savedOrderItem=orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice=cartService.calculateCartTotal(cart);


        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder=orderRespository.save(createdOrder);
        resturant.getOrders().add(savedOrder);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order =findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")
                             || orderStatus.equals("DELIVERED")
                             || orderStatus.equals("COMPLETED") 
                             || orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);
            return orderRespository.save(order);
        }
        throw new Exception("Please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order=findOrderById(orderId);
        orderRespository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRespository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getResturantOrder(Long resturantId, String orderStatus) throws Exception {
        List<Order> orders=orderRespository.findByResturantId(resturantId);
        if(orderStatus!=null){
            orders=orders.stream().filter(order-> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder=orderRespository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found");
        }
        return optionalOrder.get();
    }

}
