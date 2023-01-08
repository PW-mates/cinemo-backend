package com.pw.cinema.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping(path = "payments")
    public ResponseEntity<Object> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok().body(paymentService.createPayment(payment));
    }

    @GetMapping(path = "payments")
    public ResponseEntity<Object> getPayments() {
        return ResponseEntity.ok().body(paymentService.getPayments());
    }

    @GetMapping(path = "payments/{id}")
    public ResponseEntity<Object> getPayment(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(paymentService.getPayment(id));
    }

    @PatchMapping(path = "payments/{id}")
    public ResponseEntity<Object> updatePayment(@PathVariable("id") Long id, @RequestBody Payment payment) {
        return ResponseEntity.ok().body(paymentService.updatePayment(id, payment));
    }
}
