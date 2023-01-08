package com.pw.cinema.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping(path = "payments")
    public ResponseEntity<Object> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok().body(paymentService.createPayment(payment));
    }
}
