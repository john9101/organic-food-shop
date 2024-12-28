package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.config.VNPAYConfiguration;
import com.spring.project.organicfoodshop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VNPayService {
    private final VNPAYConfiguration vnpayConfiguration;

    public String generatePaymentUrl(Order order, String ipAddress){
        Map<String, String> vnpParamsMap = vnpayConfiguration.getVNPAYConfig(order, ipAddress);
//        List fieldNames = new ArrayList(vnpParamsMap.keySet());
//        Collections.sort(fieldNames);
//        StringBuilder hashData = new StringBuilder();
//        StringBuilder query = new StringBuilder();
//        Iterator iterator = fieldNames.iterator();
//        while (iterator.hasNext()) {
//            String fieldName = (String) iterator.next();
//            String fieldValue = vnpParamsMap.get(fieldName);
//            if ((fieldValue != null) && (fieldValue.length() > 0)) {
//                hashData.append(fieldName);
//                hashData.append('=');
//                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
//                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
//                query.append('=');
//                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
//                if (iterator.hasNext()) {
//                    query.append('&');
//                    hashData.append('&');
//                }
//            }
//        }

        List<String> fieldNames = new ArrayList<>(vnpParamsMap.keySet());
        Collections.sort(fieldNames);

        StringJoiner hashData = new StringJoiner("&");
        StringJoiner query = new StringJoiner("&");

        for (String fieldName : fieldNames) {
            String fieldValue = vnpParamsMap.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                String encodedFieldName = URLEncoder.encode(fieldName, StandardCharsets.US_ASCII);
                String encodedFieldValue = URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII);

                hashData.add(encodedFieldName + "=" + encodedFieldValue);
                query.add(encodedFieldName + "=" + encodedFieldValue);
            }
        }

        String queryUrl = query.toString();
        String vnpSecureHash = vnpayConfiguration.hmacSHA512(hashData.toString());
        queryUrl += "&vnp_SecureHash=" +  vnpSecureHash;
        return vnpayConfiguration.buildPaymentUrl(queryUrl);
    }


    public boolean verifyPayment(Map<String, String> vnpParams) {
        String vnpResponseCode = vnpParams.get("vnp_ResponseCode");
        String vnpTransactionStatus = vnpParams.get("vnp_TransactionStatus");
        return vnpResponseCode.equals("00") && vnpTransactionStatus.equals("00");
    }
}
