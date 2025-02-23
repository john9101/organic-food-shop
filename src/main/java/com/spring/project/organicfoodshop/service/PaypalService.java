package com.spring.project.organicfoodshop.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.project.organicfoodshop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaypalService {

    private final APIContext apiContext;

    @Value("${payment.paypal.return-url}")
    private String paypalRequestUrl;

    @Value("${payment.paypal.exchange-rate}")
    private Double exchangeRate;

    public String generatePaymentUrl(Order order, String intent) throws PayPalRESTException {
        Amount amount = new Amount("USD", String.format("%.2f",order.getTotalPrice() / exchangeRate));
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Thanh toán đơn hàng mã " + order.getId());

        Payer payer = new Payer();
        payer.setPaymentMethod(order.getPaymentMethod().name());

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paypalRequestUrl + "/cancel?orderId=" + order.getId());
        redirectUrls.setReturnUrl(paypalRequestUrl + "/success?orderId=" + order.getId());

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setTransactions(List.of(transaction));
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);

        payment = payment.create(apiContext);
        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                return link.getHref();
            }
        }
        return null;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = Payment.get(apiContext, paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecution);
    }
}
