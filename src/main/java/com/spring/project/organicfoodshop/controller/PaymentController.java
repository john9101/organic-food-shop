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
        String redirectUrl = "http://localhost:5173/order-result?id=" + vnpParams.get("vnp_TxnRef");
        if (isPaymentSuccessful) {
            redirectUrl += "&status=success";
        } else {
            redirectUrl += "&status=failed";
        }
        response.sendRedirect(redirectUrl);
    }

}
