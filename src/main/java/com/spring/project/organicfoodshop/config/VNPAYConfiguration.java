package com.spring.project.organicfoodshop.config;

import com.spring.project.organicfoodshop.domain.Order;
import org.apache.hc.client5.http.utils.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Configuration
public class VNPAYConfiguration {
    @Value("${payment.vnpay.url}")
    private String vnpPayUrl;

    @Value("${payment.vnpay.return-url}")
    private String vpnReturnUrl;

    @Value("${payment.vnpay.tmn-code}")
    private String vpnTmnCode;

    @Value("${payment.vnpay.secret-key}")
    private String vpnSecretKey;

    @Value("${payment.vnpay.version}")
    private String vpnVersion;

    @Value("${payment.vnpay.command}")
    private String vpnCommand;

    @Value("${payment.vnpay.order-type}")
    private String vpnOrderType;

    @Value("${payment.vnpay.timeout}")
    private Integer vnpTimeout;

    public Map<String, String> getVNPAYConfig(Order order, String ipAddress) {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", vpnVersion);
        vnpParamsMap.put("vnp_Command", vpnCommand);
        vnpParamsMap.put("vnp_TmnCode", vpnTmnCode);
        vnpParamsMap.put("vnp_Amount", String.valueOf(order.getTotalPrice().intValue() * 100));
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toán đơn hàng " +  order.getId());
        vnpParamsMap.put("vnp_OrderType", vpnOrderType);
        vnpParamsMap.put("vnp_TxnRef", String.valueOf(order.getTransactionReferenceCode()));
        vnpParamsMap.put("vnp_IpAddr", ipAddress);
        vnpParamsMap.put("vnp_Locale", "vn");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
        calendar.add(Calendar.MINUTE, vnpTimeout);
        String vnp_ExpireDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
        vnpParamsMap.put("vnp_ReturnUrl", vpnReturnUrl);
        return vnpParamsMap;
    }

    public String hmacSHA512(String data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(vpnSecretKey.getBytes(), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hashBytes = mac.doFinal(data.getBytes());
            return Hex.encodeHexString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC SHA512", e);
        }
    }

    public String buildPaymentUrl(String queryUrl){
        return vnpPayUrl + "?" + queryUrl;
    }
}
