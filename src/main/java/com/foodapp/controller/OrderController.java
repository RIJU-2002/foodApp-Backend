package com.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.model.Order;
import com.foodapp.model.User;
import com.foodapp.response.PaymentResponse;
import com.foodapp.service.OrderService;
import com.foodapp.service.PaymentService;
import com.foodapp.service.UserService;
import com.request.OrderRequest;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req,
                                                    @RequestHeader ("Authorization") String jwt) throws Exception{

        User user=userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(req, user);
        PaymentResponse res=paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader ("Authorization") String jwt) throws Exception{

        User user=userService.findUserByJwtToken(jwt);
        List<Order> orderlist=orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(orderlist,HttpStatus.OK);
    }

    
}
