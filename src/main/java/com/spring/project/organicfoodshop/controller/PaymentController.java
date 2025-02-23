package com.spring.project.organicfoodshop.controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.project.organicfoodshop.service.PaypalService;
import com.spring.project.organicfoodshop.service.VNPayService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final VNPayService vnPayService;
    private final PaypalService paypalService;

    @Value("${payment.base-redirect-url}")
    private String baseRedirectUrl;

    @GetMapping("/vnp-return")
    public void handleVNPReturn(@RequestParam Map<String, String> vnpParams, HttpServletResponse response) throws IOException {
        boolean isPaymentSuccessful = vnPayService.verifyPayment(vnpParams);
        String redirectUrl = baseRedirectUrl + "?id=" + vnpParams.get("vnp_TxnRef");
        if (isPaymentSuccessful) {
            redirectUrl += "&status=success";
        } else {
            redirectUrl += "&status=failed";
        }
        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/paypal-return/{paypalStatus}")
    public void  handlePaypalReturn(@PathVariable String paypalStatus, @RequestParam Map<String, String> paypalParams, HttpServletResponse response) throws IOException, PayPalRESTException {
        String paymentId = paypalParams.get("paymentId");
        String payerId = paypalParams.get("PayerID");
        String orderId = paypalParams.get("orderId");
        Payment payment = paypalService.executePayment(paymentId, payerId);
        String redirectUrl = baseRedirectUrl + "?id=" + orderId;
        if (paypalStatus.equals("success") && payment != null) {
            redirectUrl += "&status=success";
        }else if (paypalStatus.equals("cancel") && payment != null) {
            redirectUrl += "&status=failed";
        }
        response.sendRedirect(redirectUrl);
    }
}
