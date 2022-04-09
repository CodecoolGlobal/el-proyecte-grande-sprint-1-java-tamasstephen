package com.example.demo.controller;


import java.io.IOException;
import java.nio.file.Paths;

import com.example.demo.model.SupporterModel;
import com.example.demo.model.tip.Tip;
import com.example.demo.service.TipService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CheckoutController {

    @Autowired
    private TipService tipService;

    @CrossOrigin
    @PostMapping("/create-checkout-session/{causeLink}/{coinAmount}")
    public RedirectView checkout(@PathVariable String causeLink, @PathVariable String coinAmount, @ModelAttribute SupporterModel supporter) throws StripeException, IOException {
        Tip tip =  new Tip(Integer.parseInt(coinAmount), causeLink, supporter.getName(), supporter.getComment());
        Stripe.apiKey = "sk_test_51KmFQpCSqoetVktqxEKJngGzIxKP0xhouCdTmOCgyRyqJ4Nh5Vb5MjFUjzuCHEdoWzveXV2lrguF9rvMOinnw9pV005XVKYbWD";

        //TODO: Implement the save when the payment was successful
        String YOUR_DOMAIN = "http://localhost:3000/";
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "success")
                        .setCancelUrl(YOUR_DOMAIN + "?canceled=true")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                        .setPrice(priceFactory(coinAmount))
                                        .build())
                        .build();
        Session session = Session.create(params);
        tipService.add(tip);
        return new RedirectView(session.getUrl());
    }

    private String priceFactory(String coinAmount){
        switch (coinAmount){
            case "1":
                return "price_1KmJC0CSqoetVktqHBvZD3Es";
            case "5":
                return "price_1KmJFQCSqoetVktqIlq5bodX";
        }
        return "price_1KmJG0CSqoetVktq5sX2NLoy";
    }
}
