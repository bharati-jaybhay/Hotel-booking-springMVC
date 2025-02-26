package com.sprk.sprk_hotels.controller;

import com.sprk.sprk_hotels.model.Payment;
import com.sprk.sprk_hotels.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String showPaymentPage(Model model) {
        model.addAttribute("payment", new Payment());
        return "payment";
    }

    @PostMapping("/process")
    public String processPayment(@ModelAttribute Payment payment, Model model) {
        paymentService.savePayment(payment);
        model.addAttribute("message", "Payment successful!");
        return "payment-success";
    }
}
