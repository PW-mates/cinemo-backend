package com.pw.cinema.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public Object createPayment(Payment payment) {
        payment.setId(null);
        Payment savedPayment = paymentRepository.save(payment);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedPayment);
        resp.put("message", "Successful create payment");
        return resp;
    }
}
