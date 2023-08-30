package com.app.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.app.dao.PaymentRepository;
import com.app.entities.Payment;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment makePayment(Payment payment) {
        // Implement your payment logic here
        // For example, you can perform validations, calculations, and store the payment details

        // Save the payment in the database
        return paymentRepository.save(payment);
    }

    // Implement other methods for payment management
}

