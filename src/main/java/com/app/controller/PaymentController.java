package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.ApiResponse;
import com.app.entities.Payment;
import com.app.service.IPaymentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody Payment payment) {
        try {
            Payment newPayment = paymentService.makePayment(payment);
            return ResponseEntity.ok(newPayment);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Other endpoints for payment management (e.g., update, delete, getPaymentDetails)
}

