package com.foodapp.service;

import com.foodapp.model.Order;
import com.foodapp.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService{

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
