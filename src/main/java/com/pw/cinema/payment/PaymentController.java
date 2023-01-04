package com.pw.cinema.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;
}
