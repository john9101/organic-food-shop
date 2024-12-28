package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.service.VNPayService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final VNPayService vnPayService;

    @GetMapping("/vnp-return")
    public void handleVNPReturn(@RequestParam Map<String, String> vnpParams, HttpServletResponse response) throws IOException {
        boolean isPaymentSuccessful = vnPayService.verifyPayment(vnpParams);
        String redirectUrl;
        if (isPaymentSuccessful) {
            redirectUrl = "https://frontend.example.com/payment-success?orderId=" + vnpParams.get("vnp_TxnRef");
        } else {
            redirectUrl = "https://frontend.example.com/payment-failed?orderId=" + vnpParams.get("vnp_TxnRef");
        }
        response.sendRedirect(redirectUrl);
    }

}
