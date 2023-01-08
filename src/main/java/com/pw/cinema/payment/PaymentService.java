package com.pw.cinema.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

    public Object getPayments() {
        List<Payment> savedPayments = paymentRepository.findAll();
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedPayments);
        resp.put("message", "Successful get payments");
        return resp;
    }

    public Object getPayment(Long id) {
        Payment savedPayment = paymentRepository.findById(id)
                .orElseThrow(() ->
                    new NoSuchElementException("Payment with id not found")
                );
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", savedPayment);
        resp.put("message", "Successful get payment");
        return resp;
    }
}
