package com.example.projectshop.service;

import com.example.projectshop.model.stripe.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public interface StripeService {
    void init();
    Charge charge(ChargeRequest chargeRequest) throws StripeException;
}
