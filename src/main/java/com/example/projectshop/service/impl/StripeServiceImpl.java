package com.example.projectshop.service.impl;

import com.example.projectshop.model.stripe.ChargeRequest;
import com.example.projectshop.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("sk_test_51J9uezDwr6Ee0MykWo201zhIcg87pxpR9LwnyeO20iOB2iHwNsHhFBI1xMmqKI63YAqDYZduqZNXXaaLMO2oQEaX00oHCPsOSp")
    private String secretKey;

    @Override
    @PostConstruct
    public void init() {
        Stripe.apiKey = this.secretKey;
    }

    @Override
    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}
